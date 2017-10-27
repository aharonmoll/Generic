package com.gigaspaces.test;

import org.openspaces.core.GigaSpace;

/**
 * Created by yuval on 12/28/16.
 */
public class T2 extends Thread {

    GigaSpace gigaSpace;

    public T2(GigaSpace gigaSpace){
        this.gigaSpace = gigaSpace;
    }

    public void run(){
        MySpaceClass[] mySpaceClassesArray = new MySpaceClass[2];
        MySpaceClass mySpaceClass = null;

        for (int i=0; i<2; i++){
            mySpaceClass = new MySpaceClass();
            mySpaceClass.setId(i+1001);
            mySpaceClassesArray[i] = mySpaceClass;
        }

        gigaSpace.writeMultiple(mySpaceClassesArray);
    }
}
