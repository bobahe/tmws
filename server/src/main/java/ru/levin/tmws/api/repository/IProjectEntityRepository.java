package ru.levin.tmws.api.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.entity.Project;

import java.util.List;

@Repository
public interface IProjectEntityRepository extends FullEntityRepository<Project, String> {

    @NotNull List<Project> findByUser_id(@NotNull final String id);

}
