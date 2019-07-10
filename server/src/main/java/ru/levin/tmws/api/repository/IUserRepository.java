package ru.levin.tmws.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.User;

import java.util.List;

public interface IUserRepository extends IRepository<User> {

    @Select("SELECT * FROM `app_user`")
    @Results({
            @Result(property = "password", column = "passwordHash"),
            @Result(property = "roleType", column = "role")
    })
    @NotNull List<User> findAll();

    @Select("SELECT * FROM `app_user` WHERE `id` = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "password", column = "passwordHash"),
            @Result(property = "roleType", column = "role")
    })
    @Nullable User findOne(@NotNull final String id);

    @Insert("INSERT INTO `app_user` VALUES (#{id}, #{email}, #{firstName}, #{lastName}, #{locked}, #{login}," +
            " #{middleName}, #{password}, #{phone}, #{roleType})")
    @Nullable User persist(@NotNull final User entity);

    @Update("UPDATE `app_user` SET `email` = #{email}, `firstName` = #{firstName}, `lastName` = #{lastName}, " +
            "`locked` = #{locked}, `login` = #{login}, `middleName` = #{middleName}, `passwordHash` = #{password}, " +
            "`phone` = #{phone}, `role` = #{roleType} WHERE `id` = #{id}")
    void merge(@NotNull final User entity);

    @Delete("DELETE FROM `app_user` WHERE `id` = #{id}")
    void remove(@NotNull final User entity);

    @Delete("DELETE FROM `app_user`")
    void removeAll();

    @Select("SELECT * FROM `app_user` WHERE `login` = #{login} AND `passwordHash` = ${hash}")
    @Nullable User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String hash);

}
