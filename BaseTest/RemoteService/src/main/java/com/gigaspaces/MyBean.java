package com.gigaspaces;

import org.openspaces.core.cluster.ClusterInfo;
import org.openspaces.core.cluster.ClusterInfoContext;
import org.openspaces.core.space.mode.PostPrimary;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * Created by aharon on 9/1/15.
 */
public class MyBean {

    @ClusterInfoContext
    private ClusterInfo clusterInfo;

    Logger log= Logger.getLogger(this.getClass().getName());

    @PostPrimary
    public void postPrimary() {
        log.info("******************* postPrimary *****************");
    }


    @PostConstruct
    void init()
    {
        log.info("--- Cluster Info ------ ");
        log.info("cluster info name= "+ clusterInfo.getName());
        log.info("instance id= "+clusterInfo.getInstanceId());
        log.info("number of instances= "+ clusterInfo.getNumberOfInstances());
        log.info("backup id= "+clusterInfo.getBackupId());
    }
}

