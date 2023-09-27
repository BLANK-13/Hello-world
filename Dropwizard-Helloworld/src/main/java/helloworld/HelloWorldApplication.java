package helloworld;

import helloworld.core.NetworkInfoService;
import helloworld.core.SystemInfoService;
import helloworld.health.NetworkInfoHealthCheck;
import helloworld.health.SystemInfoHealthCheck;
import helloworld.health.TemplateHealthCheck;
import helloworld.resources.HelloWorldResource;
import helloworld.resources.NetworkInfoResource;
import helloworld.resources.SystemInfoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "HelloWorld";
    }

    @Override
    public void initialize(final Bootstrap<HelloWorldConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final HelloWorldConfiguration configuration, final Environment environment) {

        /// Resources
        HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
        environment.jersey().register(resource);

        SystemInfoResource systemInfoResource = new SystemInfoResource(new SystemInfoService()); /// <- do we use a new instance here or go through configuration?
        environment.jersey().register(systemInfoResource);

        NetworkInfoResource networkInfoResource = new NetworkInfoResource(new NetworkInfoService());
        environment.jersey().register(networkInfoResource);

        /// Health Checks
        TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        SystemInfoHealthCheck systemInfoHealthCheck = new SystemInfoHealthCheck();
        environment.healthChecks().register("system info", systemInfoHealthCheck);

        NetworkInfoHealthCheck networkInfoHealthCheck = new NetworkInfoHealthCheck();
        environment.healthChecks().register("network info", networkInfoHealthCheck);

    }

}
