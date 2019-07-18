package ru.levin.tmws.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.dto.ServerInfoDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IServerEndpoint extends IEndpoint {

    @NotNull
    @WebMethod
    ServerInfoDTO getServerInfo();

}
