package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.service.IEntityService;
import ru.levin.tmws.dto.AbstractDTO;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class AbstractEntityService<T extends AbstractDTO>
        extends AbstractService<T>
        implements IEntityService<T> {

    @NotNull
    protected final EntityManagerFactory entityManagerFactory;

    public AbstractEntityService(@NotNull EntityManagerFactory sessionFactory) {
        this.entityManagerFactory = sessionFactory;
    }

    @NotNull
    public abstract List<T> getAll();

    @Nullable
    public abstract T save(@Nullable final T entity);

    @Nullable
    public abstract T update(@Nullable final T entity);

    public abstract boolean remove(@Nullable final T entity);

    public abstract boolean removeAll();

    @Nullable
    public abstract T findOneById(final @Nullable String id);

}
