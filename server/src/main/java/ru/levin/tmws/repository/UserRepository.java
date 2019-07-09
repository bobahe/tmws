package ru.levin.tmws.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.repository.IUserRepository;
import ru.levin.tmws.entity.RoleType;
import ru.levin.tmws.entity.User;
import ru.levin.tmws.exception.SaveException;
import ru.levin.tmws.exception.UpdateException;
import ru.levin.tmws.util.FieldConst;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository(final @NotNull Connection connection) {
        super(connection);
    }

    @Nullable
    @SneakyThrows
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        @NotNull final String query =
                "SELECT * FROM `app_user` WHERE `login` = ? AND `passwordHash` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, password);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable User user = null;
        if (resultSet.next()) user = fetch(resultSet);
        statement.close();
        return user;
    }

    @Override
    @SneakyThrows
    public @NotNull List<User> findAll() {
        @NotNull final String query =
                "SELECT * FROM `app_user`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<User> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Nullable
    @Override
    @SneakyThrows
    public User findOne(final @NotNull String id) {
        @NotNull final String query =
                "SELECT * FROM `app_user` WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable User user = null;
        if (resultSet.next()) user = fetch(resultSet);
        statement.close();
        return user;
    }

    @Nullable
    @Override
    @SneakyThrows
    public User persist(final @NotNull User entity) {
        @NotNull final String query =
                "INSERT INTO `app_user` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull String id = UUID.randomUUID().toString();
        if (entity.getId() != null && !entity.getId().isEmpty()) id = entity.getId();
        statement.setString(1, id);
        statement.setString(2, entity.getEmail());
        statement.setString(3, entity.getFirstName());
        statement.setString(4, entity.getLastName());
        statement.setInt(5, (entity.getLocked() != null && entity.getLocked()) ? 1 : 0);
        statement.setString(6, entity.getLogin());
        statement.setString(7, entity.getMiddleName());
        statement.setString(8, entity.getPassword());
        statement.setString(9, entity.getPhone());
        statement.setString(10, entity.getRoleType() == null ? null : entity.getRoleType().toString());
        if (statement.executeUpdate() < 1) {
            statement.close();
            throw new SaveException();
        }
        return findOne(id);
    }

    @Override
    @SneakyThrows
    public void merge(final @NotNull User entity) {
        @NotNull final String query =
                "UPDATE `app_user` SET `email` = ?, `firstName` = ?, `lastName` = ?, " +
                        "`locked` = ?, `login` = ?, `middleName` = ?, `passwordHash` = ?, " +
                        "`phone` = ?, `role` = ? WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setInt(4, (entity.getLocked() != null && entity.getLocked()) ? 1 : 0);
        statement.setString(5, entity.getLogin());
        statement.setString(6, entity.getMiddleName());
        statement.setString(7, entity.getPassword());
        statement.setString(8, entity.getPhone());
        statement.setString(9, entity.getRoleType() == null ? null : entity.getRoleType().toString());
        statement.setString(10, entity.getId());
        if (statement.executeUpdate() < 1) {
            statement.close();
            throw new UpdateException();
        }
    }

    @Override
    @SneakyThrows
    public void remove(final @NotNull User entity) {
        @NotNull final String query =
                "DELETE FROM `app_user` WHERE `id` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        @NotNull final String query =
                "DELETE FROM `app_user`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @Nullable
    @SneakyThrows
    private User fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final User user = new User();
        user.setId(row.getString(FieldConst.ID));
        user.setEmail(row.getString(FieldConst.EMAIL));
        user.setFirstName(row.getString(FieldConst.FIRST_NAME));
        user.setLastName(row.getString(FieldConst.LAST_NAME));
        user.setLocked(row.getInt(FieldConst.LOCKED) != 0);
        user.setLogin(row.getString(FieldConst.LOGIN));
        user.setMiddleName(row.getString(FieldConst.MIDDLE_NAME));
        user.setPassword(row.getString(FieldConst.PASSWORD_HASH));
        user.setPhone(row.getString(FieldConst.PHONE));
        user.setRoleType(RoleType.valueOf(row.getString(FieldConst.ROLE)));
        return user;
    }

}
