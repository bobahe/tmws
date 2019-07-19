package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levin.tmws.api.repository.ISessionRepository;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.util.ServiceUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService extends AbstractEntityService<SessionDTO> implements ISessionService {

    @NotNull private final List<SessionDTO> list = new ArrayList<>();

    @NotNull
    private ISessionRepository repository;
    @Autowired
    public void setRepository(@NotNull final ISessionRepository repository) { this.repository = repository; }

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
    @Transactional
    public void removeByUserId(@Nullable final String userId) {
        if (userId == null) return;
        repository.removeByUserId(userId);
    }

    @Override
    @Nullable
    @Transactional
    public SessionDTO save(@Nullable final SessionDTO entity) {
        if (entity == null) return null;
        entity.setSignature(ServiceUtil.sign(entity, "123", 5));
        repository.save(entity);
        return entity;
    }

    @Override
    @Nullable
    @Transactional
    public SessionDTO update(@Nullable final SessionDTO entity) {
        if (entity == null) return null;
        if (entity.getId() == null || entity.getId().isEmpty()) return null;
        repository.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public boolean remove(final @Nullable SessionDTO entity) {
        if (entity == null) return false;
        repository.delete(entity);
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
    public SessionDTO findOneById(final @Nullable String id) {
        if (id == null) return null;
        @Nullable final SessionDTO session = repository.findById(id).orElse(null);
        return session;
    }

}
