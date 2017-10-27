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
public class NotifyWithNoLease {

    @Resource
    GigaSpace gigaSpace;

    static GigaSpace space;


    @PostConstruct
    void initMethod()
    {
        System.out.println("initMethod ------------------------ initMethod");
        space = gigaSpace;
        registerNotification();
    }

     static SimpleNotifyEventListenerContainer registerNotification()
    {
        System.out.println("Initializing notify container BBBBBBBBBBBBBB");
        return new SimpleNotifyContainerConfigurer(space)
                .template(new Data(false))
                .eventListenerAnnotation(new Object() {
                    @SpaceDataEvent
                    public void eventHappened(Data data) throws InterruptedException {
                        System.out.println("Got notification HHHHHHHHH: " + data.getClass().getName() + "id=" + data.getId());
                    }
                }).notifyContainer();
    }
}
