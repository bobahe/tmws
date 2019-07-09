package ru.levin.tmws.client.command.persist;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.api.IServiceLocator;
import ru.levin.tmws.client.api.service.ITerminalService;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.server.api.endpoint.IAdminEndpoint;

public final class LoadJAXBXmlCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IAdminEndpoint adminEndpoint;

    public LoadJAXBXmlCommand(@NotNull final IServiceLocator bootstrap) {
        super(bootstrap);
        this.terminalService = bootstrap.getTerminalService();
        this.adminEndpoint = bootstrap.getAdminService().getAdminEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "load-jaxb-xml";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[LOAD JAXB XML]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Unmarshal data from xml via JAXB";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        adminEndpoint.loadJaxbXml(bootstrap.getCurrentSession());
    }

}
