package ru.levin.tmws.command.persist;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IAdminEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

@Component
public final class JAXBXmlSaveCommand extends AbstractCommand {

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
    public JAXBXmlSaveCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @NotNull
    public String getName() {
        return "save-jaxb-xml";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[SAVE JAXB XML]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Marshal data into xml via JAXB";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        adminEndpoint.saveJaxbXml(serviceLocator.getCurrentSession());
    }

}
