package gs.example;

import gs.example.data.Data2;
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
        System.out.println("Initializing notify container CCCCCCCCCCCCCC");
        return new SimpleNotifyContainerConfigurer(space)
                .template(new Data2(false))
                .eventListenerAnnotation(new Object() {
                    @SpaceDataEvent
                    public void eventHappened(Data2 data) throws InterruptedException {
                        System.out.println("Got notification XXXXXXXXXXXX: " + data.getClass().getName() + "id=" + data.getId());
                    }
                }).notifyContainer();
    }
}
