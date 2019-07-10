package ru.levin.tmws.api.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.Project;

import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    @Select("SELECT * FROM `app_project`")
    @NotNull List<Project> findAll();

    @Select("SELECT * FROM `app_project` WHERE `id` = #{id}")
    @Nullable Project findOne(@NotNull final String id);

    @Insert("INSERT INTO `app_project` VALUES (#{id}, #{name}, #{description}, #{startDate}, #{endDate}," +
            " #{userId}, #{status)")
    @Nullable Project persist(@NotNull final Project entity);

    @Update("UPDATE `app_project` SET `name` = #{name}, `description` = #{description}, " +
            "`dateBegin` = #{startDate}, `dateEnd` = #{endDate}, `status` = #{status) WHERE `id` = #{id}")
    void merge(@NotNull final Project entity);

    @Delete("DELETE FROM `app_project` WHERE `id` = #{id}")
    void remove(@NotNull final Project entity);

    @Delete("DELETE FROM `app_project`")
    void removeAll();

    @Delete("DELETE FROM `app_project` WHERE `userId` = #{userId}")
    void removeByUserId(@NotNull final String userId);

    @Select("SELECT * FROM `app_project` WHERE `userId` = #{userId}")
    @NotNull List<Project> findAllByUserId(@NotNull final String userId);

    @Select("SELECT * FROM `app_project` WHERE `name` LIKE #{name} OR `description` LIKE #{name}")
    @NotNull List<Project> findAllByPartOfNameOrDescription(@NotNull final String name);

}
