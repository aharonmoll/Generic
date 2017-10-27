package com.gigaspaces.data.trade;

import com.gigaspaces.client.WriteMultipleException;
import com.gigaspaces.cluster.replication.ConsistencyLevelCompromisedException;
import com.gigaspaces.cluster.replication.WriteConsistencyLevelCompromisedException;
import com.j_spaces.core.IJSpace;
import com.j_spaces.core.cluster.IReplicationFilter;
import com.j_spaces.core.cluster.IReplicationFilterEntry;
import com.j_spaces.core.cluster.ReplicationOperationType;
import com.j_spaces.core.cluster.ReplicationPolicy;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.openspaces.core.space.filter.replication.DefaultReplicationFilterProviderFactory;

/**
 * Created by aharon on 3/24/15.
 */
public class Filter {

    public static void  main(String[] args) throws Exception {
        //SystemProperties.setSystemProperty("com.gs.replication.required_consistency_level", "2");

        final UrlSpaceConfigurer configurer2 = new UrlSpaceConfigurer("/./consistencyCompromised?groups=aharon&cluster_schema=sync_replicated&total_members=2&id=2");
        DefaultReplicationFilterProviderFactory repFactory = new DefaultReplicationFilterProviderFactory();
        repFactory.setInputFilter(new IReplicationFilter() {
            @Override
            public void init(IJSpace space, String paramUrl, ReplicationPolicy replicationPolicy) {
            }

            @Override
            public void process(int direction, IReplicationFilterEntry replicationFilterEntry, String remoteSpaceMemberName) {
                try {
                    if (replicationFilterEntry.getOperationType() == ReplicationOperationType.WRITE) {
                        configurer2.close();
                    }
                } catch (Exception e) {
                    System.out.println("error while stopping backup" + e);
                }
            }

            @Override
            public void close() {

            }
        });
        repFactory.afterPropertiesSet();

        final UrlSpaceConfigurer configurer1 = new UrlSpaceConfigurer("/./consistencyCompromised?groups=aharon&cluster_schema=sync_replicated&total_members=2&id=1");

        GigaSpace gigaspace = new GigaSpaceConfigurer(configurer1.replicationFilterProvider(repFactory)).gigaSpace();
        //noinspection UnusedDeclaration
        GigaSpace backup = new GigaSpaceConfigurer(configurer2.replicationFilterProvider(repFactory)).gigaSpace();

        try
        {
            gigaspace.write(new Person("1"));
        }
        catch (WriteConsistencyLevelCompromisedException e)
        {
            System.out.println("HHHHHHHHHHHHHH "+e.getLeaseContext().getUID()+"HHHHHHHHHHHHHH");
        }
        finally
        {
            configurer1.close();
            configurer2.close();
        }
    }
}
