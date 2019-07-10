package ru.levin.tmws.api.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    @Select("SELECT * FROM `app_task`")
    @NotNull List<Task> findAll();

    @Select("SELECT * FROM `app_task` WHERE `id` = #{id}")
    @Nullable Task findOne(@NotNull final String id);

    @Insert("INSERT INTO `app_task` VALUES (#{id}, #{name}, #{description}, #{startDate}, #{endDate}," +
            " #{userId}, #{projectId}, #{status})")
    @Nullable Task persist(@NotNull final Task entity);

    @Update("UPDATE `app_task` SET `name` = #{name}, `description` = #{description}, " +
            "`dateBegin` = #{startDate}, `dateEnd` = #{endDate}, `status` = #{status}," +
            " `projectId` = #{projectId} WHERE `id` = #{id}")
    void merge(@NotNull final Task entity);

    @Delete("DELETE FROM `app_task` WHERE `id` = #{id}")
    void remove(@NotNull final Task entity);

    @Delete("DELETE FROM `app_task`")
    void removeAll();

    @Select("SELECT * FROM `app_task` WHERE `userId` = #{userId} AND `projectId` = #{projectId}")
    @NotNull List<Task> findAllByUserIdProjectId(@NotNull final String userId, @NotNull final String projectId);

    @Select("SELECT * FROM `app_task` WHERE `userId` = #{userId}")
    @NotNull List<Task> findAllByUserId(@NotNull final String userId);

    @Delete("DELETE FROM `app_task` WHERE `userId` = #{userId}")
    void removeByUserId(@NotNull final String userId);

    @Select("SELECT * FROM `app_task` WHERE `name` LIKE #{name} OR `description` LIKE #{name}")
    @NotNull List<Task> findAllByPartOfNameOrDescription(@NotNull final String name);

}
