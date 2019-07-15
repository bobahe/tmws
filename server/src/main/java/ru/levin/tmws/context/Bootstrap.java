package ru.levin.tmws.context;

import lombok.NoArgsConstructor;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.endpoint.*;
import ru.levin.tmws.api.service.IPropertyService;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.dto.UserDTO;
import ru.levin.tmws.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@ApplicationScoped
public class Bootstrap {

    @Nullable
    @Inject
    private IPropertyService propertyService;

    @Nullable
    @Inject
    private ISessionEndpoint sessionEndpoint;

    @Nullable
    @Inject
    private IAdminEndpoint adminEndpoint;

    @Nullable
    @Inject
    private IProjectEndpoint projectEndpoint;

    @Nullable
    @Inject
    private ITaskEndpoint taskEndpoint;

    @Nullable
    @Inject
    private IUserEndpoint userEndpoint;

    @Nullable
    @Inject
    private IUserService userService;

    public void init() {
        createDefaultUsers();
        publishEndpoints();
    }

    private void publishEndpoints() {
        @NotNull final String url = "http://localhost:8080/tm/";
        Endpoint.publish(url + "SessionEndpoint", sessionEndpoint);
        Endpoint.publish(url + "AdminEndpoint", adminEndpoint);
        Endpoint.publish(url + "ProjectEndpoint", projectEndpoint);
        Endpoint.publish(url + "TaskEndpoint", taskEndpoint);
        Endpoint.publish(url + "UserEndpoint", userEndpoint);
    }

    private void createDefaultUsers() {
        if (userService == null) return;
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

    @Produces
    @ApplicationScoped
    private EntityManagerFactory getEntityManagerFactory() {
        if (propertyService == null) return null;
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
