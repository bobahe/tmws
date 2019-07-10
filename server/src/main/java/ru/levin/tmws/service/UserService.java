package ru.levin.tmws.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IUserRepository;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.entity.RoleType;
import ru.levin.tmws.entity.User;
import ru.levin.tmws.exception.UpdateException;
import ru.levin.tmws.util.ServiceUtil;

public final class UserService extends AbstractEntityService<User, IUserRepository> implements IUserService {

    @NotNull
    private final SqlSessionFactory sessionFactory;

    public UserService(@NotNull final SqlSessionFactory sessionFactory) {
        super(sessionFactory, IUserRepository.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Nullable
    public User save(@Nullable final User entity) {
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;

        final SqlSession session = sessionFactory.openSession();
        try {
            IUserRepository repository = session.getMapper(repositoryClass);
            repository.persist(entity);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    @Nullable
    public User update(@Nullable final User entity) {
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;

        final SqlSession session = sessionFactory.openSession();
        try {
            IUserRepository repository = session.getMapper(repositoryClass);
            repository.merge(entity);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    @Nullable
    public User getUserByLoginAndPassword(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        try (SqlSession session = sessionFactory.openSession()) {
            IUserRepository repository = session.getMapper(repositoryClass);
            return repository.findOneByLoginAndPassword(login, password);
        }
    }

    @Override
    @Nullable
    public User setNewPassword(@Nullable final User user, @Nullable final String password) {
        if (password == null || password.isEmpty()) return null;
        if (user == null) return null;

        final SqlSession session = sessionFactory.openSession();
        try {
            @NotNull final String hash = ServiceUtil.md5(password);
            user.setPassword(hash);
            IUserRepository repository = session.getMapper(repositoryClass);
            repository.merge(user);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public @Nullable User findById(final @Nullable String id) {
        if (id == null) return null;
        try (SqlSession session = sessionFactory.openSession()) {
            IUserRepository repository = session.getMapper(repositoryClass);
            return repository.findOne(id);
        }
    }

    @Override
    public void changeUserRole(@Nullable final User user, final @Nullable String role) {
        if (role == null || role.isEmpty()) throw new UpdateException();
        if (user == null || user.getId() == null) throw new UpdateException();
        @NotNull final RoleType roleType = RoleType.valueOf(role);
        user.setRoleType(roleType);

        final SqlSession session = sessionFactory.openSession();
        try {
            IUserRepository repository = session.getMapper(repositoryClass);
            repository.merge(user);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
