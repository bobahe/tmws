package ru.levin.tmws.api.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.Session;

import java.util.List;

public interface ISessionRepository extends IRepository<Session> {

    @Select("SELECT * FROM `app_session`")
    @NotNull List<Session> findAll();

    @Select("SELECT * FROM `app_session` WHERE `id` = #{id}")
    @Nullable Session findOne(@NotNull final String id);

    @Insert("INSERT INTO `app_session` VALUES (#{id}, #{signature}, #{timestamp}, #{userId})")
    @Nullable Session persist(@NotNull final Session entity);

    @Update("UPDATE `app_session` SET `signature` = #{signature}, `userId` = #{userId}," +
            " `timestamp` = #{timestamp} WHERE `id` = #{id}")
    void merge(@NotNull final Session entity);

    @Delete("DELETE FROM `app_session` WHERE `id` = #{id}")
    void remove(@NotNull final Session entity);

    @Delete("DELETE FROM `app_session`")
    void removeAll();

    @Select("SELECT * FROM `app_session` WHERE `userId` = #{userId}")
    @NotNull List<Session> findAllByUserId(@NotNull final String userId);

    @Delete("DELETE FROM `app_session` WHERE `userId` = #{userId}")
    void removeByUserId(@NotNull final String userId);

}
