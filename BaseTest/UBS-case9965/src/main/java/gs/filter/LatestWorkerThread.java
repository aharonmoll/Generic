package gs.filter;

import com.gigaspaces.client.ChangeSet;
import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.query.IdsQuery;
import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;

import java.util.Arrays;

/**
 * Created by aharon on 9/4/16.
 */
public class LatestWorkerThread implements Runnable {

    private SQLQuery<SpaceDocument> sqlQuery;
    GigaSpace gigaSpace;
    Integer id,aggId;

    public LatestWorkerThread(SQLQuery<SpaceDocument> sqlQuery, GigaSpace gigaSpace, Integer id, Integer aggId){
        this.sqlQuery=sqlQuery;
        this.gigaSpace=gigaSpace;
        this.id = id;
        this.aggId = aggId;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+sqlQuery);
        //processCommand(sqlQuery,gigaSpace);
        updateLatest();
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    private void processCommand(SQLQuery<SpaceDocument> sqlQuery, GigaSpace gigaSpace) {
        gigaSpace.change(sqlQuery, new ChangeSet().set("latest", false));
    }

    private void updateLatest()
    {
        System.out.println("updateLatest for id = "+this.id + " and aggId = "+this.aggId);
        SpaceDocument[] documents = gigaSpace.readMultiple(this.sqlQuery);
        Arrays.sort(documents,new TradesComparator());
        for (int i=0;i<documents.length;i++)
        {
            System.out.println("Document number "+i+" is: id = id = "+documents[i].getProperty("id")+" version is = "+documents[i].getProperty("version")+ " latest is "+documents[i].getProperty("latest")+" aggId is: "+documents[i].getProperty("aggId"));
        }
        Integer[] ids = new Integer[documents.length-1];
        for (int i=0;i<documents.length-1;i++)
        {
            ids[i] = documents[i].getProperty("id");
        }
        IdsQuery<SpaceDocument> documentIdsQuery = new IdsQuery<SpaceDocument>("Trade",ids);
        gigaSpace.change(documentIdsQuery, new ChangeSet().set("latest", false));
    }

}