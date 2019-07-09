package ru.levin.tmws.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.repository.IUserRepository;
import ru.levin.tmws.server.api.service.IUserService;
import ru.levin.tmws.server.entity.RoleType;
import ru.levin.tmws.server.entity.User;
import ru.levin.tmws.server.exception.SaveException;
import ru.levin.tmws.server.exception.UpdateException;
import ru.levin.tmws.server.util.ServiceUtil;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public final class UserService extends AbstractEntityService<User, IUserRepository> implements IUserService {

    @NotNull
    private final IUserRepository repository;

    @Nullable
    private User currentUser;

    public UserService(@NotNull final IUserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @Nullable
    public User save(@Nullable final User entity) {
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;

        try {
            @NotNull final String hash = ServiceUtil.md5(entity.getPassword());
            entity.setPassword(hash);
        } catch (Exception e) {
            throw new SaveException();
        }
        repository.persist(entity);
        return entity;
    }

    @Override
    @Nullable
    public User update(@Nullable final User entity) {
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;

        if (repository.findOne(entity.getId()) == null) {
            throw new IllegalStateException("Can not update user. There is no such user in storage.");
        }

        repository.merge(entity);
        return entity;
    }

    @Override
    @Nullable
    public User getUserByLoginAndPassword(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;

        try {
            @NotNull final String hash = ServiceUtil.md5(password);
            return repository.findOneByLoginAndPassword(login, hash);
        } catch (Exception e) {
            throw new SaveException();
        }
    }

    @Override
    @Nullable
    public User setNewPassword(@Nullable final User user, @Nullable final String password) {
        if (password == null || password.isEmpty()) return null;
        if (user == null) return null;

        try {
            @NotNull final String hash = ServiceUtil.md5(password);
            user.setPassword(hash);
        } catch (Exception e) {
            throw new SaveException();
        }

        return user;
    }

    @Override
    public void setCurrentUser(@Nullable final User user) {
        currentUser = user;
    }

    @Override
    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public @Nullable User findById(final @Nullable String id) {
        if (id == null) return null;
        return repository.findById(id);
    }

    @Override
    public void changeUserRole(@Nullable final User user, final @Nullable String role) {
        if (role == null || role.isEmpty()) throw new UpdateException();
        if (user == null || user.getId() == null) throw new UpdateException();
        @NotNull final RoleType roleType = RoleType.valueOf(role);
        @Nullable final User serverUser = repository.findById(user.getId());
        if (serverUser == null) throw new UpdateException();
        serverUser.setRoleType(roleType);
        repository.merge(serverUser);
    }

}
