package ru.levin.tmws.command.persist;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IAdminEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class JAXBJsonLoadCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IAdminEndpoint adminEndpoint;

    public JAXBJsonLoadCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.adminEndpoint = serviceLocator.getAdminService().getAdminEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "load-jaxb-json";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[LOAD JAXB JSON]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Unmarshal data from json via JAXB";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        adminEndpoint.loadJaxbJson(serviceLocator.getCurrentSession());
    }

}
