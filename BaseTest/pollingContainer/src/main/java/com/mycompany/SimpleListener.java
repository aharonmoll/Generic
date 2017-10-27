package com.mycompany;

/**
 * Created by aharon on 7/30/15.
 */

import com.mycompany.data.Data;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;

/**
 * Created by aharon on 7/30/15.
 */
@EventDriven
@Polling (receiveTimeout = 30000)
class SimpleListener {

    @EventTemplate
    Data unprocessedData() {
        System.out.println("HHHHHHHHH EventTemplate HHHHHHH");
        Data template = new Data("1","1",false);
        //template.setProcessed(false);
        return template;
    }

    @SpaceDataEvent
    public Data eventListener(Data event) {
        //process Data here
        System.out.println("HHHHHHHHH SpaceDataEvent HHHHHHH");
        if (!event.getProcessed())
            event.setProcessed(true);
        return event;
    }
}