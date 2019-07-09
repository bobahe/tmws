package ru.levin.tmws;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.command.persist.*;
import ru.levin.tmws.command.project.*;
import ru.levin.tmws.command.system.AboutCommand;
import ru.levin.tmws.command.system.HelpCommand;
import ru.levin.tmws.command.task.*;
import ru.levin.tmws.command.user.*;
import ru.levin.tmws.context.Bootstrap;

public final class Application {

    @NotNull
    private static final Class[] COMMANDS = {
            HelpCommand.class, UserLoginCommand.class, UserRegisterCommand.class,
            AboutCommand.class, ProjectListCommand.class, ProjectCreateCommand.class,
            ProjectFindCommand.class, ProjectSelectCommand.class, ProjectRemoveAllCommand.class,
            ProjectChangeSelectedCommand.class, ProjectRemoveSelectedCommand.class, TaskProjectTaskListCommand.class,
            TaskRemoveAllCommand.class, TaskCreateCommand.class, TaskFindCommand.class, TaskSelectCommand.class,
            TaskListCommand.class, TaskChangeSelectedCommand.class, TaskRemoveSelectedCommand.class,
            TaskJoinCommand.class, UserLogoutCommand.class, UserChangePasswordCommand.class,
            UserShowProfileCommand.class, UserEditProfileCommand.class, SerializedSaveCommand.class,
            SerializedLoadCommand.class, JAXBXmlSaveCommand.class, JAXBXmlLoadCommand.class,
            JAXBJsonSaveCommand.class, JAXBJsonLoadCommand.class, FasterXmlSaveCommand.class,
            FasterXmlLoadCommand.class, FasterJsonSaveCommand.class, FasterJsonLoadCommand.class
    };

    public static void main(String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(COMMANDS);
    }

}
