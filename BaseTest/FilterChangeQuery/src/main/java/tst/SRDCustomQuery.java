package tst;
import com.gigaspaces.internal.metadata.ITypeDesc;
import com.gigaspaces.internal.query.ICustomQuery;
import com.gigaspaces.internal.query.IQueryIndexScanner;
import com.gigaspaces.server.ServerEntry;
import com.j_spaces.core.SpaceContext;
import com.j_spaces.core.cache.CacheManager;
import com.j_spaces.core.client.SQLQuery;
import org.openspaces.core.GigaSpace;

import java.util.List;


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

	public boolean matches(CacheManager cacheManager, ServerEntry serverEntry, String s) {
		return false;
	}

	public boolean matches(ServerEntry serverEntry) {
		// TODO Auto-generated method stub
		System.out.println("____ matches is called");
		final String username = spaceContext.getSecurityContext().getUserDetails().getUsername();
		System.out.println("User name in Space Filter is: "+username);
		final String data = (String)serverEntry.getPropertyValue("data");
		if (username.equals("am")&& data.contains("am"))
			return true;
		if (username.equals("st")&& data.contains("st"))
			return  true;
		return false;
	}

	public SQLQuery toSQLQuery(ITypeDesc arg0) {
		// TODO Auto-generated method stub
		
		System.out.println("____ toSQLQuery is called");
		return null;
	}


	

}
