package gs.example;

import org.openspaces.admin.Admin;
import org.openspaces.admin.gsc.GridServiceContainer;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.rest.CustomManagerResource;
import org.openspaces.admin.rest.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;


/**
 * Created by aharon on 10/23/17.
 */
//Use the following link to execute:
//http://localhost:8090/v1/demo/report?hostname=aharon-osx.local

@CustomManagerResource
@Path("/demo")
public class restAPImethod {
        @Context
        Admin admin;

    @GET
    @Path("/report")
    public Response  report(@QueryParam("hostname") String hostname) {
        StringBuilder sb = new StringBuilder();
        sb.append ("Custom report: host=" + hostname+"\n");
        for (GridServiceContainer gsc : admin.getGridServiceContainers()) {
            System.out.println("GSC [" + gsc.getUid() + "] running on Machine "
                    + gsc.getMachine().getHostAddress());
            sb.append("GSC [" + gsc.getId() + "] running on Machine "
                    + gsc.getMachine().getHostAddress()+"\n");
            for (ProcessingUnitInstance puInstance : gsc
                    .getProcessingUnitInstances()) {
                System.out.println("   -> PU [" + puInstance.getName() + "]["
                        + puInstance.getInstanceId() + "]["
                        + puInstance.getBackupId() + "]");
                sb.append("   -> PU [" + puInstance.getName() + "]["
                        + puInstance.getInstanceId() + "]["
                        + puInstance.getBackupId() + "]"+"\n");
            }
        }
        return Response.ok().entity(sb.toString()).build();
    }
}


