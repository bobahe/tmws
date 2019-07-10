package ru.levin.tmws.context;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.api.endpoint.IEndpoint;
import ru.levin.tmws.api.repository.IProjectRepository;
import ru.levin.tmws.api.repository.ISessionRepository;
import ru.levin.tmws.api.repository.ITaskRepository;
import ru.levin.tmws.api.repository.IUserRepository;
import ru.levin.tmws.api.service.*;
import ru.levin.tmws.entity.RoleType;
import ru.levin.tmws.entity.User;
import ru.levin.tmws.service.*;

import javax.sql.DataSource;
import javax.xml.ws.Endpoint;
import java.lang.reflect.Constructor;

@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private SqlSessionFactory sessionFactory = getSqlSessionFactory();

    @NotNull
    @Getter
    private final IProjectService projectService = new ProjectService(sessionFactory);

    @NotNull
    @Getter
    private final ITaskService taskService = new TaskService(sessionFactory);

    @NotNull
    @Getter
    private final IUserService userService = new UserService(sessionFactory);

    @NotNull
    @Getter
    private final ISessionService sessionService = new SessionService(sessionFactory);

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
            @NotNull final User admin = new User();
            admin.setId("38aca227-dc47-459f-bc0b-2134e260135c");
            admin.setLogin("admin");
            admin.setPassword("admin");
            admin.setRoleType(RoleType.ADMIN);
            userService.save(admin);
        }

        if (userService.findById("54d01220-0ecd-4ca2-a300-ff6ffc9b1254") == null) {
            @NotNull final User user = new User();
            user.setId("54d01220-0ecd-4ca2-a300-ff6ffc9b1254");
            user.setLogin("user");
            user.setPassword("user");
            user.setRoleType(RoleType.USER);
            userService.save(user);
        }
    }

    private SqlSessionFactory getSqlSessionFactory() {
        propertyService.init();
        @Nullable final String user = propertyService.getJdbcUsername();
        @Nullable final String password = propertyService.getJdbcPassword();
        @Nullable final String url = propertyService.getJdbcUrl();
        @Nullable final String driver = propertyService.getJdbcDriver();
        final DataSource dataSource = new PooledDataSource(driver, url, user, password);
        final TransactionFactory transactionFactory = new JdbcTransactionFactory();
        final Environment environment = new Environment("development", transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
        configuration.addMapper(IUserRepository.class);
        configuration.addMapper(IProjectRepository.class);
        configuration.addMapper(ISessionRepository.class);
        configuration.addMapper(ITaskRepository.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }

}
