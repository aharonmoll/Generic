package com.gigaspaces.tools;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.space.SpaceDeployment;

/**
 * Created by ronz on 6/21/2015.
 */
public class DeployCluster {
    public static void main(String[] args) {
        Admin admin = new AdminFactory().addLocator("s01,s02").createAdmin();
        GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne();
        SpaceDeployment mySpace = new SpaceDeployment("mySpace");
        mySpace.numberOfInstances(20);
        mySpace.numberOfBackups(1);
        mySpace.maxInstancesPerMachine(1);
        mySpace.maxInstancesPerVM(1);
        gsm.deploy(mySpace);

        admin.close();
    }
}
