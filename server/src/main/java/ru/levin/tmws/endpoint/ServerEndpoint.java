package ru.levin.tmws.endpoint;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.endpoint.IServerEndpoint;
import ru.levin.tmws.dto.ServerInfoDTO;

import javax.jws.WebService;

@Component
@WebService(endpointInterface = "ru.levin.tmws.api.endpoint.IServerEndpoint")
public class ServerEndpoint implements IServerEndpoint {

    @Override
    public @NotNull ServerInfoDTO getServerInfo() {
        return new ServerInfoDTO("localhost", System.getProperty("server.port"));
    }

}