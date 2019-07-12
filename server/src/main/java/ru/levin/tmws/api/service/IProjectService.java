package ru.levin.tmws.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.ProjectDTO;

import java.util.List;

public interface IProjectService extends IEntityService<ProjectDTO> {

    void removeByUserId(@Nullable final String userId);
    @Nullable ProjectDTO findOneByIndex(@Nullable final String userId, final int index);
    @NotNull  List<ProjectDTO> findAllByUserId(@Nullable final String userId);
    @NotNull List<ProjectDTO> findAllByPartOfNameOrDescription(@Nullable final String partOfName);

}
