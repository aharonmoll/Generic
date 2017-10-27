package gs.example;

import gs.example.data.Data;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

/**
 * Created by aharon on 3/30/17.
 */
public class DataFeeder {
    public static void main(String[] args) throws Exception {
        UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/mySpace?locators=localhost");
        GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();
        gigaSpace.clear(new Data());
        Data data = new Data("kuku");
        for (int i=0;i<1000;i++) {
            data.setId(String.valueOf(i));
            System.out.println("Notify Feeder Write! "+String.valueOf(i));
            try {
                gigaSpace.write(data);
            }
            catch (Exception e)
            {
                System.out.println(e);
                Thread.sleep(5000);
            }
            Thread.sleep(1000);
        }
    }
}
