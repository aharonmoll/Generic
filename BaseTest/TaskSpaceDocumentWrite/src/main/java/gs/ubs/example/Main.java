package gs.ubs.example;

import com.gigaspaces.async.AsyncFuture;
import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.metadata.SpaceTypeDescriptor;
import com.gigaspaces.metadata.SpaceTypeDescriptorBuilder;
import com.gigaspaces.metadata.index.SpaceIndexType;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by aharon on 8/17/16.
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


//        GigaSpace space = new GigaSpaceConfigurer(new SpaceProxyConfigurer(
//                "mySpace").lookupGroups("aharon")).gigaSpace();
                GigaSpace space = new GigaSpaceConfigurer(new SpaceProxyConfigurer(
                "mySpace").lookupGroups("xap-11.0.0")).gigaSpace();

        registerDocument(space);


        SpaceDocument[] documents = new SpaceDocument[50];

        for (int i=0;i<5;i++)
        {
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("id", i);
            properties.put("aggId", i%10);
            properties.put("date", new Date());
            properties.put("version", i%3);
            properties.put("status", "open");
            properties.put("latest",true);

            SpaceDocument document = new SpaceDocument("Trade", properties);

            AsyncFuture<Integer> execute = space.execute(new WriteSpaceDocTask(document), 0);
            execute.get();
        }
        System.exit(1);
    }

    static public void registerDocument(GigaSpace space) {
        SpaceTypeDescriptor tradeDescriptor = new SpaceTypeDescriptorBuilder("Trade")
                .idProperty("id").addFixedProperty("id",Integer.class).routingProperty("aggId")
                .addPropertyIndex("date", SpaceIndexType.BASIC)
                .addPropertyIndex("version",SpaceIndexType.BASIC).create();
        // Register type:
        space.getTypeManager().registerTypeDescriptor(tradeDescriptor);
    }

}
