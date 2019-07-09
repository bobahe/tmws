package ru.levin.tmws.command.persist;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IAdminEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class FasterJsonLoadCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IAdminEndpoint adminEndpoint;

    public FasterJsonLoadCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.adminEndpoint = serviceLocator.getAdminService().getAdminEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "load-fxml-json";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[LOAD FASTERXML JSON]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Unmarshal data from json via FasterXML";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        adminEndpoint.loadFxmlJson(serviceLocator.getCurrentSession());
    }

}
