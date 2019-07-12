package ru.levin.tmws.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ISessionRepository;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.entity.Session;

import javax.persistence.EntityManager;
import java.util.List;

public final class SessionRepository extends AbstractRepository<SessionDTO> implements ISessionRepository {

    public SessionRepository(final @NotNull EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @NotNull
    public List<SessionDTO> findAllByUserId(@NotNull final String userId) {
        @NotNull final List<SessionDTO> sessions = entityManager
                .createQuery("from SessionDTO s where s.userId = '" + userId + "'", SessionDTO.class)
                .getResultList();
        return sessions;
    }

    @Override
    public void removeByUserId(@NotNull final String userId) {
        entityManager.createQuery("delete from Session s where s.user.id = '" + userId + "'").executeUpdate();
    }

    @Override
    public @NotNull List<SessionDTO> findAll() {
        return entityManager.createQuery("from SessionDTO", SessionDTO.class).getResultList();
    }

    @Nullable
    @Override
    public SessionDTO findOne(final @NotNull String id) {
        return entityManager.find(SessionDTO.class, id);
    }

    @Nullable
    @Override
    public void persist(final @NotNull SessionDTO entity) {
        entityManager.persist(entity);
    }

    @Override
    @SneakyThrows
    public void merge(final @NotNull SessionDTO entity) {
        entityManager.merge(entity);
    }

    @Override
    @SneakyThrows
    public void remove(final @NotNull SessionDTO entity) {
        @Nullable Session session = entityManager.find(Session.class, entity.getId());
        if (session == null) return;
        entityManager.remove(session);
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        entityManager.createQuery("delete from Session").executeUpdate();
    }

}
