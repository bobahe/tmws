package ru.levin.tmws.command.persist;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IAdminEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

@Component
public final class FasterXmlSaveCommand extends AbstractCommand {

    @NotNull
    private ITerminalService terminalService;
    @Autowired
    public void setTerminalService(@NotNull final ITerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @NotNull
    private IAdminEndpoint adminEndpoint;
    @Autowired
    public void setAdminEndpoint(@NotNull final IAdminEndpoint adminEndpoint) {
        this.adminEndpoint = adminEndpoint;
    }

    @Autowired
    public FasterXmlSaveCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
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
