package purchase.example;

import com.j_spaces.core.client.SQLQuery;
import org.openspaces.events.*;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aharon on 8/10/15.
 */
@EventDriven
@Polling(gigaSpace = "sandboxSpace")
@TransactionalEvent
public class NewOrderProcessor {

    private static Logger logger = LoggerFactory
            .getLogger(NewOrderProcessor.class);

    @ExceptionHandler
    public EventExceptionHandler<PurchaseOrder> exceptionHandler() {
        return new PoEventExceptionHandler();
    }

    @EventTemplate
    SQLQuery<PurchaseOrder> unprocessedData() {
        SQLQuery<PurchaseOrder> template = new SQLQuery<PurchaseOrder>(
                PurchaseOrder.class, "state = ?");
        template.setParameter(1, EPurchaseOrderState.NEW);
        return template;
    }

    @SpaceDataEvent
    public PurchaseOrder eventListener(PurchaseOrder po)
            throws PoProcessingException {

        try {
            if (po.getNumber() == null) {
                throw new Exception("PO Number can't be null");
            }

            logger.debug("Processing PO ");
            // do some processing
            // change the state
            po.setState(EPurchaseOrderState.PROCESSED);
            // write back the PO to the space
            return po;

        } catch (Exception ex) {
            logger.debug("handling the exception for the: "
                    + (po.getRetryCounter() + 1) + " time");

            if (po.getRetryCounter() < 2) {
                logger.debug("Max retry count reached throwing exception");
                throw new PoProcessingException(
                        "Unable to process PO after three tries");
            } else {
                po.setRetryCounter(po.getRetryCounter() + 1);
                return po;
            }
        }
    }
}