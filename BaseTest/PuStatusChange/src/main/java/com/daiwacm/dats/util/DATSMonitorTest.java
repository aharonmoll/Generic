package com.daiwacm.dats.util;


        import org.apache.commons.lang.StringUtils;
import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.pu.events.ProcessingUnitInstanceProvisionStatusChangedEvent;
import org.openspaces.admin.pu.events.ProcessingUnitInstanceProvisionStatusChangedEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aharon on 6/6/17.
 */
public class DATSMonitorTest {
//    private static final int PU_DEPLOY_READY_MILLIS = 600000;  // 10 mins
    private static final int PU_DEPLOY_READY_MILLIS = 240000;  // 4 mins

    private static Logger log = LoggerFactory.getLogger(DATSMonitorTest.class);

    private Timer timer = null;
    private Admin admin = null;

    public DATSMonitorTest() {}

    @PreDestroy
    public void preDestroy() {
        log.info("Stopping DATS MonitorService");
        admin.close();
        timer.cancel();
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Starting DATS MonitorService");
        String lookuplocators = System.getenv("LOOKUPLOCATORS");
        if (StringUtils.isEmpty(lookuplocators)) {
            lookuplocators = "127.0.0.1";
        }
        admin = new AdminFactory().addLocators(lookuplocators).createAdmin();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                initializeProcessingUnits();
            }
        }, PU_DEPLOY_READY_MILLIS);
    }

    private void initializeProcessingUnits() {
        log.info("Initializing Processing Units");

//        String lookuplocators = System.getenv("LOOKUPLOCATORS");
//        if (StringUtils.isEmpty(lookuplocators)) {
//            lookuplocators = "127.0.0.1";
//        }
//        admin = new AdminFactory().addLocators(lookuplocators).createAdmin();

        admin.getProcessingUnits().getProcessingUnitInstanceProvisionStatusChanged().add(new ProcessingUnitInstanceProvisionStatusChangedEventListener() {
            @Override
            public void processingUnitInstanceProvisionStatusChanged(
                    ProcessingUnitInstanceProvisionStatusChangedEvent e) {
                ProcessingUnitInstance pu = e.getProcessingUnitInstance();
                log(pu, "PU status has changed from " + e.getPreviousStatus().name() + " to " + e.getNewStatus().name());
            }
        }, false);
    }

    public void log(ProcessingUnitInstance pu, String msg) {
        String message = new StringBuilder("\nMessage: ").append(msg)
                .append("\nInstance: ").append(getPUName(pu))
                .append("\nHost-ProcessId: ").append(getPUKey(pu))
                .append("\n")
                .toString();
        log.info(message);
    }

    private String getPUName(ProcessingUnitInstance p) {
        return p.getName() + "." + p.getInstanceId();
    }

    private String getPUKey(ProcessingUnitInstance p) {
        return p.getMachine().getHostName() + "-" + p.getVirtualMachine().getDetails().getPid();
    }
}
