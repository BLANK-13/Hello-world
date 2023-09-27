package helloworld.health;

import com.codahale.metrics.health.HealthCheck;
import helloworld.api.NetworkInfo;
import helloworld.core.NetworkInfoService;

public class NetworkInfoHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {

        NetworkInfoService networkInfoService = new NetworkInfoService();
        NetworkInfo networkInfoResource = networkInfoService.getInterfacesInfo();

        if (networkInfoResource.getInterface1().isEmpty() || networkInfoResource.getInterface2().isEmpty()) {
            return Result.unhealthy("There was a problem with getting the network info");
        }

        return Result.healthy();
    }
}
