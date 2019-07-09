package ru.levin.tmws.command.system;

import com.jcabi.manifests.Manifests;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.service.ITerminalService;
import ru.levin.tmws.command.AbstractCommand;

public final class AboutCommand extends AbstractCommand {

    @NotNull
    private final ITerminalService terminalService;

    public AboutCommand(@NotNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
        this.terminalService = serviceLocator.getTerminalService();
    }

    @Override
    @NotNull
    public String getName() {
        return "about";
    }

    @Override
    @NotNull
    public String getTitle() {
        return "";
    }

    @Override
    @NotNull
    public String getDescription() {
        return "Show info about app";
    }

    @Override
    public boolean isRequiredAuthorization() {
        return false;
    }

    @Override
    public void execute() {
        @NotNull final String buildDeveloper = Manifests.read("Build-Developer");
        @NotNull final String buildVersion = Manifests.read("Build-Version");
        @NotNull final String buildNumber = Manifests.read("Build-Number");
        terminalService.println("[ABOUT APPLICATION]");
        terminalService.println(buildDeveloper);
        terminalService.println(buildVersion);
        terminalService.println(buildNumber);
    }

}
