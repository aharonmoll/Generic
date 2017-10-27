package gs.example;

import org.openspaces.admin.pu.events.ProcessingUnitInstanceProvisionStatusChangedEventListener;
import org.openspaces.admin.pu.events.ProcessingUnitInstanceProvisionStatusChangedEventManager;

/**
 * Created by aharon on 6/4/17.
 */
public class PuStatusChange implements ProcessingUnitInstanceProvisionStatusChangedEventManager {
    @Override
    public void add(ProcessingUnitInstanceProvisionStatusChangedEventListener listener) {

    }

    @Override
    public void add(ProcessingUnitInstanceProvisionStatusChangedEventListener listener, boolean includeCurrentStatus) {


    }

    @Override
    public void remove(ProcessingUnitInstanceProvisionStatusChangedEventListener listener) {

    }
}
