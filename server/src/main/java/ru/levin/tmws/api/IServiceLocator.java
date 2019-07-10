package ru.levin.tmws.api;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.service.*;

public interface IServiceLocator {

    @NotNull IProjectService getProjectService();
    @NotNull ITaskService getTaskService();
    @NotNull IUserService getUserService();
    @NotNull ISessionService getSessionService();
    @NotNull IPersistService getPersistService();

}
