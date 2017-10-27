package gs.example;

import gs.example.data.Data;
import org.openspaces.core.GigaSpace;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.SimpleNotifyContainerConfigurer;
import org.openspaces.events.notify.SimpleNotifyEventListenerContainer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by aharon on 8/14/16.
 */
public class Notify {

    @Resource
    GigaSpace gigaSpace;

    static GigaSpace space;


    @PostConstruct
    void initMethod()
    {
        System.out.println("initMethod ------------------------ initMethod");
        space = gigaSpace;
        MyLeaseListenerBean leaseListener = new MyLeaseListenerBean();
        registerNotification(leaseListener);
    }

     static SimpleNotifyEventListenerContainer registerNotification(MyLeaseListenerBean leaseListener)
    {
        System.out.println("Initializing notify container BBBBBBBBBBBBBB");
        return new SimpleNotifyContainerConfigurer(space)
                .template(new Data("kuku"))
                .leaseListener(leaseListener)
                .autoRenew(true)
                .eventListenerAnnotation(new Object() {
                    @SpaceDataEvent
                    public void eventHappened(Data data) throws InterruptedException {
                        System.out.println("Got notification HHHHHHHHH: " + data.getClass().getName() + "id=" + data.getId());
                    }
                }).notifyContainer();
    }
}
