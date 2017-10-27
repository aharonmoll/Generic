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
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.SimplePollingContainerConfigurer;
import org.openspaces.events.polling.SimplePollingEventListenerContainer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SupportCodeChange(id="2")
@AutowireTask
public class StartPollingContainerTask implements DistributedTask<Integer, Integer>, ClusterInfoAware{
	public StartPollingContainerTask(){}
	
	@Resource(name = "eventContainerList")
	private transient ArrayList eventContainerList;
	
	@TaskGigaSpace
	private transient GigaSpace space;

	Integer routing;
	
	transient ClusterInfo clusterInfo;
	
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
		Data template = new Data();
		template.setProcessed(false);
		if (eventContainerList == null)
			eventContainerList = new ArrayList();
		else
		{
			System.out.println("partition ID " + clusterInfo.getInstanceId() +" >>>>>>>>>>>>>> there are " + eventContainerList.size() + " event containers running");
			if (eventContainerList.size()>0)
			{
				
				Iterator iter = eventContainerList.iterator();
				while (iter.hasNext())
				{
					SimplePollingEventListenerContainer pollingEventListenerContainer = (SimplePollingEventListenerContainer )iter.next();
					System.out.println("partition ID " + clusterInfo.getInstanceId() + " >>>>>>>>>>>>>> stopping event container " + pollingEventListenerContainer);
					pollingEventListenerContainer.stop();
				}
				System.out.println("partition ID " + clusterInfo.getInstanceId() +" >>>>>>>>>>>>>> removing event containers");
				eventContainerList.clear();
			}
		}
		System.out.println("partition ID " + clusterInfo.getInstanceId() + " >>>>>>>>>>>>>> starting event container");

		SimplePollingEventListenerContainer pollingEventListenerContainer = new SimplePollingContainerConfigurer(space)
				.template(template)
                .eventListenerAnnotation(new Object() {
                    @SpaceDataEvent
                    public void eventHappened(Data data) {
                        System.out.println("partition ID " +clusterInfo.getInstanceId()+ " version:" + getVersionID() + " D processing object id:" + data.getId());
                    }
                }).pollingContainer();
		System.out.println("partition ID " + clusterInfo.getInstanceId() + " >>>>>>>>>>>>>> started polling container version " + getVersionID()+ " "+pollingEventListenerContainer);
		// start the notification
		pollingEventListenerContainer.start();
		eventContainerList.add(pollingEventListenerContainer);
		return clusterInfo.getInstanceId();
	}

	public Integer reduce(List<AsyncResult<Integer>> results) throws Exception {
		for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			AsyncResult<Integer> asyncResult = (AsyncResult<Integer>) iterator.next();
			System.out.println("Started polling container on partition:" + asyncResult.getResult());
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
