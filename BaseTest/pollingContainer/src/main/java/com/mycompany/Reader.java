package com.mycompany;

import com.mycompany.data.Data;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

/**
 * Created by aharon on 8/3/15.
 */
public class Reader {
    public static void main(String[] args) throws Exception {
        UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://localhost/*/space?groups=gigaspaces-10.1.1-XAPPremium-ga");
        GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();
        for (int i=0;i<10;i++)
        {
            Data data = new Data(String.valueOf(i),String.valueOf(i),true);
            Data dataOut = gigaSpace.read(data);
            if (dataOut!=null)
                System.out.println("Data output is: "+dataOut.toString()+ dataOut.getProcessed());
        }
        for (int i=0;i<10;i++)
        {
            Data data = new Data(String.valueOf(i),String.valueOf(i),false);
            Data dataOut = gigaSpace.read(data);
            if (dataOut!=null)
                System.out.println("Data output is: "+dataOut.toString()+ dataOut.getProcessed());
        }
    }
}
