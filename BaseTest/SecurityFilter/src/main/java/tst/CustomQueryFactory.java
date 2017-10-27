package tst;

public class CustomQueryFactory {
	
	public  SRDCustomQuery getCustomeQueryForType(String typeName){
		System.out.println("--------getCustomeQueryForType is called");
		return new SRDCustomQuery();
	}

}
