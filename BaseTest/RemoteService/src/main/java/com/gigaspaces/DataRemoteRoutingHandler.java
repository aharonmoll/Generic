package com.gigaspaces;

import org.openspaces.remoting.RemoteRoutingHandler;
import org.openspaces.remoting.SpaceRemotingInvocation;

/**
 * Created by Aharon on 12-Feb-15.
 */
public class DataRemoteRoutingHandler implements RemoteRoutingHandler {

    public DataRemoteRoutingHandler(){

    }

    public Long computeRouting(SpaceRemotingInvocation remotingEntry) {
        if (remotingEntry.getMethodName().equals("processData")) {
            Data data = (Data) remotingEntry.getArguments()[0];
            return data.getType();
        }
        return null;
    }
}
