package gs.example;

import com.gigaspaces.cluster.activeelection.SpaceMode;
import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsa.GridServiceAgent;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.pu.ProcessingUnits;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * Created by aharon on 7/25/15.
 */
public class FindNumberOfPrimaries {


            //private static IProcessingUnit space;
            private static Admin admin;

            public static void  main(String[] args)
            {
                System.out.println("Starting...");
                String locators = "localhost";
                String puName = "mySpace";

                MonitorGrid(locators, puName);
            }

            private static void MonitorGrid(String locators, String puName)
            {
                AdminFactory factory = new AdminFactory();
                factory.addLocator(locators);
                admin = factory.createAdmin();

                ProcessingUnitInstance[] processingUnits = null;
                System.out.println("Waiting for "+ puName);
                ProcessingUnit processingUnit = admin.getProcessingUnits().waitFor(puName);
                for (GridServiceAgent gridServiceAgent : admin.getGridServiceAgents()) {
                    gridServiceAgent.shutdown();
                }
                ;

                System.out.println("Found " + processingUnit.getName());
                boolean instances = processingUnit.waitFor(processingUnit.getPlannedNumberOfInstances());
                if (instances) {
                    processingUnits = processingUnit.getInstances();
                }
                //*******************************************************************************************************
                System.out.println("Number of instances :" + processingUnits.length);


                int primaryCounter = 0;
                int backupCounter = 0;

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");

                for (int j = 0; j < processingUnits.length; j++)
                {
                    if (processingUnits[j].waitForSpaceInstance().waitForMode(SpaceMode.PRIMARY,5,TimeUnit.SECONDS)){
                        System.out.println("Time = " + sdf.format(Calendar.getInstance().getTime()) + " Space name = " + processingUnits[j].getSpaceInstance().getSpaceInstanceName());
                        primaryCounter++;
                    }
                    else if(processingUnits[j].waitForSpaceInstance().waitForMode(SpaceMode.BACKUP, 5, TimeUnit.SECONDS)) {
                        System.out.println("Time = " + sdf.format(Calendar.getInstance().getTime()) + " Space name = " + processingUnits[j].getSpaceInstance().getSpaceInstanceName());
                        backupCounter++;
                    }
                }

                System.out.println("Number of Primaries: "+primaryCounter);
                System.out.println("Number of Backups: " +backupCounter);

                admin.close();
            }

}
