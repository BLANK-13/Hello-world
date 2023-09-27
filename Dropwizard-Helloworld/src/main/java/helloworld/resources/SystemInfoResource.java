package helloworld.resources;

import helloworld.api.Processor;
import helloworld.api.SystemInfo;
import helloworld.core.SystemInfoService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/v1/system/info")
@Produces(MediaType.APPLICATION_JSON)
public class SystemInfoResource {

    private final SystemInfoService systemInfoService;

    public SystemInfoResource(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSystemInfo() {

        List<Processor> cpus = systemInfoService.getProcessors();
        int totalCpus = cpus.size();


        return Response.ok(new SystemInfo(cpus, totalCpus)).build();
    }
}
