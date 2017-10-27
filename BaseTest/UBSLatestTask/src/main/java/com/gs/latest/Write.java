package com.gs.latest;

/**
 * Created by aharon on 9/5/16.
 */
import com.gigaspaces.client.WriteModifiers;
import com.gigaspaces.document.SpaceDocument;
import com.j_spaces.core.LeaseContext;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.executor.AutowireTask;
import org.openspaces.core.executor.Task;
import org.openspaces.core.executor.TaskGigaSpace;
import org.openspaces.core.transaction.TransactionProvider;
import org.openspaces.core.transaction.manager.DistributedJiniTxManagerConfigurer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Created by aharon on 9/3/15.
 */

@AutowireTask
public class Write implements Task<Serializable> {

    private SpaceDocument spaceDocument;

    @TaskGigaSpace
    private transient GigaSpace gigaSpace;

    public Write(SpaceDocument spaceDocument) {
        this.spaceDocument = spaceDocument;
    }

    @Transactional
    public Serializable execute() throws Exception {

//        throw (new Exception("Aharon is the man"));
        System.out.println("Transaction "+gigaSpace.getCurrentTransaction());
        PlatformTransactionManager ptm = null;
        if (gigaSpace.getCurrentTransaction() == null) {
            ptm = new DistributedJiniTxManagerConfigurer().transactionManager();
            gigaSpace = new GigaSpaceConfigurer(gigaSpace.getSpace()).transactionManager(ptm).create();
        }
        TransactionStatus status=null;
        if (ptm!=null)
        {
            status = ptm.getTransaction(new DefaultTransactionDefinition());
        }
        System.out.println("Transaction "+gigaSpace.getCurrentTransaction());
        gigaSpace.write(this.spaceDocument);
        Integer aggregateId = spaceDocument.getProperty("aggregateId");
        Integer uniqueId = spaceDocument.getProperty("uniqueId");
        gigaSpace.write(new LatestPointer(aggregateId,uniqueId));
        if (status!=null)
            ptm.commit(status);
        return null;
    }
}