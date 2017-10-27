package gs.filter;

import com.gigaspaces.document.SpaceDocument;
import com.j_spaces.core.IJSpace;
import com.j_spaces.core.SpaceContext;
import com.j_spaces.core.client.SQLQuery;
import com.j_spaces.core.filters.FilterOperationCodes;
import com.j_spaces.core.filters.ISpaceFilter;
import com.j_spaces.core.filters.entry.ISpaceFilterEntry;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aharon on 8/18/16.
 */
public class SimpleFilter implements ISpaceFilter {

    GigaSpace  gigaSpace;
    ExecutorService executor;


    public void init(IJSpace ijSpace, String s, String s1, int i) throws RuntimeException {

        gigaSpace = new GigaSpaceConfigurer(ijSpace).create();
        System.out.printf("I'm here HHHHHHHHHHHH");
        this.executor = Executors.newFixedThreadPool(10);
//        SpaceTypeDescriptor tradeDescriptor = new SpaceTypeDescriptorBuilder("Trade")
//                .idProperty("Id").routingProperty("aggId")
//                .addPropertyIndex("date", SpaceIndexType.BASIC)
//                .addPropertyIndex("version",SpaceIndexType.BASIC).create();
//
//        gigaSpace.getTypeManager().registerTypeDescriptor(tradeDescriptor);
    }

    public void process(SpaceContext spaceContext, ISpaceFilterEntry iSpaceFilterEntry, int operationCode) throws RuntimeException {

        if (operationCode== FilterOperationCodes.AFTER_WRITE)
        {
            System.out.println("I'm in the after write method DDDDDDD");
            System.out.println(gigaSpace.readMultiple(new SpaceDocument("Trade")).length);
            //SQLQuery<Trade> tradeSQLQuery = new SQLQuery<Trade>(Trade.class,"latest = true AND aggId = "+iSpaceFilterEntry.getFieldValue("aggId")+" AND id != "+iSpaceFilterEntry.getFieldValue("id"));
//latest            SQLQuery<SpaceDocument> tradeSQLQuery = new SQLQuery<SpaceDocument>("Trade","latest = true AND aggId = "+iSpaceFilterEntry.getFieldValue("aggId")+" AND id != "+iSpaceFilterEntry.getFieldValue("id"));
//            SQLQuery<SpaceDocument> tradeSQLQuery = new SQLQuery<SpaceDocument>("Trade","latest = true AND aggId = ? AND id != ?",iSpaceFilterEntry.getFieldValue("aggId"), iSpaceFilterEntry.getFieldValue("id"));
            SQLQuery<SpaceDocument> tradeSQLQuery = new SQLQuery<SpaceDocument>("Trade","latest = true AND aggId = ? ",iSpaceFilterEntry.getFieldValue("aggId"));
//            for (SpaceDocument spaceDocument : gigaSpace.readMultiple(tradeSQLQuery)) {
//                System.out.println(spaceDocument.toString());
//            }
            Runnable worker = new LatestWorkerThread(tradeSQLQuery,gigaSpace,Integer.valueOf(iSpaceFilterEntry.getFieldValue("aggId").toString()),Integer.valueOf(iSpaceFilterEntry.getFieldValue("id").toString()));
            executor.execute(worker);
            //gigaSpace.change(tradeSQLQuery,new ChangeSet().set("latest",false));

        }

    }

    public void process(SpaceContext spaceContext, ISpaceFilterEntry[] iSpaceFilterEntries, int i) throws RuntimeException {

    }

    public void close() throws RuntimeException {
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}
