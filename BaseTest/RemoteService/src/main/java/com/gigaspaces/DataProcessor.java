package com.gigaspaces;

import org.openspaces.core.GigaSpace;
import org.openspaces.remoting.RemotingService;
import org.openspaces.remoting.Routing;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * Created by Aharon on 05-Feb-15.
 */
@RemotingService
public class DataProcessor implements IDataProcessor {

    @Resource
    private GigaSpace gigaSpace;

//    public Data processData(Data data1,Data myData) {
//        System.out.println("inside processData method");
//        myData.setProcessed(true);
//        gigaSpace.write(myData);
//        return myData;
//    }

//    public Data processData(Data data) {
//        return null;
//    }

    public Data processData(Data myData) {
        System.out.println("inside processData method");
        myData.setProcessed(true);
        gigaSpace.write(myData);
        return myData;
    }
}
