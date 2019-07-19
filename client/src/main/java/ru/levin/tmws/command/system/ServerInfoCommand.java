package ru.levin.tmws.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IServerEndpoint;
import ru.levin.tmws.api.endpoint.ServerInfoDTO;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;
import ru.levin.tmws.exception.NoSuchItemException;

@Component
public final class ServerInfoCommand extends AbstractCommand {

    @NotNull
    private ITerminalService terminalService;
    @Autowired
    public void setTerminalService(@NotNull final ITerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @NotNull
    private IServerEndpoint serverEndpoint;
    @Autowired
    public void setServerEndpoint(@NotNull final IServerEndpoint serverEndpoint) {
        this.serverEndpoint = serverEndpoint;
    }

    @Autowired
    public ServerInfoCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
        return true;
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
