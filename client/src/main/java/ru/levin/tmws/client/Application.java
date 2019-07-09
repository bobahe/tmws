package ru.levin.tmws.client;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.client.command.persist.*;
import ru.levin.tmws.client.command.project.*;
import ru.levin.tmws.client.command.system.AboutCommand;
import ru.levin.tmws.client.command.system.HelpCommand;
import ru.levin.tmws.client.command.task.*;
import ru.levin.tmws.client.command.user.*;
import ru.levin.tmws.client.context.Bootstrap;

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
            UserShowProfileCommand.class, UserEditProfileCommand.class, SaveSerializedCommand.class,
            LoadSerializedCommand.class, SaveJAXBXmlCommand.class, LoadJAXBXmlCommand.class,
            SaveJAXBJsonCommand.class, LoadJAXBJsonCommand.class, SaveFasterXmlCommand.class,
            LoadFasterXmlCommand.class, SaveFasterJsonCommand.class, LoadFasterJsonCommand.class
    };

    public static void main(String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(COMMANDS);
    }

}
