package ru.levin.tmws.server.api.service;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.server.api.IServiceLocator;
import ru.levin.tmws.server.service.PersistService;

public interface IPersistService extends IService<PersistService> {

    void serialize(@NotNull IServiceLocator bootstrap);

    void deserialize(@NotNull IServiceLocator bootstrap);

    void saveFxmlXml(@NotNull IServiceLocator bootstrap);

    void saveFxmlJson(@NotNull IServiceLocator bootstrap);

    void loadFxmlXml(@NotNull IServiceLocator bootstrap);

    void loadFxmlJson(@NotNull IServiceLocator bootstrap);

    void saveJaxbXml(@NotNull IServiceLocator bootstrap);

    void saveJaxbJson(@NotNull IServiceLocator bootstrap);

    void loadJaxbXml(@NotNull IServiceLocator bootstrap);

    void loadJaxbJson(@NotNull IServiceLocator bootstrap);

}
