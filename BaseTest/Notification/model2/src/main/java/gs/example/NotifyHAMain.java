package gs.example;

import gs.example.data.Data2;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.SimpleNotifyContainerConfigurer;
import org.openspaces.events.notify.SimpleNotifyEventListenerContainer;

/**
 * Created by aharon on 3/30/17.
 */
public class NotifyHAMain {

    static GigaSpace space;

    public static void main(String[] args) throws Exception {

        while (space == null) {
            getSpace();
        }

        MyLeaseListener leaseListener = new MyLeaseListener(space);
        registerSlowConsumer(leaseListener);
        registerFastConsumer(leaseListener);
        System.out.println("Notification Registration Done!");
        while (true) {
            Thread.sleep(10000);
        }
    }

    static void getSpace() {
        try {
            space = new GigaSpaceConfigurer(new SpaceProxyConfigurer("mySpace").lookupGroups("aharon")).create();
        } catch (Exception e) {
            System.out.println("Cannot find space: " + e.getMessage());
        }
    }

    static SimpleNotifyEventListenerContainer registerSlowConsumer(MyLeaseListener leaseListener) {
        return new SimpleNotifyContainerConfigurer(space)
                .template(new Data2(false))
                .leaseListener(leaseListener)
//                .autoRenew(true)
                .eventListenerAnnotation(new Object() {
                    @SpaceDataEvent
                    public void eventHappened(Data2 data) throws InterruptedException {
                        System.out.println("Got notification Slow Consumer: " + data.getClass().getName() +"id="+data.getId());
                        Thread.sleep(10000);
                    }
                }).durable(true).batchSize(5).batchTime(10)
                .notifyContainer();
    }

    static SimpleNotifyEventListenerContainer registerFastConsumer(MyLeaseListener leaseListener) {
        return new SimpleNotifyContainerConfigurer(space)
                .template(new Data2(false))
                .leaseListener(leaseListener)
//                .autoRenew(true)
                .eventListenerAnnotation(new Object() {
                    @SpaceDataEvent
                    public void eventHappened(Data2 data) throws InterruptedException {
                        System.out.println("Got notification Fast Consumer: " + data.getClass().getName() +"id="+data.getId());
                    }
                }).durable(true).batchSize(5).batchTime(10)
                .notifyContainer();
    }
}