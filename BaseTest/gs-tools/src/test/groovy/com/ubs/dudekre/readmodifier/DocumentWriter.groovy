package com.ubs.dudekre.readmodifier

import com.gigaspaces.document.SpaceDocument
import org.junit.Test
import org.junit.runner.RunWith
import org.openspaces.core.GigaSpace
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.TransactionCallback
import org.springframework.transaction.support.TransactionTemplate

import static com.ubs.ops.tess.document.DocumentBuilder.rootDocument

@RunWith (SpringJUnit4ClassRunner)
@ContextConfiguration ('classpath:/com/ubs/dudekre/space-context.xml')
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
                myLocalGigaSpace.write(rootDocument(DOC_TYPE, 'id_1').build())
                myLocalGigaSpace.write(rootDocument(DOC_TYPE, 'id_2').build())
            }
        })
    }

    @Test
    public void writePairOfDocumentsTransactionaly() {
        while (true) {
            transaction.execute(new TransactionCallback() {
                @Override
                Object doInTransaction(TransactionStatus status) {
                    myLocalGigaSpace.write(rootDocument(DOC_TYPE, UUID.randomUUID().toString()).build())
                    myLocalGigaSpace.write(rootDocument(DOC_TYPE, UUID.randomUUID().toString()).build())
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
                        [rootDocument(DOC_TYPE, UUID.randomUUID().toString()).build(),
                         rootDocument(DOC_TYPE, UUID.randomUUID().toString()).build()].toArray(new SpaceDocument[2])
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

    private static SpaceDocument[] getPairOfDocuments() {
        [rootDocument(DOC_TYPE, UUID.randomUUID().toString()).build(), rootDocument(DOC_TYPE, UUID.randomUUID().toString()).build()]

    }
}
