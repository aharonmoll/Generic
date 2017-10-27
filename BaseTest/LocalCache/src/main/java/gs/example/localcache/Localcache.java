package gs.example.localcache;

import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.openspaces.core.space.cache.LocalCacheSpaceConfigurer;
import org.openspaces.core.space.cache.LocalViewSpaceConfigurer;

/**
 * Created by aharon on 5/16/16.
 */
public class Localcache {

    public static void main(String[] args) throws Exception {

        // Initialize remote space configurer:
        SpaceProxyConfigurer urlConfigurer = new SpaceProxyConfigurer("mySpace?groups=xap-11.0.0");

// Initialize local cache configurer
        LocalCacheSpaceConfigurer localCacheConfigurer =
                new LocalCacheSpaceConfigurer(urlConfigurer);
// Create local cache:
        GigaSpace localCache = new GigaSpaceConfigurer(localCacheConfigurer).gigaSpace();

        LocalViewSpaceConfigurer localViewConfigurer = new LocalViewSpaceConfigurer(urlConfigurer)
                .batchSize(1000)
                .batchTimeout(100)
                .maxDisconnectionDuration(1000*60*60)
                .addProperty("space-config.engine.memory_usage.high_watermark_percentage", "90")
                .addProperty("space-config.engine.memory_usage.write_only_block_percentage", "88")
                .addProperty("space-config.engine.memory_usage.write_only_check_percentage", "86")
                .addProperty("space-config.engine.memory_usage.retry_count", "5")
                .addProperty("space-config.engine.memory_usage.explicit", "false")
                .addProperty("space-config.engine.memory_usage.retry_yield_time", "50")
                .addViewQuery(new SQLQuery(Data.class, "processed = true"));
// Create local view:
        GigaSpace localView = new GigaSpaceConfigurer(localViewConfigurer).gigaSpace();

        Thread.sleep(1000000);
    }
}
