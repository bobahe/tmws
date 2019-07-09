package ru.levin.tmws.client.command.persist;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.server.api.endpoint.IAdminEndpoint;

public final class LoadSerializedCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IAdminEndpoint adminEndpoint;

    public LoadSerializedCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.adminEndpoint = bootstrap.getAdminService().getAdminEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "load-serialized";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[LOAD SERIALIZED DATA]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Deserialize data from file";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        adminEndpoint.deserialize(bootstrap.getCurrentSession());
    }

}
