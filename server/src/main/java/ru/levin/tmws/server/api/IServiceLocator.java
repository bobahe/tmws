package ru.levin.tmws.server.api;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.server.api.service.*;

import java.sql.Connection;

public interface IServiceLocator {

    @NotNull IProjectService getProjectService();
    @NotNull ITaskService getTaskService();
    @NotNull IUserService getUserService();
    @NotNull ISessionService getSessionService();
    @NotNull IPersistService getPersistService();
    @NotNull Connection getDbConnection();

}
