package gigaspaces.task;

import com.gigaspaces.async.AsyncFuture;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import java.util.concurrent.ExecutionException;

/**
 * Created by aharon on 2/16/15.
 */
public class TaskExecutionMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Start client");
        GigaSpace gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer("jini://localhost/*/mySpace?groups=aharon")).gigaSpace();
        System.out.println("got connection, executing task ...");
        AsyncFuture<Integer> future = gigaSpace.execute(new MyTask(333), 1);
        int result = future.get();
        System.out.println("Result is: "+ result);
    }
}
