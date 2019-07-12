package ru.levin.tmws.repository;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.repository.IRepository;
import ru.levin.tmws.dto.AbstractDTO;

import javax.persistence.EntityManager;

public abstract class AbstractRepository<T extends AbstractDTO> implements IRepository<T> {

    @NotNull final EntityManager entityManager;

    public AbstractRepository(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
