package gs.example;

import net.jini.lease.LeaseListener;
import net.jini.lease.LeaseRenewalEvent;
import org.openspaces.events.notify.SimpleNotifyEventListenerContainer;

/**
 * Created by aharon on 3/30/17.
 */
public class MyLeaseListenerBean implements LeaseListener {

    // Called when the event registration's lease cannot be renewed
    public void notify(LeaseRenewalEvent event) {
        System.out.println("Can't renew - try to re-register");
        SimpleNotifyEventListenerContainer container = null;
        while (container == null) {
            try {
                container = Notify.registerNotification(this);
            } catch (Exception ex) {
                System.out.println("Unable to register notify container");
                try {
                    Thread.sleep(30000);   // 30 seconds
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
        System.out.println("Notify ReRegistration Done!");
    }
}