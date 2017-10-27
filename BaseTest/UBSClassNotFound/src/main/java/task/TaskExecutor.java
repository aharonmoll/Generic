package task;

import com.gigaspaces.async.AsyncFuture;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.cluster.ClusterInfo;
import org.openspaces.core.cluster.ClusterInfoAware;
import org.openspaces.core.space.mode.PostPrimary;

import javax.annotation.PostConstruct;

/**
 * Execute remote task
 */
public class TaskExecutor  {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("TaskExecutor");

    private GigaSpace space;

    private int branch = 0;

    public TaskExecutor(int branch) {
        this.branch = branch;
    }

    @PostConstruct
    public void executeTask() {
        logger.info("Executing task on space");
        try {
            AsyncFuture<String> obj = space.execute(new DistTaskNotLoadingAllClasses(branch));
            obj.get();
            logger.info("Execution successful");
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.INFO, "Failed to execute task.", ex);
        }
    }

    public void setSpace(GigaSpace space) {
        this.space = space;
    }

}
