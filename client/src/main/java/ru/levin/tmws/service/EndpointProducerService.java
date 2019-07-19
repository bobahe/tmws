package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.levin.tmws.api.endpoint.*;
import ru.levin.tmws.api.service.IEndpointProducer;
import ru.levin.tmws.endpoint.*;

@Configuration
public class EndpointProducerService implements IEndpointProducer {

    @NotNull
    @Bean
    public IAdminEndpoint getAdminEndpoint() {
        return new AdminEndpointService().getAdminEndpointPort();
    }

    @NotNull
    @Bean
    public IProjectEndpoint getProjectEndpoint() {
        return new ProjectEndpointService().getProjectEndpointPort();
    }

    @NotNull
    @Bean
    public ISessionEndpoint getSessionEndpoint() {
        return new SessionEndpointService().getSessionEndpointPort();
    }

    @NotNull
    @Bean
    public ITaskEndpoint getTaskEndpoint() {
        return new TaskEndpointService().getTaskEndpointPort();
    }

    @NotNull
    @Bean
    public IUserEndpoint getUserEndpoint() {
        return new UserEndpointService().getUserEndpointPort();
    }

    @NotNull
    @Bean
    public IServerEndpoint getServerEndpoint() {
        return new ServerEndpointService().getServerEndpointPort();
    }

}
