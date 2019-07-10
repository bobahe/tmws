package ru.levin.tmws.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.ITaskRepository;
import ru.levin.tmws.entity.Status;
import ru.levin.tmws.entity.Task;
import ru.levin.tmws.exception.SaveException;
import ru.levin.tmws.exception.UpdateException;
import ru.levin.tmws.util.FieldConst;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    public TaskRepository(final @NotNull Connection connection) {
        super(connection);
    }

    @NotNull
    @SneakyThrows
    public List<Task> findAllByUserIdProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final String query =
                "SELECT * FROM `app_task` WHERE `userId` = ? AND `projectId` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, projectId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Override
    @NotNull
    @SneakyThrows
    public List<Task> findAllByUserId(@NotNull final String userId) {
        @NotNull final String query =
                "SELECT * FROM `app_task` WHERE `userId` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Override
    @SneakyThrows
    public void removeByUserId(@NotNull final String userId) {
        @NotNull final String query =
                "DELETE FROM `app_task` WHERE `userId` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public @NotNull List<Task> findAllByPartOfNameOrDescription(final @NotNull String name) {
        @NotNull final String query =
                "SELECT * FROM `app_task` WHERE `name` LIKE ? OR `description` LIKE ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        statement.setString(2, "%" + name + "%");
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Override
    @SneakyThrows
    public @NotNull List<Task> findAll() {
        @NotNull final String query =
                "SELECT * FROM `app_task`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Task findOne(final @NotNull String id) {
        @NotNull final String query =
                "SELECT * FROM `app_task` WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable Task task = null;
        if (resultSet.next()) task = fetch(resultSet);
        statement.close();
        return task;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Task persist(final @NotNull Task entity) {
        @NotNull final String query =
                "INSERT INTO `app_task` VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull String id = UUID.randomUUID().toString();
        if (entity.getId() != null && !entity.getId().isEmpty()) id = entity.getId();
        statement.setString(1, id);
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getDescription());
        statement.setDate(4, entity.getStartDate() == null ? null : new Date(entity.getStartDate().getTime()));
        statement.setDate(5, entity.getEndDate() == null ? null : new Date(entity.getEndDate().getTime()));
        statement.setString(6, entity.getUserId());
        statement.setString(7, entity.getProjectId());
        statement.setString(8, entity.getStatus() == null ? null : entity.getStatus().toString());
        if (statement.executeUpdate() < 1) {
            statement.close();
            throw new SaveException();
        }
        return findOne(id);
    }

    @Override
    @SneakyThrows
    public void merge(final @NotNull Task entity) {
        @NotNull final String query =
                "UPDATE `app_task` SET `name` = ?, `description` = ?, `dateBegin` = ?, `dateEnd` = ?, " +
                        "`status` = ?, `projectId` = ? WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setDate(3, entity.getStartDate() == null ? null : new Date(entity.getStartDate().getTime()));
        statement.setDate(4, entity.getEndDate() == null ? null : new Date(entity.getEndDate().getTime()));
        statement.setString(5, entity.getStatus() == null ? null : entity.getStatus().toString());
        statement.setString(6, entity.getProjectId());
        statement.setString(7, entity.getId());
        if (statement.executeUpdate() < 1) {
            statement.close();
            throw new UpdateException();
        }
    }

    @Override
    @SneakyThrows
    public void remove(final @NotNull Task entity) {
        @NotNull final String query =
                "DELETE FROM `app_task` WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        @NotNull final String query =
                "DELETE FROM `app_task`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @Nullable
    @SneakyThrows
    private Task fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Task task = new Task();
        task.setId(row.getString(FieldConst.ID));
        task.setUserId(row.getString(FieldConst.USER_ID));
        task.setName(row.getString(FieldConst.NAME));
        task.setDescription(row.getString(FieldConst.DESCRIPTION));
        task.setStartDate(row.getDate(FieldConst.DATE_BEGIN));
        task.setEndDate(row.getDate(FieldConst.DATE_END));
        task.setProjectId(row.getString(FieldConst.PROJECT_ID));
        task.setStatus(Status.valueOf(row.getString(FieldConst.STATUS)));
        return task;
    }

}
