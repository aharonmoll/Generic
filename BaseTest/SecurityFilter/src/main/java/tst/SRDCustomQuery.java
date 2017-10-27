package tst;
import java.util.List;

import org.openspaces.core.GigaSpace;

import com.gigaspaces.internal.metadata.ITypeDesc;
import com.gigaspaces.internal.query.ICustomQuery;
import com.gigaspaces.internal.query.IQueryIndexScanner;
import com.gigaspaces.server.ServerEntry;
import com.j_spaces.core.SpaceContext;
import com.j_spaces.core.client.SQLQuery;


public class SRDCustomQuery implements ICustomQuery{
	GigaSpace gigaspace;
	SpaceContext spaceContext;

	public SpaceContext getSpaceContext() {
		return spaceContext;
	}

	public void setSpaceContext(SpaceContext spaceContext) {
		this.spaceContext = spaceContext;
	}

	public SRDCustomQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GigaSpace getGigaspace() {
		return gigaspace;
	}

	public void setGigaspace(GigaSpace gigaspace) {
		this.gigaspace = gigaspace;
	}


	public List<IQueryIndexScanner> getCustomIndexes() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean matches(ServerEntry arg0) {
		// TODO Auto-generated method stub
		System.out.println("____ matches is called");
		return false;
	}


	public SQLQuery toSQLQuery(ITypeDesc arg0) {
		// TODO Auto-generated method stub
		
		System.out.println("____ toSQLQuery is called");
		return null;
	}


	

}
