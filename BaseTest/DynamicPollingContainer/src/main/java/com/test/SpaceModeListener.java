package com.test;

import com.gigaspaces.async.AsyncFuture;
import com.gigaspaces.cluster.activeelection.SpaceMode;
import org.openspaces.admin.space.Space;
import org.openspaces.admin.space.events.SpaceModeChangedEvent;
import org.openspaces.admin.space.events.SpaceModeChangedEventListener;

import java.util.concurrent.ExecutionException;

public class SpaceModeListener implements SpaceModeChangedEventListener {

	Space space;

	public SpaceModeListener(Space space) {
		this.space = space;
	}

	public void spaceModeChanged(SpaceModeChangedEvent event) {
		System.out.println("get space mode event:" + event);
		
		if (event.getPreviousMode() == null) 
			return;
		String partition_member = event.getSpaceInstance().getInstanceId() + "";
		if ((event.getNewMode().equals(SpaceMode.PRIMARY)) && (event.getPreviousMode().equals(SpaceMode.BACKUP))) {
			StartPollingContainerTask taskStartPC = new StartPollingContainerTask();

			System.out.println("Client Side - Executing Task version:" + taskStartPC.getVersionID());

			AsyncFuture result = space.getGigaSpace().execute(taskStartPC, event.getSpaceInstance().getInstanceId());
			try {
				result.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("SpaceModeChangedEvent:  Space " + space.getName() + " - Instance " + partition_member
					+ " moved into " + event.getNewMode());

		}
	}
}
