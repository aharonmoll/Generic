package gigaspaces.task;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

/**
 * Created by aharon on 9/3/15.
 */
public class Feeder {
    public static void main(String[] args) throws Exception {
        SpaceProxyConfigurer configurer = new SpaceProxyConfigurer("mySpace").lookupLocators("localhost");
        GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();
        for (int i = 0;i < 10; i++)
        {
            Employee employee = new Employee();
            //if (i == 5) {
            //    gigaSpace.write(person, 1000);
            //} else {
            employee.setAddress(new Address("Tel"+i,""+i,i+i,i));
            employee.setId(i);
            employee.setAge(10*i);
            System.out.println("Id number Employee:" + employee.getId());
            gigaSpace.write(employee);
        }
    }
}
