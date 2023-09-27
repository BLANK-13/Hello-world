package helloworld.health;

import com.codahale.metrics.health.HealthCheck;
import helloworld.api.Processor;
import helloworld.core.SystemInfoService;

import java.util.List;

public class SystemInfoHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {

        SystemInfoService systemInfoService = new SystemInfoService();
        List<Processor> cpus = systemInfoService.getProcessors();

        if (cpus.isEmpty()) {
            return Result.unhealthy("There was a problem with getting the processors info");
        }

        return Result.healthy();
    }
}
