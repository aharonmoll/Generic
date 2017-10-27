package SocGen;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

/**
 * Created by aharon on 12/28/16.
 */
public class RunShortThread extends Thread {
    public void run() {
        UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/mySpace?locators=localhost");
        GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();
        Person[] persons = new Person[2];
        for (int i = 0;i < 2; i++)
        {
            Person person = new Person((long) (i+1),String.valueOf(i),i);
            persons[i]=person;
        }
        gigaSpace.writeMultiple(persons);
        System.out.println("Hello from a short thread!");
    }
}