package com.gigaspaces;

import java.security.Permission;

/**
 * Created by aharon on 2/23/15.
 */

public class GridSecurityManager extends SecurityManager {

    @Override
    public void checkPackageAccess(String pkg) {
        // avoid garbage
    }

    @Override
    public void checkPermission(Permission perm) {
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
    }



}