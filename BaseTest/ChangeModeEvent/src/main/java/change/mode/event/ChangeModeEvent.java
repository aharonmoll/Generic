package change.mode.event;

import org.openspaces.core.space.mode.BeforeSpaceModeChangeEvent;
import org.openspaces.core.space.mode.PostPrimary;
import org.openspaces.core.space.mode.PreBackup;

/**
 * Created by aharon on 8/11/15.
 */
public class ChangeModeEvent {

    // invoked before a space becomes backup; gets the BeforeSpaceModeChangeEvent as a parameter
    @PreBackup
    public void myBeforeBackupMethod(BeforeSpaceModeChangeEvent event) {
        System.out.println("PRE Backup initiate");
        // Do something
    }

    // invoked after a space becomes primary; doesn't get any parameter.
    @PostPrimary
    public void myAfterPrimaryMethod() {
        // Do something
        System.out.println("POST Primary initiate");
    }
}
