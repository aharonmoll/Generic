package com.gs.latest;

import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.query.IdQuery;
import com.gigaspaces.query.IdsQuery;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

/**
 * Created by aharon on 9/5/16.
 */
public class LatestResultQuery {
    public static void main(String[] args) {
        GigaSpace space = new GigaSpaceConfigurer(new SpaceProxyConfigurer(
                "mySpace").lookupGroups("aharon")).gigaSpace();

        SpaceDocument spaceDocument = null;
        for (int i=0;i<10;i++)
        {
            spaceDocument=UbsUtils.getLatest(space,i);
            System.out.println("The Latest Trade is= aggregateId: "+spaceDocument.getProperty("aggregateId")+ " uniqueId: "+spaceDocument.getProperty("uniqueId"));
        }
    }
}
