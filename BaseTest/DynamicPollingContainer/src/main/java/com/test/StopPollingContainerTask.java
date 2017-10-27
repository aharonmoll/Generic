package com.test;


import com.gigaspaces.annotation.SupportCodeChange;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.async.AsyncResult;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.cluster.ClusterInfo;
import org.openspaces.core.cluster.ClusterInfoAware;
import org.openspaces.core.executor.AutowireTask;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.TaskGigaSpace;
import org.openspaces.events.polling.SimplePollingEventListenerContainer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SupportCodeChange(id="1")
@AutowireTask
public class StopPollingContainerTask implements DistributedTask<Integer, Integer>, ClusterInfoAware{


	@Resource(name = "eventContainerList")
	private transient ArrayList eventContainerList;
	
	@TaskGigaSpace
	private transient GigaSpace space;

	
	transient ClusterInfo clusterInfo;
	
	Integer routing;
	
	 @SpaceRouting
	  public Integer routing() {
		 return routing;
	  }	

	 public String getVersionID()
	 {
		 SupportCodeChange annotation = this.getClass().getAnnotation(SupportCodeChange.class);
		 String id = annotation != null ? annotation.id() : null;
		 return id;
	 }

	public Integer execute() throws Exception {
		System.out.println("partition ID " + clusterInfo.getInstanceId() +" ---> Space Side  - Executing Task version ID:" + getVersionID());
		System.out.println("partition ID " + clusterInfo.getInstanceId() +" ---> task executed - classloader " + this.getClass().getClassLoader());

		if (eventContainerList == null)
			System.out.println("partition ID " + clusterInfo.getInstanceId() +" - No polling containers running");
		else
		{
			System.out.println("partition ID " + clusterInfo.getInstanceId() +" >>>>>>>>>>>>>> there are " + eventContainerList.size() + " event containers running");
			if (eventContainerList.size()>0)
			{
				
				Iterator iter = eventContainerList.iterator();
				while (iter.hasNext())
				{
					SimplePollingEventListenerContainer pollingEventListenerContainer = (SimplePollingEventListenerContainer )iter.next();
					System.out.println("partition ID " + clusterInfo.getInstanceId() +" >>>>>>>>>>>>>> stopping event container " + pollingEventListenerContainer);
					pollingEventListenerContainer.stop();
				}
				System.out.println("partition ID " + clusterInfo.getInstanceId() +" >>>>>>>>>>>>>> removing event containers");
				eventContainerList.clear();
			}
		}
		return clusterInfo.getInstanceId();
	}

	public Integer reduce(List<AsyncResult<Integer>> results) throws Exception {
		for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			AsyncResult<Integer> asyncResult = (AsyncResult<Integer>) iterator.next();
			System.out.println("Stopped polling container on partition:" + asyncResult.getResult());
		}
		return 0;
	}

	public void setClusterInfo(ClusterInfo clusterInfo) {
		if (clusterInfo == null )
		{
			this.clusterInfo = new ClusterInfo();
			this.clusterInfo.setInstanceId(1);
		}
		else
			this.clusterInfo = clusterInfo;		
	}
}
