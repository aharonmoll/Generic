package gs.example;

import net.jini.lease.LeaseListener;
import net.jini.lease.LeaseRenewalEvent;
import org.openspaces.core.GigaSpace;
import org.openspaces.events.notify.SimpleNotifyEventListenerContainer;

/**
 * Created by aharon on 3/30/17.
 */
public class MyLeaseListener implements LeaseListener {

    GigaSpace space ;

    public MyLeaseListener (GigaSpace space) {
        this.space = space;
    }

    // Called when the event registration's lease cannot be renewed
    public void notify(LeaseRenewalEvent event) {
        System.out.println("Can't renew - try to re-register");
        SimpleNotifyEventListenerContainer container = null;
        while (container == null) {
            NotifyHAMain.getSpace();
            container = NotifyHAMain.registerSlowConsumer(this);
            container = NotifyHAMain.registerFastConsumer(this);
        }
        System.out.println("Notify ReRegistration Done!");
    }
}