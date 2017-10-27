package com.gigaspaces;

import org.openspaces.remoting.Routing;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Aharon on 05-Feb-15.
 */
public interface IDataProcessor {

    /**
     * Process a given Data object, returning the processed Data object.
     */
    //Data processData(Data data);

    /**
     * Process a given Data object, returning the processed Data object.
     */
    Data processData(@Routing("getType") Data data);

}
