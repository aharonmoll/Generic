package tst;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.gigaspaces.security.Authority;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;

import com.gigaspaces.internal.query.CompoundAndCustomQuery;
import com.gigaspaces.internal.query.ICustomQuery;
import com.gigaspaces.internal.server.storage.ITemplateHolder;
import com.gigaspaces.internal.server.storage.TemplateHolder;
import com.j_spaces.core.EntryImpl;
import com.j_spaces.core.IJSpace;
import com.j_spaces.core.SpaceContext;
import com.j_spaces.core.filters.FilterOperationCodes;
import com.j_spaces.core.filters.ISpaceFilter;
import com.j_spaces.core.filters.entry.ISpaceFilterEntry;

public class CounterPartyFilter implements ISpaceFilter {
	private Field entryHolder;
	private Method customQuerySetter;
	// late context is needed since there's a circular dependency between the
	// filter bean and the space itself

	private GigaSpace space;

	private CustomQueryFactory customeQueryFactory = new CustomQueryFactory();

	public void init(IJSpace ijSpace, String s, String s1, int i) throws RuntimeException {

		try {
			System.out.println("================Filter init2======================");
			entryHolder = EntryImpl.class.getDeclaredField("_entryHolder");
			entryHolder.setAccessible(true);
			customQuerySetter = TemplateHolder.class.getDeclaredMethod("setCustomQuery", ICustomQuery.class);
			customQuerySetter.setAccessible(true);
			this.space = new GigaSpaceConfigurer(ijSpace).gigaSpace();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void process(SpaceContext spaceContext, ISpaceFilterEntry filterEntry, int operationCode) throws RuntimeException {
		try {

			final Authority[] authorities = spaceContext.getSecurityContext().getUserDetails().getAuthorities();
			authorities[0].getAuthority();

			if (operationCode == FilterOperationCodes.BEFORE_READ || operationCode == FilterOperationCodes.BEFORE_READ_MULTIPLE) {
				System.out.println("====== process0 =======: +spaceContext: " + spaceContext);				
				filterEntry.getClassName();
				SRDCustomQuery customQuery = customeQueryFactory.getCustomeQueryForType(filterEntry.getClassName());
				if (customQuery != null) {
					ITemplateHolder templateHolder;
					templateHolder = (ITemplateHolder) entryHolder.get(filterEntry);
					customQuery.setGigaspace(space);
					customQuery.setSpaceContext(spaceContext);
					ICustomQuery filterQuery = customQuery;
					ICustomQuery templateCustomQuery = templateHolder.getCustomQuery();						
					if (templateCustomQuery != null) {
						filterQuery = new CompoundAndCustomQuery(templateCustomQuery, filterQuery);
					}
					customQuerySetter.invoke(templateHolder, filterQuery);
				}
			}			
		}														
		 catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void process(SpaceContext paramSpaceContext, ISpaceFilterEntry[] paramArrayOfISpaceFilterEntry, int paramInt) throws RuntimeException {
		// TODO Auto-generated method stub

	}

	public void close() throws RuntimeException {
		// TODO Auto-generated method stub

	}

}
