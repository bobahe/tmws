package ru.levin.tmws.service;

import org.apache.deltaspike.jpa.api.entitymanager.PersistenceUnitName;
import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.service.IPropertyService;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.dto.UserDTO;
import ru.levin.tmws.entity.Project;
import ru.levin.tmws.entity.Session;
import ru.levin.tmws.entity.Task;
import ru.levin.tmws.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class EntityManagerProducer {

    @NotNull
    private static final String UNIT_NAME = "ENTERPRISE";

    @Inject
    @Named("entityManagerFactory")
    @PersistenceUnitName(UNIT_NAME)
    private EntityManagerFactory entityManagerFactory;

    @NotNull
    @Produces
    @TransactionScoped
    public EntityManager createEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) entityManager.close();
    }

    @Produces
    @ApplicationScoped
    @Named("entityManagerFactory")
    @PersistenceUnitName("ENTERPRISE")
    private EntityManagerFactory getEntityManagerFactory(@NotNull final IPropertyService propertyService) {
        propertyService.init();
        @NotNull final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, propertyService.getJdbcDriver());
        settings.put(Environment.URL, propertyService.getJdbcUrl());
        settings.put(Environment.USER, propertyService.getJdbcUsername());
        settings.put(Environment.PASS, propertyService.getJdbcPassword());
        settings.put(Environment.DIALECT, propertyService.getJdbcDialect());
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
