package ru.levin.tmws.context;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IEndpoint;
import ru.levin.tmws.api.service.*;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.dto.UserDTO;
import ru.levin.tmws.entity.*;
import ru.levin.tmws.service.*;

import javax.persistence.EntityManagerFactory;
import javax.xml.ws.Endpoint;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private EntityManagerFactory entityManagerFactory = getEntityManagerFactory();

    @NotNull
    @Getter
    private final IProjectService projectService = new ProjectService(entityManagerFactory);

    @NotNull
    @Getter
    private final ITaskService taskService = new TaskService(entityManagerFactory);

    @NotNull
    @Getter
    private final IUserService userService = new UserService(entityManagerFactory);

    @NotNull
    @Getter
    private final ISessionService sessionService = new SessionService(entityManagerFactory);

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
        if (userService.findById("38aca227-dc47-459f-bc0b-2134e260135c") == null) {
            @NotNull final UserDTO admin = new UserDTO();
            admin.setId("38aca227-dc47-459f-bc0b-2134e260135c");
            admin.setLogin("admin");
            admin.setPassword("admin");
            admin.setRoleType(RoleType.ADMIN);
            userService.save(admin);
        }

        if (userService.findById("54d01220-0ecd-4ca2-a300-ff6ffc9b1254") == null) {
            @NotNull final UserDTO user = new UserDTO();
            user.setId("54d01220-0ecd-4ca2-a300-ff6ffc9b1254");
            user.setLogin("user");
            user.setPassword("user");
            user.setRoleType(RoleType.USER);
            userService.save(user);
        }
    }

    private EntityManagerFactory getEntityManagerFactory() {
        propertyService.init();
        @NotNull final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, propertyService.getJdbcDriver());
        settings.put(Environment.URL, propertyService.getJdbcUrl());
        settings.put(Environment.USER, propertyService.getJdbcUsername());
        settings.put(Environment.PASS, propertyService.getJdbcPassword());
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.HBM2DDL_CHARSET_NAME, "utf8");
        @NotNull final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        final StandardServiceRegistry registry = registryBuilder.build();
        final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Task.class);
        sources.addAnnotatedClass(Project.class);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        sources.addAnnotatedClass(TaskDTO.class);
        sources.addAnnotatedClass(ProjectDTO.class);
        sources.addAnnotatedClass(UserDTO.class);
        sources.addAnnotatedClass(SessionDTO.class);
        final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

}
