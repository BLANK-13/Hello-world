package helloworld.resources;


import helloworld.api.NetworkInfo;
import helloworld.api.Processor;
import helloworld.api.SystemInfo;
import helloworld.core.NetworkInfoService;
import helloworld.core.SystemInfoService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/network/info")
@Produces(MediaType.APPLICATION_JSON)
public class NetworkInfoResource {

    private final NetworkInfoService networkInfoService;

    public NetworkInfoResource(NetworkInfoService networkInfoService) {
        this.networkInfoService = networkInfoService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNetworkInfo() {

        NetworkInfo networkInfo = networkInfoService.getInterfacesInfo();

        return networkInfo != null ? Response.ok(networkInfo).build() : Response.serverError().build();
    }
}
