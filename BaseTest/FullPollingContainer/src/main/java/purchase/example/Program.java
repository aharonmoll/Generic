package purchase.example;

import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.EmbeddedSpaceConfigurer;
import org.openspaces.events.polling.SimplePollingContainerConfigurer;
import org.openspaces.events.polling.SimplePollingEventListenerContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


/**
 * Created by aharon on 8/10/15.
 */
public class Program {

    private static Logger logger = LoggerFactory.getLogger(Program.class);

    public static void main(String[] args) throws InterruptedException {

        GigaSpace space = new GigaSpaceConfigurer(new EmbeddedSpaceConfigurer("sandboxSpace")).gigaSpace();

        // Register the Processor
        registerListener(space);

        Thread.sleep(1000);

        // Create a Purchase Order
        PurchaseOrder po = new PurchaseOrder();
        //po.setNumber("12345");
        po.setId(UUID.randomUUID());
        po.setState(EPurchaseOrderState.NEW);

        // Write it into the Space
        space.write(po);

        Thread.sleep(1000);

        // Read all not processable PO's
        SQLQuery<PurchaseOrder> query = new SQLQuery<PurchaseOrder>(
                PurchaseOrder.class, "state = ?");
        query.setParameter(1, EPurchaseOrderState.UNPROCESSABLE);

        for (PurchaseOrder pu : space.readMultiple(query)) {
            logger.debug("PurchaseOrder in Space "+ pu);
        }

        System.exit(1);
    }

    private static void registerListener(GigaSpace space) {
        SimplePollingEventListenerContainer pollingEventListenerContainer = new SimplePollingContainerConfigurer(
                space).eventListenerAnnotation(new NewOrderProcessor())
                .pollingContainer();

        pollingEventListenerContainer.start();
    }
}