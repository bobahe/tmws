package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IUserRepository;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.UserDTO;
import ru.levin.tmws.entity.RoleType;
import ru.levin.tmws.exception.InternalServiceException;
import ru.levin.tmws.exception.UpdateException;
import ru.levin.tmws.repository.UserRepository;
import ru.levin.tmws.util.ServiceUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@ApplicationScoped
public class UserService extends AbstractEntityService<UserDTO> implements IUserService {

    @Nullable
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Override
    public @NotNull List<UserDTO> getAll() {
        if (entityManagerFactory == null) throw new InternalServiceException();
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository repository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        @NotNull final List<UserDTO> user = repository.findAll();
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    @Nullable
    public UserDTO save(@Nullable final UserDTO entity) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;
        entity.setPassword(ServiceUtil.md5(entity.getPassword()));
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository repository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    @Nullable
    public UserDTO update(@Nullable final UserDTO entity) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository repository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    public boolean remove(final @Nullable UserDTO entity) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (entity == null) return false;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository repository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.remove(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean removeAll() {
        if (entityManagerFactory == null) throw new InternalServiceException();
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository repository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.removeAll();
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Nullable
    @Override
    public UserDTO findOneById(final @Nullable String id) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (id == null) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository repository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        @Nullable final UserDTO user = repository.findOne(id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    @Nullable
    public UserDTO getUserByLoginAndPassword(@Nullable final String login, @Nullable final String password) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        @NotNull final String hash = ServiceUtil.md5(password);
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository repository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        @Nullable final UserDTO user = repository.findOneByLoginAndPassword(login, hash);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    @Nullable
    public UserDTO setNewPassword(@Nullable final UserDTO user, @Nullable final String password) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (password == null || password.isEmpty()) return null;
        if (user == null) return null;
        @NotNull final String hash = ServiceUtil.md5(password);
        user.setPassword(hash);
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository repository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public @Nullable UserDTO findById(final @Nullable String id) {
        if (id == null) return null;
        return findOneById(id);
    }

    @Override
    public void changeUserRole(@Nullable final UserDTO user, final @Nullable String role) {
        if (entityManagerFactory == null) throw new InternalServiceException();
        if (role == null || role.isEmpty()) throw new UpdateException();
        if (user == null || user.getId() == null) throw new UpdateException();
        @NotNull final RoleType roleType = RoleType.valueOf(role);
        user.setRoleType(roleType);
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository repository = new UserRepository(entityManager);
        entityManager.getTransaction().begin();
        repository.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
