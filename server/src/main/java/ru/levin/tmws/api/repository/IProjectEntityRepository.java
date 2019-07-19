package ru.levin.tmws.api.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.levin.tmws.entity.Project;

import java.util.List;

@Repository
public interface IProjectEntityRepository extends JpaRepository<Project, String> {

    @NotNull List<Project> findByUser_id(@NotNull final String id);

}
