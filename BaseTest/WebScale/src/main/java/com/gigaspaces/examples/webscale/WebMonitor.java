package com.gigaspaces.examples.webscale;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: ezer
 * Date: Mar 23, 2009
 * Time: 2:10:15 PM
 */
public class WebMonitor implements InitializingBean, DisposableBean, Runnable {

    private String processingUnitName;
    private long maxRequestPerInstance;
    private ScheduledExecutorService executorService;
    private ProcessingUnit pu;
    private Admin admin;
    private long scaleTimestamp = System.currentTimeMillis();

    public void setProcessingUnitName(String processingUnitName) {
        this.processingUnitName = processingUnitName;
    }

    public void setMaxRequestPerInstance(long maxRequestPerInstance) {
        this.maxRequestPerInstance = maxRequestPerInstance;
    }

    public void afterPropertiesSet() throws Exception {
        admin = new AdminFactory().createAdmin();
        admin.getLookupServices().waitFor(1,10, TimeUnit.SECONDS);
        System.out.println(admin.getLookupServices().getLookupServices().length);
        pu = admin.getProcessingUnits().waitFor(processingUnitName, 30000L, TimeUnit.SECONDS);
        System.out.println("PU is " + pu);
        if (pu != null) {
            executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleWithFixedDelay(this, 1, 1, TimeUnit.SECONDS);
        }
    }

    public void run() {
        long averageRequests = getAverageRequests();
        if (averageRequests > maxRequestPerInstance) {
            scaleUp();
        }

        // if there is nothing going on since it scale up/down for a minute - scale down
        if (System.currentTimeMillis() - scaleTimestamp > 60000)
        {
	        if (averageRequests==0) {
	            scaleDown();
	            scaleTimestamp = System.currentTimeMillis();
	        }
        }
    }

    private void scaleUp() {
        System.out.println("Scaling up...");
        //admin.getGridServiceAgents().getAgents()[0].startGridServiceAndWait(new GridServiceContainerOptions());
        pu.incrementInstance();
        try {
            Thread.sleep(10000L);
            scaleTimestamp = System.currentTimeMillis();
        } catch (InterruptedException e) {}

    }

    private void scaleDown() {
    	
    	if (pu.getInstances().length == 1) return;
    	
        System.out.println("Scaling down...");
        //admin.getGridServiceAgents().getAgents()[0].startGridServiceAndWait(new GridServiceContainerOptions());
        pu.decrementInstance();
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {}
    }

    public void destroy() throws Exception {
        executorService.shutdownNow();
        admin.close();
    }

    public long getAverageRequests() {
        long totalRequests = 0;
        long previousTotalRequests = 0;
        for (ProcessingUnitInstance instance : pu) {
            instance.getStatistics().getPrevious();
        }
        for (ProcessingUnitInstance instance : pu) {
            if (instance.getStatistics().getPrevious() == null) {
                return 0;
            }
            totalRequests += instance.getStatistics().getWebRequests().getTotal();
            previousTotalRequests += instance.getStatistics().getPrevious().getWebRequests().getTotal();
        }
        long averageRequests = ((totalRequests - previousTotalRequests)/ pu.getTotalNumberOfInstances());
        System.out.println("Average [" + averageRequests + "], Total [" + totalRequests + "] Previous Total [" + previousTotalRequests + "]");
        return averageRequests; 
    }
}
