package com.test;


import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.EmbeddedSpaceConfigurer;

public class SpaceRunMain {

	public static void main(String[] args) throws Exception{
		
		GigaSpace gigaSpace  =  new GigaSpaceConfigurer(new EmbeddedSpaceConfigurer("mySpace")).gigaSpace();
		while (true)
		{
			Thread.sleep(10000);
		}
	}
}
