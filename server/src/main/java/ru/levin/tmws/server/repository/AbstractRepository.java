package ru.levin.tmws.server.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.repository.IRepository;
import ru.levin.tmws.server.entity.AbstractEntity;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractRepository<T extends AbstractEntity> implements IRepository<T> {

    @NotNull
    protected final Map<String, T> storageMap = Collections.synchronizedMap(new LinkedHashMap<>());

    @Override
    @NotNull
    public Map<String, T> findAll() {
        return storageMap;
    }

    @Override
    @Nullable
    public T findOne(@NotNull final String id) {
        return storageMap.get(id);
    }

    @Override
    @NotNull
    public T persist(@NotNull final T entity) {
        if (entity.getId() == null || entity.getId().isEmpty()) {
            @NotNull final String id = UUID.randomUUID().toString();
            entity.setId(id);
        }
        storageMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void merge(@NotNull final T entity) {
        storageMap.put(entity.getId(), entity);
    }

    @Override
    public void remove(@NotNull final T entity) {
        storageMap.remove(entity.getId());
    }

    @Override
    public void removeAll() {
        storageMap.clear();
    }

}
