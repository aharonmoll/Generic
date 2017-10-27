package com.gigaspaces.tools;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitDeployment;
import org.openspaces.admin.pu.ProcessingUnits;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by ronz on 6/21/2015.
 */
public class DeployRest {
    public static void main(String[] args) {
        Admin admin = new AdminFactory().addLocator("localhost").createAdmin();
        GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne();
        admin.getProcessingUnits().waitFor("rest-async", 2000, TimeUnit.MILLISECONDS);
        ProcessingUnit processingUnit = admin.getProcessingUnits().getProcessingUnit("rest-async-new");
        if (processingUnit != null) {
            System.out.println("Need to undeploy first");
            processingUnit.undeploy();
        }

        //Example for Six
        ProcessingUnits processingUnits = admin.getProcessingUnits();
        Map<String, ProcessingUnit> pus = processingUnits.getNames();

        for (Map.Entry<String, ProcessingUnit> entry : pus.entrySet()) {
            String puName = entry.getKey();
            System.out.println("Undeploy pu = "+ puName);
            ProcessingUnit value = entry.getValue();
            processingUnit.undeployAndWait();
        }
        //end of example

        ProcessingUnitDeployment deployment = new ProcessingUnitDeployment(new File("/Users/aharon/IdeaProjects/Generic/BaseTest/my-rest/target/rest-async-new.war"));
        gsm.deploy(deployment);

        admin.close();
    }
}
