package ru.levin.tmws.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    @Select("SELECT * FROM `app_task`")
    @Results({
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd")
    })
    @NotNull List<Task> findAll();

    @Select("SELECT * FROM `app_task` WHERE `id` = #{id}")
    @Results({
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd")
    })
    @Nullable Task findOne(@NotNull final String id);

    @Insert("INSERT INTO `app_task` VALUES (#{id}, #{name}, #{description}, #{startDate}, #{endDate}," +
            " #{userId}, #{projectId}, #{status})")
    void persist(@NotNull final Task entity);

    @Update("UPDATE `app_task` SET `name` = #{name}, `description` = #{description}, " +
            "`dateBegin` = #{startDate}, `dateEnd` = #{endDate}, `status` = #{status}," +
            " `projectId` = #{projectId} WHERE `id` = #{id}")
    void merge(@NotNull final Task entity);

    @Delete("DELETE FROM `app_task` WHERE `id` = #{id}")
    void remove(@NotNull final Task entity);

    @Delete("DELETE FROM `app_task`")
    void removeAll();

    @Select("SELECT * FROM `app_task` WHERE `userId` = #{userId} AND `projectId` = #{projectId}")
    @Results({
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd")
    })
    @NotNull List<Task> findAllByUserIdProjectId(
            @NotNull @Param("userId") final String userId,
            @NotNull @Param("projectId") final String projectId
    );

    @Select("SELECT * FROM `app_task` WHERE `userId` = #{userId}")
    @Results({
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd")
    })
    @NotNull List<Task> findAllByUserId(@NotNull final String userId);

    @Delete("DELETE FROM `app_task` WHERE `userId` = #{userId}")
    void removeByUserId(@NotNull final String userId);

    @Select("SELECT * FROM `app_task` WHERE `name` LIKE #{name} OR `description` LIKE #{name}")
    @Results({
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd")
    })
    @NotNull List<Task> findAllByPartOfNameOrDescription(@NotNull final String name);

}
