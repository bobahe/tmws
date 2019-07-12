package ru.levin.tmws.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IUserRepository;
import ru.levin.tmws.dto.UserDTO;
import ru.levin.tmws.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public final class UserRepository extends AbstractRepository<UserDTO> implements IUserRepository {

    public UserRepository(final @NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Nullable
    public UserDTO findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        return entityManager
                .createQuery("from UserDTO u where u.login = '" + login + "' and u.password = '" + password + "'", UserDTO.class)
                .getSingleResult();
    }

    @Override
    public @NotNull List<UserDTO> findAll() {
        return entityManager.createQuery("from UserDTO", UserDTO.class).getResultList();
    }

    @Nullable
    @Override
    public UserDTO findOne(final @NotNull String id) {
        return entityManager.find(UserDTO.class, id);
    }

    @Override
    public void persist(final @NotNull UserDTO entity) {
        entityManager.persist(entity);
    }

    @Override
    public void merge(final @NotNull UserDTO entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(final @NotNull UserDTO entity) {
        @Nullable final User user = entityManager.find(User.class, entity.getId());
        if (user == null) return;
        entityManager.remove(user);
    }

    @Override
    public void removeAll() {
        entityManager.createQuery("delete from User").executeUpdate();
    }

}
