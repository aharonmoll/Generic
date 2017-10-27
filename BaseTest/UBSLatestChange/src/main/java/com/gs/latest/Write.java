package com.gs.latest;

/**
 * Created by aharon on 9/5/16.
 */
import com.gigaspaces.client.ChangeSet;
import com.gigaspaces.client.WriteModifiers;
import com.gigaspaces.document.SpaceDocument;
import com.j_spaces.core.LeaseContext;
import com.j_spaces.core.client.SQLQuery;
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
        Integer aggregateId = this.spaceDocument.getProperty("aggregateId");
        SQLQuery query = new SQLQuery<SpaceDocument>("Trade", "latest = true and aggregateId = "+aggregateId);
        //gigaSpace.change(query,new ChangeSet().set("latest",false).set("status","close"));
        gigaSpace.change(query, new ChangeSet().set("latest", false));
        //gigaSpace.change(query,new ChangeSet().increment("version",1));
        this.spaceDocument.setProperty("latest",true);
        gigaSpace.write(this.spaceDocument);
//        this.spaceDocument.setProperty("foo",true);
//        gigaSpace.write(this.spaceDocument);
        if (status!=null)
            ptm.commit(status);
        return null;
    }
}