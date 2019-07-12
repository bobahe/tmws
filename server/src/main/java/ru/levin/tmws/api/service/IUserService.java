package ru.levin.tmws.api.service;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.dto.UserDTO;

public interface IUserService extends IEntityService<UserDTO> {

    @Nullable UserDTO getUserByLoginAndPassword(@Nullable final String login, @Nullable final String password);
    @Nullable UserDTO setNewPassword(@Nullable final UserDTO user, @Nullable final String password);
    @Nullable UserDTO findById(@Nullable final String id);
    void changeUserRole(@Nullable final UserDTO user, @Nullable final String role);

}
