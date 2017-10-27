package com.gs;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.EmbeddedSpaceConfigurer;
import org.openspaces.core.space.mode.AfterSpaceModeChangeEvent;
import org.openspaces.core.space.mode.PostPrimary;

import java.util.logging.Logger;

/**
 * Created by aharon on 3/20/17.
 */
public class EmbeddedSpaceWriter {

    private static final Logger _Logger = Logger.getLogger(EmbeddedSpaceWriter.class.getName());
    private final GigaSpace _embeddedNodeSpaceProxy = new GigaSpaceConfigurer(new EmbeddedSpaceConfigurer("referenceDataSpace")).gigaSpace();

    @PostPrimary
    public void postPrimary(AfterSpaceModeChangeEvent event) {
        _Logger.info("**** Entered postPrimary ****");
        _Logger.info("**** Starting the embedded writer loop (straight write) ****");
        for(long i=1L; i<=10L; i++) {
            NotFullySerializableObject notFullySerializableObject = new NotFullySerializableObject(i);
            _embeddedNodeSpaceProxy.write(notFullySerializableObject);
        }

    }
}