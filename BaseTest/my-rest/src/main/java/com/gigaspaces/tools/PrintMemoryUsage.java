package com.gigaspaces.tools;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitPartition;
import org.openspaces.admin.vm.VirtualMachine;

import java.util.Date;

/**
 * Created by ronz on 6/21/2015.
 */
public class PrintMemoryUsage {
    public static void main(String[] args) throws InterruptedException {
        Admin admin = new AdminFactory().addLocator("s01,s02").createAdmin();
        GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne();
        admin.getGridServiceContainers().waitFor(20);
        Thread.sleep(2000);

        while (true) {
            System.out.println("Current time is " + new Date());
            VirtualMachine[] virtualMachines = admin.getVirtualMachines().getVirtualMachines();
            for (VirtualMachine virtualMachine : virtualMachines) {
                System.out.println("virtualMachine id " + virtualMachine.getUid() +
                        " memory used: " + virtualMachine.getStatistics().getMemoryHeapUsedInMB() +
                        " memory define: " + virtualMachine.getStatistics().getMemoryHeapCommittedInMB());
            }

            long countObjects = 0;
            ProcessingUnit mySpace = admin.getProcessingUnits().getProcessingUnit("mySpace");
            ProcessingUnitPartition[] partitions = mySpace.getPartitions();
            for (int i = 0; i < partitions.length; i++) {
                ProcessingUnitPartition partition = partitions[i];
                long objectCount = partition.getPrimary().getSpaceInstance().getStatistics().getObjectCount();
                System.out.println("partition " + partition.getPartitionId() + ", object count = " + objectCount);
                countObjects += objectCount;
            }

            System.out.println("Total objects = " + countObjects);
            Thread.sleep(15000);
        }
        //admin.close();
    }
}
