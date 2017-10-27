package com.mycompany;

import com.mycompany.data.Data;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

/**
 * Created by aharon on 7/30/15.
 */
public class Feeder {

    public static void main(String[] args) throws Exception {
        UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://localhost/*/space?groups=gigaspaces-10.1.1-XAPPremium-ga");
        //UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://localhost/*/space?groups=aharon");
        GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();
//        SpaceDocument document = gigaSpace.read(
//                new SQLQuery<SpaceDocument>(Data.class.getName(),
//                        "name='Dynamite'", QueryResultType.DOCUMENT).setProjections("spaceKey"));
        for (int i=0;i<10;i++)
        {
            Data data = new Data(String.valueOf(i),String.valueOf(i),false);
            gigaSpace.write(data);
        }
    }
}
