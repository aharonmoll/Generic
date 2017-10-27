package gigaspaces.clusterinfo;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.cluster.ClusterInfo;
import org.openspaces.core.cluster.ClusterInfoContext;
import org.openspaces.core.context.GigaSpaceContext;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * Created by aharon on 9/11/15.
 */
public class MyBean {

    @ClusterInfoContext
    private ClusterInfo clusterInfo;
    @GigaSpaceContext
    GigaSpace gigaSpace;

    Logger log= Logger.getLogger(this.getClass().getName());


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
