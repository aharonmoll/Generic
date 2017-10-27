package custom;

import com.gigaspaces.metrics.ServiceMetric;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by aharon on 5/28/15.
 */
public class FooService implements InitializingBean {

    private final AtomicInteger processedRequests = new AtomicInteger();

    public void processRequest() {
        // TODO: Process request
        processedRequests.incrementAndGet();
    }

    @ServiceMetric(name="foo-requests")
    public int getProcessedRequests() {
        return processedRequests.get();
    }

    public void afterPropertiesSet() throws Exception {
        for (int i=0;i<100;i++) {
            processRequest();
            //Thread.sleep(1000);
        }
    }


}