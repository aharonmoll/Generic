package readmodifier

import com.gigaspaces.document.SpaceDocument
import org.junit.Test
import org.junit.runner.RunWith
import org.openspaces.core.GigaSpace
import org.openspaces.core.GigaSpaceConfigurer
import org.openspaces.core.space.UrlSpaceConfigurer
import org.openspaces.core.transaction.manager.DistributedJiniTxManagerConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.DefaultTransactionDefinition
import org.springframework.transaction.support.TransactionCallback
import org.springframework.transaction.support.TransactionTemplate

@RunWith (SpringJUnit4ClassRunner)
@ContextConfiguration ('classpath:/readmodifier/space-context.xml')
class DocumentWriter {
    private static final String DOC_TYPE = 'ic.TransactionExpected'
    private static final String UNIQUE_ID = 'uniqueId'
    @Autowired GigaSpace myLocalGigaSpace
    @Autowired TransactionTemplate transaction

    @Test
    public void cleanUpSpace() {
        myLocalGigaSpace.clear(new SpaceDocument())
        println myLocalGigaSpace.count(new SpaceDocument())
    }

    @Test
    public void writeDocumentsTransactionaly() {
        transaction.execute(new TransactionCallback() {
            @Override
            Object doInTransaction(TransactionStatus status) {
                myLocalGigaSpace.write(new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID,'id_1'))
                myLocalGigaSpace.write(new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID,'id_2'))
            }
        })
    }

    @Test
    public void writePairOfDocumentsTransactionaly() {
        while (true) {
            transaction.execute(new TransactionCallback() {
                @Override
                Object doInTransaction(TransactionStatus status) {
                    myLocalGigaSpace.write(new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID, UUID.randomUUID().toString()))
                    myLocalGigaSpace.write(new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID, UUID.randomUUID().toString()))
                }
            })
        }
    }

    @Test
    public void multipleWritePairOfDocumentsTransactionaly() {
        while (true) {
            transaction.execute(new TransactionCallback() {
                @Override
                Object doInTransaction(TransactionStatus status) {
                    myLocalGigaSpace.writeMultiple(
                        [new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID, UUID.randomUUID().toString()),
                         new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID, UUID.randomUUID().toString())].toArray(new SpaceDocument[2])
                    )
                }
            })
        }
    }

    @Test
    public void clearDocumentsTransactionaly() {
        transaction.execute(new TransactionCallback() {
            @Override
            Object doInTransaction(TransactionStatus status) {
                myLocalGigaSpace.take(new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID, 'id_1'))
                myLocalGigaSpace.take(new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID, 'id_2'))
            }
        })
    }

    @Test
    public void AharonWriteDocumentsTransactionaly() {

        UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/myLocalSpace?locators=localhost");
        PlatformTransactionManager ptm = new DistributedJiniTxManagerConfigurer().transactionManager();
        GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).transactionManager(ptm).gigaSpace();
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = ptm.getTransaction(definition);
        gigaSpace.write(new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID, 'id_1'))
        gigaSpace.write(new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID, 'id_2'))
        ptm.commit(status);
    }

    @Test
    public void AharonWritePojosTransactionaly() {

        UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/myLocalSpace?locators=localhost");
        PlatformTransactionManager ptm = new DistributedJiniTxManagerConfigurer().transactionManager();
        GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).transactionManager(ptm).gigaSpace();
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = ptm.getTransaction(definition);
//        myLocalGigaSpace.write(new Person('id_1'));
//        myLocalGigaSpace.write(new Person('id_2'));
        gigaSpace.write(new Person('id_1'));
        gigaSpace.write(new Person('id_2'));
        ptm.commit(status);
    }

    @Test
    public void AharonWritePairOfDocumentsTransactionaly() {
        while (true) {
            UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/myLocalSpace?locators=localhost");
            PlatformTransactionManager ptm = new DistributedJiniTxManagerConfigurer().transactionManager();
            GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).transactionManager(ptm).gigaSpace();
            DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
            TransactionStatus status = ptm.getTransaction(definition);
            gigaSpace.write(new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID, UUID.randomUUID().toString()))
            gigaSpace.write(new SpaceDocument(DOC_TYPE).setProperty(UNIQUE_ID, UUID.randomUUID().toString()))
            ptm.commit(status);
        }
    }

}
