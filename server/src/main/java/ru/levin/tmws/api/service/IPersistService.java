package ru.levin.tmws.api.service;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;
import ru.levin.tmws.service.PersistService;

public interface IPersistService extends IService<PersistService> {

    void serialize(@NotNull IServiceLocator serviceLocator);

    void deserialize(@NotNull IServiceLocator serviceLocator);

    void saveFxmlXml(@NotNull IServiceLocator serviceLocator);

    void saveFxmlJson(@NotNull IServiceLocator serviceLocator);

    void loadFxmlXml(@NotNull IServiceLocator serviceLocator);

    void loadFxmlJson(@NotNull IServiceLocator serviceLocator);

    void saveJaxbXml(@NotNull IServiceLocator serviceLocator);

    void saveJaxbJson(@NotNull IServiceLocator serviceLocator);

    void loadJaxbXml(@NotNull IServiceLocator serviceLocator);

    void loadJaxbJson(@NotNull IServiceLocator serviceLocator);

}
