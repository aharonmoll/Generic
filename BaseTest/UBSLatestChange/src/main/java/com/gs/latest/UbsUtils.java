package com.gs.latest;

import com.gigaspaces.async.AsyncFuture;
import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.query.IdQuery;
import org.openspaces.core.GigaSpace;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by aharon on 9/7/16.
 */
public class UbsUtils {

    public static void write(GigaSpace gigaSpace, SpaceDocument spaceDocument) throws ExecutionException, InterruptedException {
        AsyncFuture<Serializable> future = gigaSpace.execute(new Write(spaceDocument), spaceDocument);
        future.get();
    }
}
