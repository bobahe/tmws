package ru.levin.tmws.command.persist;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IAdminEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class FasterXmlSaveCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    @NotNull
    private final IAdminEndpoint adminEndpoint;

    public FasterXmlSaveCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
        this.adminEndpoint = serviceLocator.getAdminService().getAdminEndpointPort();
    }

    @Override
    @NotNull
    public String getName() {
        return "save-fxml-xml";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[SAVE FASTERXML XML]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Marshal data into xml via FasterXML";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        adminEndpoint.saveFxmlXml(serviceLocator.getCurrentSession());
    }

}
