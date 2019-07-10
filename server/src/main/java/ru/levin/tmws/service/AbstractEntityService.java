package ru.levin.tmws.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IRepository;
import ru.levin.tmws.api.service.IEntityService;
import ru.levin.tmws.entity.AbstractEntity;

import java.util.List;

public abstract class AbstractEntityService<T extends AbstractEntity, E extends IRepository<T>>
        extends AbstractService<T>
        implements IEntityService<T> {

    @NotNull
    protected final SqlSessionFactory sessionFactory;

    @NotNull final Class<E> repositoryClass;

    public AbstractEntityService(@NotNull SqlSessionFactory sessionFactory, @NotNull final Class<E> repositoryClass) {
        this.sessionFactory = sessionFactory;
        this.repositoryClass = repositoryClass;
    }

    @NotNull
    public List<T> getAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            @NotNull final E repository = session.getMapper(repositoryClass);
            return repository.findAll();
        }
    }

    @Nullable
    public abstract T save(@Nullable final T entity);

    @Nullable
    public abstract T update(@Nullable final T entity);

    public boolean remove(@Nullable final T entity) {
        if (entity == null) return false;
        if (entity.getId() == null || entity.getId().isEmpty()) return false;

        @Nullable final SqlSession session = sessionFactory.openSession();
        if (session == null) throw new SqlSessionException();
        try {
            @NotNull final E repository = session.getMapper(repositoryClass);
            repository.remove(entity);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }

        return true;
    }

    public boolean removeAll() {
        @Nullable final SqlSession session = sessionFactory.openSession();
        if (session == null) throw new SqlSessionException();
        try {
            @NotNull final E repository = session.getMapper(repositoryClass);
            repository.removeAll();
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return true;
    }

    @Nullable
    public T findOneById(final @Nullable String id) {
        if (id == null) return null;
        try (final SqlSession session = sessionFactory.openSession()) {
            @NotNull final E repository = session.getMapper(repositoryClass);
            return repository.findOne(id);
        }
    }

}
