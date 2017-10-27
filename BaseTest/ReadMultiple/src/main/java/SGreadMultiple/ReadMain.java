package SGreadMultiple;

import com.gigaspaces.query.IdsQuery;
import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

/**
 * Created by aharon on 1/9/17.
 */
public class ReadMain {

    public static void main(String[] args) {
        UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/mySpace?locators=localhost");

        GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();

        for (int i = 1;i < 100; i++)
        {
            Person person = new Person(String.valueOf(i));
            person.setNumber(i);
            person.setSequenceNumber((long) (i+1));
            gigaSpace.write(person);
            //System.out.println(person);
        }

        SQLQuery<Person> query = new SQLQuery<Person>(Person.class,"").
                setProjections("id");
        Person result[] = gigaSpace.readMultiple(query);

        String[] strings = new String[20];
        for (int i=0;i<20;i++)
        {
            System.out.println(result[i].getNumber());
            System.out.println(result[i].getId());
            strings[i]=result[i].getId();
        }

        IdsQuery<Person> idsQuery = new IdsQuery<Person>(Person.class, strings);
        Person result2[] = gigaSpace.readByIds(idsQuery).getResultsArray();
        for (int i = 0;i < 20; i++)
        {
            System.out.println(result2[i].getNumber());
            System.out.println(result2[i].getId());
        }

        System.exit(0);

    }
}
