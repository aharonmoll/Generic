package purchase.example;

import org.openspaces.core.GigaSpace;
import org.openspaces.events.EventExceptionHandler;
import org.openspaces.events.ListenerExecutionFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;

/**
 * Created by aharon on 8/10/15.
 */
public class PoEventExceptionHandler implements
        EventExceptionHandler<PurchaseOrder> {

    private static Logger logger = LoggerFactory
            .getLogger(PoEventExceptionHandler.class);

    public void onSuccess(PurchaseOrder po, GigaSpace space,
                          TransactionStatus txStatus, Object obj) throws RuntimeException {
    }

    public void onException(ListenerExecutionFailedException exception,
                            PurchaseOrder po, GigaSpace space, TransactionStatus txStatus,
                            Object obj) throws RuntimeException {

        if (exception.getCause() instanceof PoProcessingException) {
            logger.debug("Dealing with the exception, change the status to UNPROCESSABLE and write it back into the space");
            po.setState(EPurchaseOrderState.UNPROCESSABLE);
            space.write(po);
        }
        else
        {
            // Handle other exceptions
        }
    }
}