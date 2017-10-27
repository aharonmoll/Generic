import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Client {
	private GigaSpace gigaSpace;

	public void setup()  {
			gigaSpace = new GigaSpaceConfigurer(new SpaceProxyConfigurer(
				"mySpace").lookupGroups("aharon").credentials("aharon","1234")).gigaSpace();
	}
	
	public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/pu.xml");
		new Client();


        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        applicationContext.destroy();
        System.exit(1);
	}

	public Client() {
		super();
		setup();
		write();
		read();
	}

	private void write() {		
		gigaSpace.write(new MyPojo("A1","For user am"));
		gigaSpace.write(new MyPojo("A2","For user am"));
		gigaSpace.write(new MyPojo("A3","For user st"));
		gigaSpace.write(new MyPojo("A4","For user st"));
	}

	private void read() {
//		MyPojo[] pojos = gigaSpace.readMultiple(new MyPojo("A1","For user aharon"));
		SQLQuery<MyPojo> sqlQuery = new SQLQuery<MyPojo>(MyPojo.class, "data like 'For%' or data like '%ester'");
		MyPojo[] myEntrys = gigaSpace.readMultiple(sqlQuery);
		//sSystem.out.println("Read by template number of pojos: " + pojos.length);
		System.out.println("Read by SQL Query number of pojos: " + myEntrys.length);
		System.out.println("Total count for all users: "+gigaSpace.count(new MyPojo()));
	}
}
