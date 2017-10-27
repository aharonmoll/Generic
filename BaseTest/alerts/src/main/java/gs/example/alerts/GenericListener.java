package gs.example.alerts;

import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.pu.events.*;
import org.openspaces.admin.space.events.SpaceModeChangedEvent;
import org.openspaces.admin.space.events.SpaceModeChangedEventListener;

import java.util.logging.Logger;

/**
 * Created by aharon on 4/13/16.
 */
public class GenericListener implements ProcessingUnitInstanceLifecycleEventListener, SpaceModeChangedEventListener {
    private static final Logger logger = Logger.getLogger(Listener.class.getName());

    @Override
    public void spaceModeChanged(SpaceModeChangedEvent event) {
        logger.info(event.getSpaceInstance().getSpaceInstanceName() + " mode changed to "+event.getNewMode());
    }

    @Override
    public void processingUnitInstanceAdded(ProcessingUnitInstance processingUnitInstance) {
        logger.info(processingUnitInstance.getProcessingUnitInstanceName() + " was added ****");
    }

    @Override
    public void processingUnitInstanceRemoved(ProcessingUnitInstance processingUnitInstance) {
        logger.info(processingUnitInstance.getProcessingUnitInstanceName() + " was removed ****");
    }
}
