package ru.levin.tmws.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IUserRepository;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.UserDTO;
import ru.levin.tmws.entity.RoleType;
import ru.levin.tmws.exception.UpdateException;
import ru.levin.tmws.util.ServiceUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Transactional
public class UserService extends AbstractEntityService<UserDTO> implements IUserService {

    @NotNull
    @Inject
    private IUserRepository repository;

    @Override
    public @NotNull List<UserDTO> getAll() {
        @NotNull final List<UserDTO> user = repository.findAll();
        return user;
    }

    @Override
    @Nullable
    public UserDTO save(@Nullable final UserDTO entity) {
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;
        entity.setPassword(ServiceUtil.md5(entity.getPassword()));
        repository.persist(entity);
        return entity;
    }

    @Override
    @Nullable
    public UserDTO update(@Nullable final UserDTO entity) {
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        repository.merge(entity);
        return entity;
    }

    @Override
    public boolean remove(final @Nullable UserDTO entity) {
        if (entity == null) return false;
        repository.remove(entity);
        return true;
    }

    @Override
    public boolean removeAll() {
        repository.findAll().forEach(repository::remove);
        return true;
    }

    @Nullable
    @Override
    public UserDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @Nullable final UserDTO user = repository.findBy(id);
        return user;
    }

    @Override
    @Nullable
    public UserDTO getUserByLoginAndPassword(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        @NotNull final String hash = ServiceUtil.md5(password);
        @Nullable final UserDTO user = repository.findByLoginAndPassword(login, hash);
        return user;
    }

    @Override
    @Nullable
    public UserDTO setNewPassword(@Nullable final UserDTO user, @Nullable final String password) {
        if (password == null || password.isEmpty()) return null;
        if (user == null) return null;
        @NotNull final String hash = ServiceUtil.md5(password);
        user.setPassword(hash);
        repository.merge(user);
        return user;
    }

    @Override
    public @Nullable UserDTO findById(final @Nullable String id) {
        if (id == null) return null;
        return findOneById(id);
    }

    @Override
    public void changeUserRole(@Nullable final UserDTO user, final @Nullable String role) {
        if (role == null || role.isEmpty()) throw new UpdateException();
        if (user == null || user.getId() == null) throw new UpdateException();
        @NotNull final RoleType roleType = RoleType.valueOf(role);
        user.setRoleType(roleType);
        repository.merge(user);
    }

}
