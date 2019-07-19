package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levin.tmws.api.repository.IUserEntityRepository;
import ru.levin.tmws.api.repository.IUserRepository;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.UserDTO;
import ru.levin.tmws.entity.RoleType;
import ru.levin.tmws.entity.User;
import ru.levin.tmws.exception.UpdateException;
import ru.levin.tmws.util.ServiceUtil;

import java.util.List;

@Service
public class UserService extends AbstractEntityService<UserDTO> implements IUserService {

    @NotNull
    private IUserRepository repository;
    @Autowired
    public void setRepository(@NotNull final IUserRepository repository) { this.repository = repository; }

    @NotNull
    private IUserEntityRepository entityRepository;
    @Autowired
    public void setEntityRepository(@NotNull final IUserEntityRepository repository) { this.entityRepository = repository; }

    @Override
    public @NotNull List<UserDTO> getAll() {
        @NotNull final List<UserDTO> user = repository.findAll();
        return user;
    }

    @Override
    @Nullable
    @Transactional
    public UserDTO save(@Nullable final UserDTO entity) {
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;
        entity.setPassword(ServiceUtil.md5(entity.getPassword()));
        repository.save(entity);
        return entity;
    }

    @Override
    @Nullable
    @Transactional
    public UserDTO update(@Nullable final UserDTO entity) {
        if (entity == null) return null;
        if (entity.getLogin() == null || entity.getLogin().isEmpty()) return null;
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        repository.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public boolean remove(final @Nullable UserDTO entity) {
        if (entity == null) return false;
        @Nullable final User user = entityRepository.findById(entity.getId()).orElse(null);
        if (user == null) return false;
        entityRepository.delete(user);
        return true;
    }

    @Override
    @Transactional
    public boolean removeAll() {
        repository.findAll().forEach(repository::delete);
        return true;
    }

    @Nullable
    @Override
    public UserDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @Nullable final UserDTO user = repository.findById(id).orElse(null);
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
    @Transactional
    public UserDTO setNewPassword(@Nullable final UserDTO user, @Nullable final String password) {
        if (password == null || password.isEmpty()) return null;
        if (user == null) return null;
        @NotNull final String hash = ServiceUtil.md5(password);
        user.setPassword(hash);
        repository.save(user);
        return user;
    }

    @Override
    public @Nullable UserDTO findById(final @Nullable String id) {
        if (id == null) return null;
        return findOneById(id);
    }

    @Override
    @Transactional
    public void changeUserRole(@Nullable final UserDTO user, final @Nullable String role) {
        if (role == null || role.isEmpty()) throw new UpdateException();
        if (user == null || user.getId() == null) throw new UpdateException();
        @NotNull final RoleType roleType = RoleType.valueOf(role);
        user.setRoleType(roleType);
        repository.save(user);
    }

}
