package gs.example;

import org.openspaces.admin.Admin;
import org.openspaces.admin.gsc.GridServiceContainer;
import org.openspaces.admin.machine.Machine;
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
@CustomManagerResource
@Path("/demo")
public class restAPImethod {
        @Context
        Admin admin;

//        @GET
//        @Path("/report")
//        public String report(@QueryParam("hostname") String hostname) {
//            Machine machine = admin.getMachines().getMachineByHostName(hostname);
//            return "Custom report: host=" + hostname +
//                    ", containers=" + machine.getGridServiceContainers() +
//                    ", PU instances=" + machine.getProcessingUnitInstances();
//        }

//    @GET
//    @Path("/report")
//    public Response report(@QueryParam("hostname") String hostname) {
//        Machine machine = admin.getMachines().getMachineByHostName(hostname);
//        if (machine == null)
//            return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).entity("Host not found").build();
//        String result = "Custom report: host=" + hostname +
//                ", containers=" + machine.getGridServiceContainers() +
//                ", PU instances=" + machine.getProcessingUnitInstances();
//        return Response.ok().entity(result).build();
//    }

    @GET
    @Path("/report")
    public Response  report(@QueryParam("hostname") String hostname) {
        for (GridServiceContainer gsc : admin.getGridServiceContainers()) {
            System.out.println("GSC [" + gsc.getUid() + "] running on Machine "
                    + gsc.getMachine().getHostAddress());
            for (ProcessingUnitInstance puInstance : gsc
                    .getProcessingUnitInstances()) {
                System.out.println("   -> PU [" + puInstance.getName() + "]["
                        + puInstance.getInstanceId() + "]["
                        + puInstance.getBackupId() + "]");
            }
        }
        Machine machine = admin.getMachines().getMachineByHostName(hostname);
        if (machine == null)
            return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).entity("Host not found").build();
        String result = "Custom report: host=" + hostname +
                ", containers=" + machine.getGridServiceContainers() +
                ", PU instances=" + machine.getProcessingUnitInstances();
        return Response.ok().entity(result).build();
    }
    }


