package com.gigaspaces.test;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yuvald on 19-Oct-15.
 */
public class Main {

    public static void main(String[] args) {

        GigaSpace space = new GigaSpaceConfigurer (new UrlSpaceConfigurer("jini://*/*/mySpace")).gigaSpace();

        T1 t1 = new T1(space);
        T2 t2 = new T2(space);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss:SSS");
        Date date = new Date();
        String sDate= sdf.format(date);
        System.out.println("Main have started: + "+sDate);

        t1.start();

        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        t2.start();
    }
}

