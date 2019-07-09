package ru.levin.tmws.server.context;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.server.api.IServiceLocator;
import ru.levin.tmws.server.api.endpoint.IEndpoint;
import ru.levin.tmws.server.api.repository.IProjectRepository;
import ru.levin.tmws.server.api.repository.ISessionRepository;
import ru.levin.tmws.server.api.repository.ITaskRepository;
import ru.levin.tmws.server.api.repository.IUserRepository;
import ru.levin.tmws.server.api.service.*;
import ru.levin.tmws.server.entity.RoleType;
import ru.levin.tmws.server.entity.User;
import ru.levin.tmws.server.repository.ProjectRepository;
import ru.levin.tmws.server.repository.SessionRepository;
import ru.levin.tmws.server.repository.TaskRepository;
import ru.levin.tmws.server.repository.UserRepository;
import ru.levin.tmws.server.service.*;

import javax.xml.ws.Endpoint;
import java.lang.reflect.Constructor;

@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final ITaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final IUserRepository userRepository = new UserRepository();

    @NotNull
    private final ISessionRepository sessionRepository = new SessionRepository();

    @NotNull
    @Getter
    private final IProjectService projectService = new ProjectService(projectRepository);

    @NotNull
    @Getter
    private final ITaskService taskService = new TaskService(taskRepository);

    @NotNull
    @Getter
    private final IUserService userService = new UserService(userRepository);

    @NotNull
    @Getter
    private final ISessionService sessionService = new SessionService(sessionRepository);

    @NotNull
    @Getter
    private final IPersistService persistService = new PersistService();

    public void init(@NotNull final Class<?>[] endpoints) {
        createDefaultUsers();
        publishEndpoints(endpoints);
    }

    private void publishEndpoints(@NotNull final Class<?>[] endpoints) {
        for (Class<?> endpointClass : endpoints) {
            if (endpointClass.isInterface()) continue;
            if (endpointClass.getSuperclass().equals(IEndpoint.class)) continue;
            try {
                @NotNull final Constructor<?> constructor = endpointClass.getConstructor(IServiceLocator.class);
                @NotNull final IEndpoint instance = (IEndpoint) constructor.newInstance(this);
                Endpoint.publish("http://localhost:8080/tm/" + endpointClass.getSimpleName(), instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createDefaultUsers() {
        @NotNull final User admin = new User();
        admin.setId("38aca227-dc47-459f-bc0b-2134e260135c");
        admin.setLogin("admin");
        admin.setPassword("admin");
        admin.setRoleType(RoleType.ADMIN);

        @NotNull final User user = new User();
        user.setId("54d01220-0ecd-4ca2-a300-ff6ffc9b1254");
        user.setLogin("user");
        user.setPassword("user");
        user.setRoleType(RoleType.USER);

        userService.save(admin);
        userService.save(user);
    }

}
