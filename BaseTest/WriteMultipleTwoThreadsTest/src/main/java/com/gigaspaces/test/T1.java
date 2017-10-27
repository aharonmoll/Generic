package com.gigaspaces.test;

import org.openspaces.core.GigaSpace;

/**
 * Created by yuval on 12/28/16.
 */
public class T1 extends Thread {

    GigaSpace gigaSpace;

    public T1(GigaSpace gigaSpace){
        this.gigaSpace = gigaSpace;
    }

    public void run(){
        MySpaceClass[] mySpaceClassesArray = new MySpaceClass[1000];
        MySpaceClass mySpaceClass = null;

        for (int i=0; i<1000; i++){
            mySpaceClass = new MySpaceClass();
            mySpaceClass.setId(i);
            mySpaceClassesArray[i] = mySpaceClass;
        }

        gigaSpace.writeMultiple(mySpaceClassesArray);
    }
}
