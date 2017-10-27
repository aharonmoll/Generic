package com.gigaspaces;

import com.gigaspaces.metadata.SpaceTypeDescriptorBuilder;
import org.openspaces.core.GigaSpace;
import org.springframework.transaction.PlatformTransactionManager;

public class Connector {
    private static GigaSpace gigaSpace;
    private static PlatformTransactionManager ptm;

    public Connector(GigaSpace gigaSpace, PlatformTransactionManager ptm) {
        this.gigaSpace = gigaSpace;
        this.ptm = ptm;
    }

    public static GigaSpace getGigaSpace() {
        return gigaSpace;
    }

    public static PlatformTransactionManager getPtm() {
        return ptm;
    }
}
