package gs.example.alerts;

import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.pu.events.ProcessingUnitInstanceLifecycleEventListener;

import java.util.logging.Logger;

/**
 * Created by aharon on 4/11/16.
 */
public class Listener implements ProcessingUnitInstanceLifecycleEventListener {
    private static final Logger logger = Logger.getLogger(Listener.class.getName());
    @Override
    public void processingUnitInstanceAdded(ProcessingUnitInstance processingUnitInstance) {
        logger.info(processingUnitInstance.getProcessingUnitInstanceName() + " was added ****");
    }

    @Override
    public void processingUnitInstanceRemoved(ProcessingUnitInstance processingUnitInstance) {
        logger.info(processingUnitInstance.getProcessingUnitInstanceName() + " was removed ****");
    }
}
