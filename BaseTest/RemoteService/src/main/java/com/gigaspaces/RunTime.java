package com.gigaspaces;

import org.openspaces.core.space.mode.PostPrimary;
import org.springframework.stereotype.Component;

/**
 * Created by aharon on 2/23/15.
 */
@Component
public class RunTime {
    public RunTime() {
        System.out.println("******** Constracting RunTime class ##########");
    }

    @PostPrimary
    public void setSecurityManager() {
        System.out.println("******** Setting SecurityManager ##########");
        System.setSecurityManager(new GridSecurityManager());
    }
}
