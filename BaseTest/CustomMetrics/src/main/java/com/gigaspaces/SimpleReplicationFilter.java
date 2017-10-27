package com.gigaspaces;

import com.gigaspaces.lrmi.LRMIManager;
import com.gigaspaces.management.GigaSpacesRuntime;
import com.j_spaces.core.IJSpace;
import com.j_spaces.core.cluster.IReplicationFilter;
import com.j_spaces.core.cluster.IReplicationFilterEntry;
import com.j_spaces.core.cluster.ReplicationPolicy;
import com.j_spaces.kernel.SystemProperties;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceLateContext;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by aharon on 3/25/15.
 */
public class SimpleReplicationFilter implements IReplicationFilter {


    //private GigaSpace gigaSpace;
    public void init(IJSpace space, String paramUrl, ReplicationPolicy replicationPolicy) {
        // init logic here
    }

    public void process(int direction, IReplicationFilterEntry replicationFilterEntry, String remoteSpaceMemberName) {
        // process logic here
        System.out.println("OutGoing OOOOOOOOOOO "+direction +" Object "+ replicationFilterEntry.getUID());
        System.out.println("remoteSpaceMemberName "+ remoteSpaceMemberName);
        //throw new OutOfMemoryError("OOM HHHHHHHHHHHHHHH");
        //throw new RuntimeException("THIS IS MEEEEEEEEEE");
        //UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/mySpace_container1_1/mySpace?groups=aharon");
        //System.out.println("NAME " + configurer.space().getName());

        //System.out.println("NNNNNNNNNNNNN "+gigaSpace.getName());
        //System.out.println(gigaSpace.getSpace().getName());
        //System.out.println(gigaSpace.getSpace().getFinderURL().toString());
        //LRMIManager.shutdown();
        GigaSpacesRuntime.shutdown();
        //ijSpace.getDirectProxy().close();;
        //configurer.close();

        //System.exit(0);
    }

    public void close() {
        // close logic here
    }

}