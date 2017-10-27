package com.gigaspaces;

import com.gigaspaces.async.AsyncResult;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.TaskGigaSpace;

import java.util.List;

/**
* Created by ronz on 6/24/2015.
*/
public class MyDistTask implements DistributedTask<Integer, Long> {

    @TaskGigaSpace
    private transient GigaSpace gigaSpace;

    public Integer execute() throws Exception {
        int sum = 0;
        sum += gigaSpace.count(new Object());
        return sum;
    }

    public Long reduce(List<AsyncResult<Integer>> results) throws Exception {
        long sum = 0;
        for (AsyncResult<Integer> result : results) {
            if (result.getException() != null) {
                throw result.getException();
            }
            sum += result.getResult();
        }
        return sum;
    }
}
