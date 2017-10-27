package com.gigaspaces;

import com.j_spaces.core.IJSpace;
import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.openspaces.remoting.ExecutorRemotingProxyConfigurer;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Aharon on 03-Feb-15.
 */
public class Main {;

    public static void main(String args[]) {

        IJSpace ispace = new UrlSpaceConfigurer("jini://*/*/" + "mySpace")
                .lookupGroups("aharon")
                .lookupLocators("aharon-mbp.local:4174")
                .space();

        GigaSpace gigaSpace = new GigaSpaceConfigurer(ispace).gigaSpace();

        Data myData = new Data();
        myData.setId("2");
        myData.setType(new Long(2));
        Address address = new Address();
        address.setProperty("street", "Moshe");
        address.setProperty("city", "Kfar Saba");
        myData.setExtendedProperties(address);

//        IDataProcessor dataProcessor = new ExecutorRemotingProxyConfigurer<IDataProcessor>(gigaSpace, IDataProcessor.class)
//                .remoteRoutingHandler(new DataRemoteRoutingHandler())
//                .proxy();
        IDataProcessor dataProcessor = new ExecutorRemotingProxyConfigurer<IDataProcessor>(gigaSpace, IDataProcessor.class).proxy();

//        DataRemoting dataRemoting = new DataRemoting();
//        dataRemoting.setDataProcessor(dataProcessor);

        dataProcessor.processData(myData);
        SQLQuery<Data> sqlQuery = new SQLQuery<Data>(Data.class, "id=2");
        Data myEntry = gigaSpace.read(sqlQuery);
        System.out.println(myEntry.getExtendedProperties().get("street"));
    }
}
