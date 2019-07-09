package ru.levin.tmws.repository;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.repository.IRepository;
import ru.levin.tmws.entity.AbstractEntity;

import java.sql.Connection;

public abstract class AbstractRepository<T extends AbstractEntity> implements IRepository<T> {

    @NotNull final Connection connection;

    public AbstractRepository(@NotNull final Connection connection) {
        this.connection = connection;
    }
}
