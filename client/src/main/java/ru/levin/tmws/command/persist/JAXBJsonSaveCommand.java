package ru.levin.tmws.command.persist;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IAdminEndpoint;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

@Component
public final class JAXBJsonSaveCommand extends AbstractCommand {

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
    public JAXBJsonSaveCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    @NotNull
    public String getName() {
        return "save-jaxb-json";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "[SAVE JAXB JSON]";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Marshal data into json via JAXB";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return true;
    }

    @Override
    public void execute() {
        terminalService.println(getTitle());
        adminEndpoint.saveJaxbJson(serviceLocator.getCurrentSession());
    }

}
