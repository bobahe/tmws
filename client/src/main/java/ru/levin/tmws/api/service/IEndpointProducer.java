package ru.levin.tmws.api.service;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.endpoint.*;

public interface IEndpointProducer {

    @NotNull IAdminEndpoint getAdminEndpoint() ;

    @NotNull IProjectEndpoint getProjectEndpoint();

    @NotNull ISessionEndpoint getSessionEndpoint();

    @NotNull ITaskEndpoint getTaskEndpoint();

    @NotNull IUserEndpoint getUserEndpoint();

}
