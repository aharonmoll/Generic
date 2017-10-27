package simple;

import com.gigaspaces.cluster.activeelection.SpaceMode;
import data.Person;
import data.Person2;
import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsa.GridServiceAgent;
import org.openspaces.admin.gsa.GridServiceAgents;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.gsm.GridServiceManagers;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.pu.ProcessingUnits;
import org.openspaces.admin.space.Space;
import org.openspaces.admin.space.SpaceInstance;
import org.openspaces.admin.space.SpaceInstanceRuntimeDetails;
import org.openspaces.admin.space.SpaceRuntimeDetails;
import org.openspaces.admin.space.events.SpaceModeChangedEvent;
import org.openspaces.admin.space.events.SpaceModeChangedEventListener;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by aharon on 6/24/15.
 */
public class SimpleTest {


    public static void main(String[] args) throws Exception {
        //UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://localhost/*/mySpace?groups=aharon");
        SpaceProxyConfigurer configurer = new SpaceProxyConfigurer("mySpace").lookupGroups("aharon");
       /* GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();
        for (int i = 0;i < 10; i++)
        {
            Person person = new Person(String.valueOf(i));
            //if (i == 5) {
            //    gigaSpace.write(person, 1000);
            //} else {
            System.out.println("Squence number Person:" + person.getSequenceNumber());
            gigaSpace.write(person);
            //Thread.sleep(1000);
            //Person person2 = gigaSpace.read(person);
            //System.out.println("Squence number Person2:"+person2.getSequenceNumber());
            Person2 person2 = new Person2(String.valueOf(i));
            //if (i == 5) {
            //    gigaSpace.write(person, 1000);
            //} else {
            System.out.println("Squence number Person:" + person2.getSequenceNumber());
            gigaSpace.write(person2);
        }*/
        AdminFactory factory = new AdminFactory();
        factory.addGroup("aharon");
        //factory.addLocator("localhost");
        Admin admin = factory.createAdmin();
        /*if (admin.getGridServiceAgents().waitFor(1)) {
            for (GridServiceAgent gridServiceAgent : admin.getGridServiceAgents()) {
                gridServiceAgent.getProcessesDetails();
                System.out.println("Processing details: " + gridServiceAgent.getProcessesDetails());
            }
        }*/


        GridServiceAgents gridServiceAgents  = null;
        if (admin.getGridServiceAgents().waitFor(1))
        {
            gridServiceAgents = admin.getGridServiceAgents();
        }

        GridServiceManagers gridServiceManagers = null;

        if (admin.getGridServiceManagers().waitFor(1)) {
            for (GridServiceManager gridServiceManager : admin.getGridServiceManagers()) {
                int i = gridServiceManager.getAgentId();
                //gridServiceAgents.getAgents()[0].killByAgentId(i);
                if(gridServiceAgents.getAgents()[0].getGridServiceManagers().waitFor(1))
                {
                    gridServiceManagers = gridServiceAgents.getAgents()[0].getGridServiceManagers();
                    gridServiceManagers.getManagers()[0].kill();
                }

                //gridServiceAgents.getAgents()[0].shutdown();
            }
        }


        Space space = admin.getSpaces().waitFor("mySpace", 10, TimeUnit.SECONDS);

        space.getSpaceModeChanged().add(new SpaceModeChangedEventListener() {
            public void spaceModeChanged(SpaceModeChangedEvent event) {

                SpaceMode newMode = event.getNewMode();

                if( newMode == SpaceMode.BACKUP ){
                    SpaceInstance spaceInstance = event.getSpaceInstance();
                    spaceInstance.getRuntimeDetails().getCountPerClassName();
                }
            }
        });
        SpaceInstance[] spaceInstances = space.getInstances();

        for (int i=0;i<spaceInstances.length;i++)
            if (spaceInstances[i].waitForMode(SpaceMode.BACKUP, 5, TimeUnit.SECONDS))
            {
                SpaceInstance backupSpaceInstance = spaceInstances[i];
                SpaceInstanceRuntimeDetails spaceInstanceRuntimeDetails = backupSpaceInstance.getRuntimeDetails();
                Map<String, Integer> map = spaceInstanceRuntimeDetails.getCountPerClassName();
                for (Map.Entry<String, Integer> entry : map.entrySet())
                {
                    System.out.println(entry.getKey() + "/" + entry.getValue());
                }
            }
        admin.close();
    }
}
