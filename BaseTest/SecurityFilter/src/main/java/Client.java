import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.j_spaces.core.IJSpace;


public class Client {
	  private GigaSpace gigaSpace;

	public void setup()  {
	        IJSpace configurer = new UrlSpaceConfigurer("jini://*/*/mySpace?groups=ST90GA").userDetails("st","st").space();	        	       
	        gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();
	}
	
	public static void main(String[] args) {
		new Client();
	}

	public Client() {
		super();
		setup();
		write();
		read();
	}

	private void write() {		
		gigaSpace.write(new MyPojo("A2","A2"));
	}

	private void read() {
		MyPojo pojo = gigaSpace.read(new MyPojo("A2","A2"));
		System.out.println("Read pojo: " + pojo);
	}
}
