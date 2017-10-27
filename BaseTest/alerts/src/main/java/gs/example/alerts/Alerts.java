package gs.example.alerts;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminEventListener;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.alert.AlertManager;
import org.openspaces.admin.alert.config.parser.XmlAlertConfigurationParser;
import org.openspaces.admin.pu.events.ProcessingUnitInstanceLifecycleEventListener;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * Created by aharon on 4/10/16.
 */
public class Alerts {

    private static final Logger logger = Logger.getLogger(Alerts.class.getName());


    @PostConstruct
    public void process() throws InterruptedException {

        Admin admin = new AdminFactory().createAdmin();

        AlertManager alertManager = admin.getAlertManager();

        alertManager.configure(new XmlAlertConfigurationParser("alerts.xml").parse());

        alertManager.getAlertTriggered().add(alert -> {
            //System.out.println(alert);
            logger.info(alert.toString());
        });

        /*ProcessingUnitInstanceLifecycleEventListener processingUnitInstanceLifecycleEventListener = new Listener();
        admin.getProcessingUnits().addLifecycleListener(processingUnitInstanceLifecycleEventListener);
*/
        AdminEventListener adminEventListener = new GenericListener();
        admin.addEventListener(adminEventListener);
    }
}
