package com.ubs.dudekre.readmodifier

import com.gigaspaces.client.CountModifiers
import com.gigaspaces.document.SpaceDocument
import org.junit.Test
import org.junit.runner.RunWith
import org.openspaces.core.GigaSpace
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static com.gigaspaces.client.ReadModifiers.DIRTY_READ
import static com.gigaspaces.client.ReadModifiers.READ_COMMITTED
import static com.gigaspaces.client.ReadModifiers.REPEATABLE_READ

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration('classpath:/com/ubs/dudekre/space-context.xml')
class DocumentReader {
    private static final String DOC_TYPE = 'ic.TransactionExpected'
    @Autowired GigaSpace myLocalGigaSpace

    @Test
    public void nonBlockingWithMultipleRead() {
        SpaceDocument[] multiple = myLocalGigaSpace.readMultiple(new SpaceDocument(DOC_TYPE), 9999999, REPEATABLE_READ)
        println multiple.length
    }

    private void printDocs(SpaceDocument[] multiple) {
        for (SpaceDocument doc : multiple) {
            println doc
        }
    }

    @Test
    public void readingPairsOfDocuments() {
        int threshold = 9999999;
        while(true) {
            SpaceDocument[] multiple = myLocalGigaSpace.readMultiple(new SpaceDocument(DOC_TYPE), 9999999, READ_COMMITTED)
            int responseSize = multiple.length
            if (responseSize == threshold) {
                exit 1
            } else if (responseSize % 2 != 0) {
                println "Bingo!! $responseSize"
            }
        }
    }

    @Test
    public void countingPairsOfDocuments() {
        while(true) {
            int multiple = myLocalGigaSpace.count(new SpaceDocument(DOC_TYPE), CountModifiers.READ_COMMITTED)
            if (multiple % 2 != 0) {
                println "Bingo!! $multiple"
            }
        }
    }

    @Test
    public void blockingWithRead() {
        def multiple = myLocalGigaSpace.read(new SpaceDocument(DOC_TYPE), 3_600_000, READ_COMMITTED)
        println multiple
    }
}
