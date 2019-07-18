package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.endpoint.*;
import ru.levin.tmws.api.service.IEndpointProducer;
import ru.levin.tmws.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class EndpointProducerService implements IEndpointProducer {

    @NotNull
    @Produces
    public IAdminEndpoint getAdminEndpoint() {
        return new AdminEndpointService().getAdminEndpointPort();
    }

    @NotNull
    @Produces
    public IProjectEndpoint getProjectEndpoint() {
        return new ProjectEndpointService().getProjectEndpointPort();
    }

    @NotNull
    @Produces
    public ISessionEndpoint getSessionEndpoint() {
        return new SessionEndpointService().getSessionEndpointPort();
    }

    @NotNull
    @Produces
    public ITaskEndpoint getTaskEndpoint() {
        return new TaskEndpointService().getTaskEndpointPort();
    }

    @NotNull
    @Produces
    public IUserEndpoint getUserEndpoint() {
        return new UserEndpointService().getUserEndpointPort();
    }

    @NotNull
    @Produces
    public IServerEndpoint getServerEndpoint() {
        return new ServerEndpointService().getServerEndpointPort();
    }

}
