package com.gs.latest;

import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.query.explainplan.ExplainPlan;
import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

/**
 * Created by aharon on 9/14/16.
 */
public class LatestVersionQuery {

    public static void main(String[] args) {
        GigaSpace space = new GigaSpaceConfigurer(new SpaceProxyConfigurer(
                "mySpace").lookupGroups("aharon").credentials("aharon", "1234")).gigaSpace();
        SpaceDocument spaceDocument;
        SpaceDocument[] spaceDocuments;
        for (int i=0;i<1;i++)
        {
            SQLQuery<SpaceDocument>  query = new SQLQuery<SpaceDocument>("Trade", "latest = true and aggregateId = "+i).withExplainPlan();
            spaceDocument = space.read(query);
            System.out.println("The Latest Trade is= aggregateId: "+spaceDocument.getProperty("aggregateId")+ " uniqueId: "+spaceDocument.getProperty("uniqueId"));
            ExplainPlan explainPlan = query.getExplainPlan();
            System.out.println(explainPlan.toString());
        }
        SQLQuery<SpaceDocument>  query = new SQLQuery<SpaceDocument>("Trade", "latest = true").withExplainPlan();
        spaceDocuments = space.readMultiple(query);
        ExplainPlan explainPlan = query.getExplainPlan();
        System.out.println(explainPlan.toString());
        System.out.println("Number of Objects: "+spaceDocuments.length);
    }

}
