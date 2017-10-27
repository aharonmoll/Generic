package com.test;


import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

public class MainFeeder {

	public static void main(String[] args) throws Exception{

		GigaSpace gigaSpace = new GigaSpaceConfigurer(new SpaceProxyConfigurer("space")).gigaSpace();

		int count =0;
		while (true)
		{
			count ++;
			Data d = new Data();
			d.setData("A"+count);
			d.setType(1L+count);
			d.setId("A"+count);
			d.setProcessed(false);
			Thread.sleep(3000);
			gigaSpace.write(d);
			System.out.println("wrote "+ count);
//			System.out.println("Hit enter to continue");
//			System.in.read();
		}
	}
}
