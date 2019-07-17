package ru.levin.tmws.context;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.endpoint.*;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.UserDTO;
import ru.levin.tmws.entity.RoleType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;

@ApplicationScoped
public class Bootstrap {

    @Nullable
    @Inject
    private ISessionEndpoint sessionEndpoint;

    @Nullable
    @Inject
    private IAdminEndpoint adminEndpoint;

    @Nullable
    @Inject
    private IProjectEndpoint projectEndpoint;

    @Nullable
    @Inject
    private ITaskEndpoint taskEndpoint;

    @Nullable
    @Inject
    private IUserEndpoint userEndpoint;

    @Nullable
    @Inject
    private IUserService userService;

    public void init() {
        publishEndpoints();
        createDefaultUsers();
    }

    private void publishEndpoints() {
        @NotNull final String url = "http://localhost:8081/tm/";
        Endpoint.publish(url + "SessionEndpoint", sessionEndpoint);
        Endpoint.publish(url + "AdminEndpoint", adminEndpoint);
        Endpoint.publish(url + "ProjectEndpoint", projectEndpoint);
        Endpoint.publish(url + "TaskEndpoint", taskEndpoint);
        Endpoint.publish(url + "UserEndpoint", userEndpoint);
    }

    private void createDefaultUsers() {
        if (userService == null) return;
        if (userService.findById("38aca227-dc47-459f-bc0b-2134e260135c") == null) {
            @NotNull final UserDTO admin = new UserDTO();
            admin.setId("38aca227-dc47-459f-bc0b-2134e260135c");
            admin.setLogin("admin");
            admin.setPassword("admin");
            admin.setRoleType(RoleType.ADMIN);
            userService.save(admin);
        }
        if (userService.findById("54d01220-0ecd-4ca2-a300-ff6ffc9b1254") == null) {
            @NotNull final UserDTO user = new UserDTO();
            user.setId("54d01220-0ecd-4ca2-a300-ff6ffc9b1254");
            user.setLogin("user");
            user.setPassword("user");
            user.setRoleType(RoleType.USER);
            userService.save(user);
        }
    }

}
