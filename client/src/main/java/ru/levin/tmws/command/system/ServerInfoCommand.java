package ru.levin.tmws.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IServerEndpoint;
import ru.levin.tmws.api.endpoint.ServerInfoDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSuchItemException;

public final class ServerInfoCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IServerEndpoint serverEndpoint;

    public ServerInfoCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        serverEndpoint = serviceLocator.getServerService().getServerEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "server-info";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[SERVER INFORMATION]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Show info about server";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return false;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        @Nullable final ServerInfoDTO serverInfo = serverEndpoint.getServerInfo();
        if (serverInfo == null) throw new NoSuchItemException();
        terminalService.println("HOST: " + serverInfo.getHostname());
        terminalService.println("PORT: " + serverInfo.getPort());
    }

}
