package gigaspaces.example;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.gsm.GridServiceManagers;
import org.openspaces.admin.pu.ProcessingUnitDeployment;
import org.openspaces.admin.space.Space;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.gigaspaces.client.ChangeSet;
import com.gigaspaces.query.IdQuery;
import com.gigaspaces.security.directory.User;
import com.gigaspaces.security.directory.UserDetails;
import com.j_spaces.core.IJSpace;

public class Main
{

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException 
	{
		ProcessingUnitDeployment deployment=null;
		UserDetails userDetails = new User("kuku","1234");
		AdminFactory factory = new AdminFactory().credentials("kuku", "1234");
		factory.addGroup("aharon");
		factory.addLocator("127.0.0.1");
		Admin admin = factory.createAdmin();
        GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne(5, TimeUnit.MINUTES);
        Space space = admin.getSpaces().getSpaceByName("mySpace");
        if (space == null)
        {
			System.out.println("Deploying mySpace PU wait for 8 seconds");
        	deployment = new ProcessingUnitDeployment("mySpace.zip");
        	deployment.userDetails(userDetails);
        	gsm.deploy(deployment);
            Thread.sleep(8000);
        }
		UrlSpaceConfigurer spaceConfigurer = new
				UrlSpaceConfigurer("jini://*/*/mySpace?groups=aharon").credentials("kuku", "1234");
		IJSpace space2 = spaceConfigurer.space();
		GigaSpace gigaSpace = new GigaSpaceConfigurer(space2).gigaSpace();
//		gigaSpace.snapshot(new Data());
		Data data = new Data();
		Data data2 = new Data();
		data.setNormalDate(new Date());
		data.setId("1");
		data.setType((long)1);
		ArrayList<String> ls=new ArrayList<String>();
        ls.add("one");
        ls.add("Three");
	    ls.add("two");
	    ls.add("four");
		data.setList(ls);
		try
		{
			gigaSpace.write(data,3600000);
			IdQuery<Data> idQuery = new IdQuery<Data>(Data.class, "1");
			data2 = gigaSpace.read(idQuery);
			gigaSpace.change(idQuery, new ChangeSet().increment("type", (long)10));
			data = gigaSpace.read(idQuery);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage()+" "+e.getCause());
		}

		System.out.println("Data2 id = "+data2.getId());
		System.out.println("Data2 type = "+data2.getType());
		System.out.println("Data id = "+data.getId());
		System.out.println("Data type = "+data.getType());
		System.out.println("write succeeded");
		}
}