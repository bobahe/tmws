package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.entity.RoleType;
import ru.levin.tmws.entity.User;
import ru.levin.tmws.exception.UpdateException;
import ru.levin.tmws.util.ServiceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public final class UserService extends AbstractEntityService<User> implements IUserService {

    public UserService(@NotNull final EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public @NotNull List<User> getAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final List<User> user = entityManager
                .createQuery("from User ", User.class)
                .getResultList();
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    @Nullable
    public User save(@Nullable final User entity) {
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;

        entity.setPassword(ServiceUtil.md5(entity.getPassword()));
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    @Nullable
    public User update(@Nullable final User entity) {
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public boolean remove(final @Nullable User entity) {
        if (entity == null) return false;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean removeAll() {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from User ").executeUpdate();
        entityManager.getTransaction().commit();
        return true;
    }

    @Nullable
    @Override
    public User findOneById(final @Nullable String id) {
        if (id == null) return null;
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @Nullable final User user = entityManager.find(User.class, id);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    @Nullable
    public User getUserByLoginAndPassword(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        @NotNull final String hash = ServiceUtil.md5(password);
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        @NotNull final User user = entityManager
                .createQuery(
                        "from User u where u.login = '" + login + "' and u.password = '" + hash + "'",
                        User.class
                )
                .getSingleResult();
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    @Nullable
    public User setNewPassword(@Nullable final User user, @Nullable final String password) {
        if (password == null || password.isEmpty()) return null;
        if (user == null) return null;
        @NotNull final String hash = ServiceUtil.md5(password);
        user.setPassword(hash);

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public @Nullable User findById(final @Nullable String id) {
        if (id == null) return null;
        return findOneById(id);
    }

    @Override
    public void changeUserRole(@Nullable final User user, final @Nullable String role) {
        if (role == null || role.isEmpty()) throw new UpdateException();
        if (user == null || user.getId() == null) throw new UpdateException();
        @NotNull final RoleType roleType = RoleType.valueOf(role);
        user.setRoleType(roleType);

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

}
