package ru.levin.tmws.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IProjectRepository;
import ru.levin.tmws.entity.Project;
import ru.levin.tmws.entity.Status;
import ru.levin.tmws.exception.SaveException;
import ru.levin.tmws.util.FieldConst;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    public ProjectRepository(final @NotNull Connection connection) {
        super(connection);
    }

    @Override
    @SneakyThrows
    public void removeByUserId(@NotNull String userId) {
        @NotNull final String query =
                "DELETE FROM `app_project` WHERE `userId` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public @NotNull List<Project> findAllByUserId(@NotNull String userId) {
        @NotNull final String query =
                "SELECT * FROM `app_project` WHERE `userId` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Override
    @SneakyThrows
    public @NotNull List<Project> findAllByPartOfNameOrDescription(final @NotNull String name) {
        @NotNull final String query =
                "SELECT * FROM `app_project` WHERE `name` LIKE ? OR `description` LIKE ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        statement.setString(2, "%" + name + "%");
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Override
    @SneakyThrows
    public @NotNull List<Project> findAll() {
        @NotNull final String query =
                "SELECT * FROM `app_project`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Project findOne(final @NotNull String id) {
        @NotNull final String query =
                "SELECT * FROM `app_project` WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable Project project = null;
        if (resultSet.next()) project = fetch(resultSet);
        statement.close();
        return project;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Project persist(final @NotNull Project entity) {
        @NotNull final String query =
                "INSERT INTO `app_project` VALUES (?, ?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull String id = UUID.randomUUID().toString();
        if (entity.getId() != null && !entity.getId().isEmpty()) id = entity.getId();
        statement.setString(1, id);
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getDescription());
        statement.setDate(4, entity.getStartDate() == null? null : new Date(entity.getStartDate().getTime()));
        statement.setDate(5, entity.getEndDate() == null ? null : new Date(entity.getEndDate().getTime()));
        statement.setString(6, entity.getUserId());
        statement.setString(7, entity.getStatus() == null ? null : entity.getStatus().toString());
        if (statement.executeUpdate() < 1) {
            statement.close();
            throw new SaveException();
        }
        return findOne(id);
    }

    @Override
    @SneakyThrows
    public void merge(final @NotNull Project entity) {
        @NotNull final String query =
                "UPDATE `app_project` SET `name` = ?, `description` = ?, `dateBegin` = ?, `dateEnd` = ?, " +
                        "`status` = ? WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setDate(3, entity.getStartDate() == null ? null : new Date(entity.getStartDate().getTime()));
        statement.setDate(4, entity.getEndDate() == null ? null : new Date(entity.getEndDate().getTime()));
        statement.setString(5, entity.getStatus() == null ? null : entity.getStatus().toString());
        statement.setString(6, entity.getId());
        if (statement.executeUpdate() < 1) {
            statement.close();
            throw new SaveException();
        }
    }

    @Override
    @SneakyThrows
    public void remove(final @NotNull Project entity) {
        @NotNull final String query =
                "DELETE FROM `app_project` WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        @NotNull final String query =
                "DELETE FROM `app_project`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @Nullable
    @SneakyThrows
    private Project fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Project project = new Project();
        project.setId(row.getString(FieldConst.ID));
        project.setName(row.getString(FieldConst.NAME));
        project.setDescription(row.getString(FieldConst.DESCRIPTION));
        project.setStartDate(row.getDate(FieldConst.DATE_BEGIN));
        project.setEndDate(row.getDate(FieldConst.DATE_END));
        project.setStatus(Status.valueOf(row.getString(FieldConst.STATUS)));
        return project;
    }

}
