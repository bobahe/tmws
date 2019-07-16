package ru.levin.tmws.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ISessionRepository;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.util.ServiceUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class SessionService extends AbstractEntityService<SessionDTO> implements ISessionService {

    @NotNull private final List<SessionDTO> list = new ArrayList<>();

    @NotNull
    @Inject
    private ISessionRepository repository;

    @Override
    public @NotNull List<SessionDTO> getAll() {
        @NotNull final List<SessionDTO> result = repository.findAll();
        return result;
    }

    @Override
    @NotNull
    public List<SessionDTO> findAllByUserId(@Nullable final String userId) {
        if (userId == null) return list;
        @NotNull final List<SessionDTO> result = repository.findByUserId(userId);
        return result;
    }

    @Override
    public @Nullable SessionDTO findById(@Nullable final String id) {
        if (id == null) return null;
        return findOneById(id);
    }

    @Override
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        repository.removeByUserId(userId);
    }

    @Override
    @Nullable
    public SessionDTO save(@Nullable final SessionDTO entity) {
        if (entity == null) return null;
        entity.setSignature(ServiceUtil.sign(entity, "123", 5));
        repository.persist(entity);
        return entity;
    }

    @Override
    @Nullable
    public SessionDTO update(@Nullable final SessionDTO entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        repository.merge(entity);
        return entity;
    }

    @Override
    public boolean remove(final @Nullable SessionDTO entity) {
        if (entity == null) return false;
        repository.remove(entity);
        return true;
    }

    @Override
    public boolean removeAll() {
        repository.removeAll();
        return true;
    }

    @Nullable
    @Override
    public SessionDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @Nullable final SessionDTO session = repository.findBy(id);
        return session;
    }

}
