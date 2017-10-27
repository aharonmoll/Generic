package com.gs.latest;

import com.gigaspaces.document.SpaceDocument;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;
import org.openspaces.core.transaction.manager.DistributedJiniTxManagerConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by aharon on 9/5/16.
 */
public class LatestMain {
    public static void main(String[] args) throws Exception {

        // Create the Space
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/pu.xml");
        PlatformTransactionManager ptm = null;
        ptm = new DistributedJiniTxManagerConfigurer().transactionManager();
        GigaSpace space = new GigaSpaceConfigurer(new SpaceProxyConfigurer(
                "mySpace").lookupGroups("aharon").credentials("aharon", "1234")).transactionManager(ptm).gigaSpace();


        for (int i=0;i<100;i++)
        {
            TransactionStatus status=null;
            if (ptm!=null)
            {
                status = ptm.getTransaction(new DefaultTransactionDefinition());
            }
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("uniqueId", i);
            properties.put("aggregateId", i%10);
            properties.put("date", new Date());
            properties.put("latest",true);
            properties.put("version", i%3);
            properties.put("status", "open");

            SpaceDocument document = new SpaceDocument("Trade", properties);
            try {
                UbsUtils.write(space, document);
                if (status!=null)
                    ptm.commit(status);
            } catch (InterruptedException e) {
                e.printStackTrace();
                if (status!=null)
                    ptm.rollback(status);
            } catch (ExecutionException e) {
                e.printStackTrace();
                if (status!=null)
                    ptm.rollback(status);
            }
        }

        applicationContext.destroy();

        System.exit(1);
    }
}
