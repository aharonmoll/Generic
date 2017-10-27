package com.gs.latest;

import com.gigaspaces.async.AsyncFuture;
import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.query.IdQuery;
import org.openspaces.core.GigaSpace;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

/**
 * Created by aharon on 9/7/16.
 */
public class UbsUtils {

    public static void write(GigaSpace gigaSpace, SpaceDocument spaceDocument) throws ExecutionException, InterruptedException {
        AsyncFuture<Serializable> future = gigaSpace.execute(new Write(spaceDocument), spaceDocument);
        future.get();
    }

    public static SpaceDocument getLatest(GigaSpace gigaSpace,Integer aggregateId)
    {
        Integer uniqueId = getUniqueId(gigaSpace,aggregateId);
        System.out.println("latestPoniterResult= aggregateId: "+aggregateId+" uniqueId: "+uniqueId);
        IdQuery<SpaceDocument> documentIdQuery = new IdQuery<SpaceDocument>("Trade",uniqueId);
        SpaceDocument spaceDocument = gigaSpace.read(documentIdQuery);
        return spaceDocument;
    }

    public static Integer getUniqueId(GigaSpace gigaSpace,Integer aggregateId)
    {
        LatestPointer latestPointer = new LatestPointer();
        latestPointer.setAggregateId(aggregateId);
        LatestPointer latestPointerResult = gigaSpace.read(latestPointer);
        return latestPointerResult.getLatestUniqueId();
    }
}
