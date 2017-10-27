package gs.example;

import com.gigaspaces.cluster.activeelection.SpaceMode;
import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.esm.ElasticServiceManager;
import org.openspaces.admin.esm.events.ElasticServiceManagerLifecycleEventListener;
import org.openspaces.admin.gsc.GridServiceContainer;
import org.openspaces.admin.gsc.events.GridServiceContainerLifecycleEventListener;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.gsm.events.GridServiceManagerLifecycleEventListener;
import org.openspaces.admin.lus.LookupService;
import org.openspaces.admin.lus.events.LookupServiceLifecycleEventListener;
import org.openspaces.admin.machine.Machine;
import org.openspaces.admin.machine.events.MachineLifecycleEventListener;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.pu.events.*;
import org.openspaces.admin.space.Space;
import org.openspaces.admin.space.SpaceInstance;
import org.openspaces.admin.space.events.*;
import org.openspaces.admin.vm.VirtualMachine;
import org.openspaces.admin.vm.events.VirtualMachineLifecycleEventListener;
import org.openspaces.admin.zone.Zone;
import org.openspaces.admin.zone.events.ZoneLifecycleEventListener;


/**
 * Created by aharon on 6/4/17.
 */
public class TestEventSampler implements MachineLifecycleEventListener,
        ElasticServiceManagerLifecycleEventListener,
        GridServiceContainerLifecycleEventListener,
        GridServiceManagerLifecycleEventListener,
        ProcessingUnitLifecycleEventListener,
        ProcessingUnitInstanceLifecycleEventListener,
        LookupServiceLifecycleEventListener,
        VirtualMachineLifecycleEventListener,
        SpaceLifecycleEventListener,
        SpaceInstanceLifecycleEventListener,
        SpaceModeChangedEventListener,
        ReplicationStatusChangedEventListener,
        ZoneLifecycleEventListener{

    public static void main(String[] args) throws Exception {
        TestEventSampler eventSampler = new TestEventSampler();
        Admin admin = new AdminFactory().addGroup("aharon").addLocator("aharon-osx.local").createAdmin();
        admin.addEventListener(eventSampler);
    }

    public void machineAdded(Machine machine) {
        System.out.println("Aharon  Machine Added [" + machine.getUid() + "]");
    }

    public void machineRemoved(Machine machine) {
        System.out.println("Aharon  Machine Removed [" + machine.getUid() + "]");
    }

    public void processingUnitAdded(ProcessingUnit processingUnit) {
        System.out.println("Aharon  Processing Unit Added [" + processingUnit.getName() + "]");
    }

    public void processingUnitRemoved(ProcessingUnit processingUnit) {
        System.out.println("Aharon  Processing Unit Removed [" + processingUnit.getName() + "]");
    }

    public void processingUnitInstanceAdded(ProcessingUnitInstance processingUnitInstance) {
        System.out.println("Aharon  Processing Unit Instance Added [" + processingUnitInstance.getClusterInfo() + "]");
    }

    public void processingUnitInstanceRemoved(ProcessingUnitInstance processingUnitInstance) {
        System.out.println("Aharon  Processing Unit Instance Removed [" + processingUnitInstance.getClusterInfo() + "]");
    }

    public void gridServiceManagerAdded(GridServiceManager gridServiceManager) {
        System.out.println("Aharon  GSM Added [" + gridServiceManager.getUid() + "]");
    }

    public void gridServiceManagerRemoved(GridServiceManager gridServiceManager) {
        System.out.println("Aharon  GSM Removed [" + gridServiceManager.getUid() + "]");
    }

    public void gridServiceContainerAdded(GridServiceContainer gridServiceContainer) {

        System.out.println("Aharon  waiting to be closed");
        try {
            Thread.currentThread().wait();
        }catch(InterruptedException e){
            System.out.println("Aharon  thread was interapted");
            e.printStackTrace();
        }

        System.out.println("Aharon  GSC Added [" + gridServiceContainer.getUid() + "]");
    }

    public void gridServiceContainerRemoved(GridServiceContainer gridServiceContainer) {
        System.out.println("Aharon  GSC Removed [" + gridServiceContainer.getUid() + "]");
    }

    public void lookupServiceAdded(LookupService lookupService) {
        System.out.println("Aharon  LUS Added [" + lookupService.getUid() + "]");
    }

    public void lookupServiceRemoved(LookupService lookupService) {
        System.out.println("Aharon  LUS Removed [" + lookupService.getUid() + "]");
    }

    public void virtualMachineAdded(VirtualMachine virtualMachine) {
        System.out.println("Aharon  VM Added [" + virtualMachine.getUid() + "]");
    }

    public void virtualMachineRemoved(VirtualMachine virtualMachine) {
        System.out.println("Aharon  VM Removed [" + virtualMachine.getUid() + "]");
    }

    public void processingUnitStatusChanged(ProcessingUnitStatusChangedEvent event) {
        System.out.println("Aharon  PU [" + event.getProcessingUnit().getName() + "] Status changed from [" + event.getPreviousStatus() + "] to [" + event.getNewStatus() + "]");
    }

    public void processingUnitManagingGridServiceManagerChanged(ManagingGridServiceManagerChangedEvent event) {
        if (event.isUnknown()) {
            System.out.println("Aharon  Processing Unit [" + event.getProcessingUnit().getName() + "] managing GSM UNKNOWN");
        } else {
            System.out.println("Aharon  Processing Unit [" + event.getProcessingUnit().getName() + "] new managing GSM [" + event.getNewGridServiceManager().getUid() + "]");
        }
    }

    public void processingUnitBackupGridServiceManagerChanged(BackupGridServiceManagerChangedEvent event) {
        System.out.println("Aharon  Processing Unit [" + event.getProcessingUnit().getName() + "] Backup GSM [" + event.getType() + "] with uid [" + event.getGridServiceManager().getUid() + "]");
    }

    public void spaceAdded(Space space) {
        System.out.println("Aharon  Space Added [" + space.getUid() + "]");
    }

    public void spaceRemoved(Space space) {
        System.out.println("Aharon  Space Removed [" + space.getUid() + "]");
    }

    public void spaceInstanceAdded(SpaceInstance spaceInstance) {
        System.out.println("Aharon  Space Instance Added [" + spaceInstance.getUid() + "]");
    }

    public void spaceInstanceRemoved(SpaceInstance spaceInstance) {
        System.out.println("Aharon  Space Instance Removed [" + spaceInstance.getUid() + "]");
    }

    public void spaceModeChanged(SpaceModeChangedEvent event) {
        System.out.println("Aharon  Space Instance [" + event.getSpaceInstance().getUid() + "] changed mode from [" + event.getPreviousMode() + "] to [" + event.getNewMode() + "]");

        if (event.getNewMode().equals(SpaceMode.PRIMARY))
            System.out.println("Aharon  Space Instance [" + event.getSpaceInstance().getUid() + "] is primary");
    }

    public void replicationStatusChanged(ReplicationStatusChangedEvent event) {
        System.out.println("Aharon  Space Instance [" + event.getSpaceInstance().getUid() + "] replication status changed from [" + event.getPreviousStatus() + "] to [" + event.getNewStatus() + "]");
    }

    public void zoneAdded(Zone zone) {
        System.out.println("Aharon  Zone Added [" + zone.getName() + "]");
    }

    public void zoneRemoved(Zone zone) {
        System.out.println("Aharon  Zone Removed [" + zone.getName() + "]");
    }

    public void elasticServiceManagerAdded(ElasticServiceManager elasticServiceManager) {
        System.out.println("Aharon  ESM Added [" + elasticServiceManager.getUid() + "]");
    }

    public void elasticServiceManagerRemoved(ElasticServiceManager elasticServiceManager) {
        System.out.println("Aharon  ESM Removed [" + elasticServiceManager.getUid() + "]");
    }
}
