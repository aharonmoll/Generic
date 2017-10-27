package com.gigaspaces;

import org.openspaces.persistency.patterns.PersistencyExceptionHandler;

import javax.persistence.Entity;

@Entity
public class MyExceptionHandler implements PersistencyExceptionHandler {
	
	public MyExceptionHandler(){ 
		System.out.println("Got Exception");
	}

	@Override
	public void onException(Exception e, Object event) {
		System.out.println("Got Exception from MyExceptionHandler");
        e.printStackTrace();
	}
}
