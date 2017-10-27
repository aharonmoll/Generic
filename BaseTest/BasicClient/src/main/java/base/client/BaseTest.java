package base.client;

import base.data.Person;
import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.openspaces.core.transaction.manager.DistributedJiniTxManagerConfigurer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Created by aharon on 1/1/15.
 */
public class BaseTest {

    private static final String ENCODING = "ISO-8859-1";

    public static void main(String[] args) throws Exception {

        //UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://localhost/*/mySpace?groups=aharon");
        //UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("/./mySpace?groups=aharon");
//        final PlatformVersion platformVersion;
//        platformVersion = new PlatformVersion();

//        UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/mySpace?locators=localhost").credentials("aharon","1234");
        UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/mySpace?locators=localhost");

//        EventSessionFactory factory = EventSessionFactory.getFactory(configurer.space());
//        EventSessionConfig config = new EventSessionConfig();
//        config.setDurableNotifications(true);
//        DataEventSession session = factory.newDataEventSession(config);
//
//        //GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();
        PlatformTransactionManager ptm = new DistributedJiniTxManagerConfigurer().transactionManager();
        GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).transactionManager(ptm).gigaSpace();

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        //definition.setPropagationBehavior(Propagation.REQUIRES_NEW.ordinal());
        TransactionStatus status = ptm.getTransaction(definition);

        System.out.println("Start client");
        //GigaSpace gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer("jini://localhost/*/mySpace?groups=aharon")).gigaSpace();
        System.out.println("got connection, writing objects ...");
        Person person1 = null;
        Person person2 = new Person(String.valueOf(1));

        for (int i = 1;i < 3; i++)
        {
            Person person = new Person(String.valueOf(i));
            person.setNumber(i);
            person.setSequenceNumber((long) (i+1));
            //if (i == 5) {
            gigaSpace.write(person);
            //} else {
            //System.out.println("Squence number Person:"+person.getSequenceNumber());
            //gigaSpace.write(person);
//            person1 = gigaSpace.read(person2,3000);
//            gigaSpace.change(person,new ChangeSet().increment("number",100));
            Thread.sleep(1000);
            //Person person2 = gigaSpace.read(person);
            //System.out.println("Squence number Person2:"+person2.getSequenceNumber());
            System.out.println(person);
        }

        ptm.commit(status);

        gigaSpace.read(new SQLQuery<Person>(Person.class, "SequenceNumber > 1"));

        System.exit(0);

        //String name = "number";
        //String type = "BASIC";
        //gigaSpace.getTypeManager().asyncAddIndex("base.data.Person",SpaceIndexFactory.createPropertyIndex(name, SpaceIndexType.BASIC));
       /* if(name.contains(".")){
             SpaceIndexFactory.createPathIndex(name, SpaceIndexType.valueOf(type));
        } else if(name.contains("[*]")){
             new SpaceCollectionIndex(name, SpaceIndexType.valueOf(type),false);
        } else if(name.contains("+")){
             SpaceIndexFactory.createCompoundIndex(name.split("\\+"));
        } else {
             SpaceIndexFactory.createPropertyIndex(name, SpaceIndexType.valueOf(type));
        }*/


     }
}

