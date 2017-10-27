package com.elastic;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.elastic.config.DiscoveredMachineProvisioningConfigurer;
import org.openspaces.admin.pu.elastic.config.ManualCapacityScaleConfigurer;
import org.openspaces.admin.space.ElasticSpaceDeployment;
import org.openspaces.core.util.MemoryUnit;

import java.util.concurrent.TimeUnit;

/**
 * Created by aharon on 2/27/15.
 */
public class ElasticMain {
    public static void main(String[] args)
    {
        AdminFactory factory = new AdminFactory();
        factory.addGroup("aharon");
        factory.addLocator("pc-lab120,pc-lab89");
        Admin admin = factory.createAdmin();
        GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne(5, TimeUnit.MINUTES);

        ProcessingUnit pu1 = gsm.deploy(
                new ElasticSpaceDeployment("mySpace")
                        .maxMemoryCapacity(512, MemoryUnit.MEGABYTES)
                        .memoryCapacityPerContainer(32, MemoryUnit.MEGABYTES)
                                // discover only agents with "zoneX"
                        .dedicatedMachineProvisioning(new DiscoveredMachineProvisioningConfigurer()
                                .addGridServiceAgentZone("zoneX").addGridServiceAgentZone("zoneY")
                                .removeGridServiceAgentsWithoutZone()
                                .create())
                        .scale(new ManualCapacityScaleConfigurer()
                                .memoryCapacity(128, MemoryUnit.MEGABYTES)
                                .create()));
    }
}
