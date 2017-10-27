package com.gigaspaces;

import com.j_spaces.core.IJSpace;
import com.j_spaces.core.cluster.IReplicationFilter;
import com.j_spaces.core.cluster.IReplicationFilterEntry;
import com.j_spaces.core.cluster.ReplicationOperationType;
import com.j_spaces.core.cluster.ReplicationPolicy;
import org.openspaces.core.space.filter.replication.DefaultReplicationFilterProviderFactory;

/**
 * Created by aharon on 3/25/15.
 */
public class RepFactory extends DefaultReplicationFilterProviderFactory {
    @Override
    public void setActiveWhenBackup(boolean activeWhenBackup) {
        super.setActiveWhenBackup(activeWhenBackup);
    }

    @Override
    public void setOutputFilter(IReplicationFilter outputFilter) {
        super.setOutputFilter(outputFilter);
    }
}
