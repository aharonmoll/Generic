package com.gigaspaces.test;

import com.j_spaces.core.IJSpace;
import com.j_spaces.core.SpaceContext;
import com.j_spaces.core.filters.FilterOperationCodes;
import com.j_spaces.core.filters.ISpaceFilter;
import com.j_spaces.core.filters.entry.ISpaceFilterEntry;


public class SimpleFilter implements ISpaceFilter {
    public void init(IJSpace space, String filterId, String url, int priority) throws RuntimeException {

    }

    public void process(SpaceContext context, ISpaceFilterEntry entry, int operationCode) throws RuntimeException {
//        System.out.println(context.getSecurityContext().getUserDetails().getUsername());//user namer
//        context.getSecurityContext().getUserDetails().getAuthorities();//What the user permit to do

        System.out.println("In process filter method");
        if (operationCode==FilterOperationCodes.BEFORE_WRITE){

            System.out.println("Before Write filter");

            try{
                Thread.sleep(12);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

//        if (operationCode==FilterOperationCodes.BEFORE_UPDATE)
//        {
//            if (entry.getClassName().equals("Trade"))
//                throw (new RuntimeException("Update is not allowed"));
//        }
//        if (operationCode== FilterOperationCodes.BEFORE_CHANGE)
//        {
//            System.out.println("Before Change filter");
//            if (entry.getClassName().equals("Trade")) {
//                Collection<ChangeOperation> operations = ((SpaceBeforeChangeFilterEntryImpl) entry).getOperations();
//                for (ChangeOperation operation : operations) {
//                    if (operation.getName().equals("set")) {
//                        if (((SetValueSpaceEntryMutator) operation).getPath().equals("latest"))
//                            System.out.println("set operation");
//                        else throw (new RuntimeException("Change is not allowed"));
//                    } else throw (new RuntimeException("Change is not allowed"));
//                }
//            }
//        }
//        if (operationCode==FilterOperationCodes.AFTER_READ)
//        {
//            entry=null;
//        }

    }

    public void process(SpaceContext context, ISpaceFilterEntry[] entries, int operationCode) throws RuntimeException {

//        if (operationCode==FilterOperationCodes.AFTER_READ)
//        {
//            entries=null;
//        }

    }

    public void close() throws RuntimeException {

    }
}
