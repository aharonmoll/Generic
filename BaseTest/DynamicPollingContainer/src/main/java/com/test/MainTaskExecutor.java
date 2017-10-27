package com.test;


import com.gigaspaces.async.AsyncFuture;
import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.space.Space;
import org.openspaces.admin.space.events.SpaceModeChangedEventManager;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

import java.util.concurrent.TimeUnit;

public class MainTaskExecutor {

	public static void main(String[] args) throws Exception{
		String spaceName = "space";
		GigaSpace gigaSpace = new GigaSpaceConfigurer(new SpaceProxyConfigurer(spaceName)).gigaSpace();
		
		StartPollingContainerTask taskStartPC = new StartPollingContainerTask();

		System.out.println("Client Side - Executing Task version:" + taskStartPC.getVersionID());

		AsyncFuture result = gigaSpace.execute(taskStartPC);
		result.get();

		Admin admin = new AdminFactory().discoverUnmanagedSpaces().createAdmin();
		System.out.println(admin.getSpaces());
		Space space = admin.getSpaces().waitFor(spaceName, 10, TimeUnit.SECONDS);
		SpaceModeChangedEventManager modeManager =  space.getSpaceModeChanged();
		SpaceModeListener spaceModeListener = new SpaceModeListener (space);
		modeManager.add(spaceModeListener);
		Thread.sleep(1000);
		System.out.println("Kill a Partition and hit Enter");
		System.in.read();
		System.in.read();
		
		
		System.out.println("Hit Enter to stop polling containers");
		System.in.read();
		System.in.read();
///////////////		
		StopPollingContainerTask taskStopPC = new StopPollingContainerTask();

		System.out.println("Client Side - Executing Task version:" + taskStopPC.getVersionID());

		result = gigaSpace.execute(taskStopPC);
		result.get();
		Thread.sleep(1000);
		admin.close();
	}
}
