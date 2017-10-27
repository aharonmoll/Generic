package com.gigaspaces.tools;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.vm.VirtualMachine;

import java.util.Date;

/**
 * Created by ronz on 6/21/2015.
 */
public class RunFullGC {
    public static void main(String[] args) throws InterruptedException {
        Admin admin = new AdminFactory().addLocator("s01,s02").createAdmin();
        GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne();
        admin.getGridServiceContainers().waitFor(20);
        Thread.sleep(2000);

        System.out.println("Current time is " + new Date());
        VirtualMachine[] virtualMachines = admin.getVirtualMachines().getVirtualMachines();
        for (VirtualMachine virtualMachine : virtualMachines) {
            System.out.println("Running Full GC on partition id " + virtualMachine.getUid());
            virtualMachine.runGc();
        }

        admin.close();
    }
}
