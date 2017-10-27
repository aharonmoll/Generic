package com.gigaspaces;

import com.j_spaces.core.IJSpace;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

        IJSpace ispace = new UrlSpaceConfigurer("jini://*/*/" + "mySpace" + "?groups=aharon").space();
        GigaSpace gigaSpace = new GigaSpaceConfigurer(ispace).clustered(true).gigaSpace();

        for (int i=0; i<10 ; i++ ) {
            Person person = new Person();
            person.setFirstName("Aharon");
            person.setLastName("Moll");
            person.setPersonId("id" + i);
            gigaSpace.write(person);
        }
    }
}


