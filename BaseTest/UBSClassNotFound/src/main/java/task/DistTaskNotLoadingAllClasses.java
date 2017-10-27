package task;

import com.gigaspaces.async.AsyncResult;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.SupportCodeChange;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by aharon on 10/10/16.
 */
@SupportCodeChange
public class DistTaskNotLoadingAllClasses implements DistributedTask<String, String> {
    private static final Logger logger =
            Logger.getLogger(DistTaskNotLoadingAllClasses.class.getName());
    private final int branch;

    public DistTaskNotLoadingAllClasses(int branch) {
        this.branch = branch;
    }

    @Override
    public String execute() throws Exception {
        switch (branch) {
            case 1:

                logger.info("creating instance of " + new Class1());
                return "branch" + branch;
            case 2:
                logger.info("creating instance of " + new Class2());
                return "branch " + branch;
            default:
                throw new RuntimeException("unsupported branch=$branch");
        }
    }

    public String reduce(List<AsyncResult<String>> list) throws Exception {
        return null;
    }
}
