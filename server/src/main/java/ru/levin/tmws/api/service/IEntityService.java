package ru.levin.tmws.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.AbstractEntity;

import java.util.List;

public interface IEntityService<T extends AbstractEntity> extends IService<T> {

    @NotNull List<T> getAll();
    @Nullable T save(@Nullable T entity);
    @Nullable T update(@Nullable T entity);
    boolean remove(@Nullable T entity);
    boolean removeAll();
    @Nullable T findOneById(@Nullable final String id);

}
