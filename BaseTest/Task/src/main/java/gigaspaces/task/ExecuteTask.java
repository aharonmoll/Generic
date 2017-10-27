package gigaspaces.task;

import com.gigaspaces.async.AsyncFuture;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

/**
 * Created by aharon on 9/3/15.
 */
public class ExecuteTask {

    public static void main(String[] args) throws Exception {
        SpaceProxyConfigurer configurer = new SpaceProxyConfigurer("space").lookupLocators("localhost");
        GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();
        // Send Task to the proxy
        for (int i=0;i<10;i++) {
            Employee employee = new Employee().setId(i);
            AsyncFuture<Boolean> future = gigaSpace.execute(new RemoteTask(i), employee);
            boolean b = future.get();
            System.out.println("Task result: "+b);
        }
    }
}
