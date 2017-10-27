package gs.ubs.example;

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

/**
 * Created by aharon on 8/17/16.
 */
public class Main {

    public static void main(String[] args) {

        // Create the Space
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/pu.xml");

        GigaSpace space = new GigaSpaceConfigurer(new SpaceProxyConfigurer(
                "mySpace").lookupGroups("aharon")).gigaSpace();

        //GigaSpace space = new GigaSpaceConfigurer(new EmbeddedSpaceConfigurer("mySpace")).gigaSpace();

        //registerDocument(space);

        SpaceDocument[] documents = new SpaceDocument[50];

        for (int i=0;i<50;i++)
        {
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("id", i);
            properties.put("aggId", i%10);
            properties.put("date", new Date());
            properties.put("version", i%3);
            properties.put("status", "open");
            properties.put("latest",true);
            //if (i==1000) properties.put("latest",true);

            SpaceDocument document = new SpaceDocument("Trade", properties);
            documents[i] = document;


            //space.write(document);
        }
        space.writeMultiple(documents);
//        for (int i=1;i<=1000;i++)
//        {
//            Trade trade = new Trade();
//            trade.setId(i);
//            trade.setAggId(i%10);
//            trade.setDate(new Date());
//            trade.setVersion(i%3);
//            trade.setStatus("open");
//            trade.setLatest(true);
//            //if (i==1000) trade.setLatest(true);
//
//            space.write(trade);
//        }

//        SQLQuery<SpaceDocument> query = new SQLQuery<SpaceDocument>(
//                "Trade", "");
//
//        SpaceDocument[] result = space.readMultiple(query);

        //Trade[] result = space.readMultiple(new Trade());

        // You should see two documents
        //System.out.println(result.length);

//        SQLQuery<Trade> tradeSQLQuery = new SQLQuery<Trade>(Trade.class,"latest = true");
//        Trade trade = space.read(tradeSQLQuery);
//        System.out.println(trade.isLatest()+" for id = "+ trade.getId());


  applicationContext.destroy();

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
