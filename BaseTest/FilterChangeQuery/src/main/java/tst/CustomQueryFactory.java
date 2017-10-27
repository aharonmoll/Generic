package tst;

public class CustomQueryFactory {
	
	public  SRDCustomQuery getCustomeQueryForType(String typeName){
		System.out.println("--------getCustomeQueryForType is called");
		//TODO create filter per type
		return new SRDCustomQuery();
	}

}
