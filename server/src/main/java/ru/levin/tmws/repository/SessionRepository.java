package ru.levin.tmws.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ISessionRepository;
import ru.levin.tmws.entity.Session;
import ru.levin.tmws.exception.SaveException;
import ru.levin.tmws.util.FieldConst;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    public SessionRepository(final @NotNull Connection connection) {
        super(connection);
    }

    @Override
    @NotNull
    @SneakyThrows
    public List<Session> findAllByUserId(@NotNull final String userId) {
        @NotNull final String query =
                "SELECT * FROM `app_session` WHERE `userId` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Session> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Override
    @SneakyThrows
    public void removeByUserId(@NotNull final String userId) {
        @NotNull final String query =
                "DELETE FROM `app_session` WHERE `userId` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public @NotNull List<Session> findAll() {
        @NotNull final String query =
                "SELECT * FROM `app_session`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Session> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Session findOne(final @NotNull String id) {
        @NotNull final String query =
                "SELECT * FROM `app_session` WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable Session session = null;
        if (resultSet.next()) session = fetch(resultSet);
        statement.close();
        return session;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Session persist(final @NotNull Session entity) {
        @NotNull final String query =
                "INSERT INTO `app_session` VALUES (?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull String id = UUID.randomUUID().toString();
        if (entity.getId() != null && !entity.getId().isEmpty()) id = entity.getId();
        statement.setString(1, id);
        statement.setString(2, entity.getSignature());
        statement.setLong(3, System.currentTimeMillis());
        statement.setString(4, entity.getUserId());
        if (statement.executeUpdate() < 1) {
            statement.close();
            throw new SaveException();
        }
        return findOne(id);
    }

    @Override
    @SneakyThrows
    public void merge(final @NotNull Session entity) {
        @NotNull final String query =
                "UPDATE `app_session` SET `signature` = ?, `userId` = ?, `timestamp` = ? WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getSignature());
        statement.setString(2, entity.getUserId());
        statement.setLong(3, System.currentTimeMillis());
        statement.setString(4, entity.getId());
        if (statement.executeUpdate() < 1) {
            statement.close();
            throw new SaveException();
        }
    }

    @Override
    @SneakyThrows
    public void remove(final @NotNull Session entity) {
        @NotNull final String query =
                "DELETE FROM `app_session` WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        @NotNull final String query =
                "DELETE FROM `app_session`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @Nullable
    @SneakyThrows
    private Session fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Session session = new Session();
        session.setId(row.getString(FieldConst.ID));
        session.setSignature(row.getString(FieldConst.SIGNATURE));
        session.setUserId(row.getString(FieldConst.USER_ID));
        session.setTimestamp(row.getLong(FieldConst.TIMESTAMP));
        return session;
    }

}
