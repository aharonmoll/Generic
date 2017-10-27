package com.gigaspaces;

import org.openspaces.remoting.ExecutorProxy;

/**
 * Created by Aharon on 08-Feb-15.
 */
public class DataRemoting {

    @ExecutorProxy
    private IDataProcessor dataProcessor;

    public void setDataProcessor(IDataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }
}
