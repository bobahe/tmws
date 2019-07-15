package ru.levin.tmws.api.service;

import ru.levin.tmws.service.PersistService;

public interface IPersistService extends IService<PersistService> {

    void serialize();

    void deserialize();

    void saveFxmlXml();

    void saveFxmlJson();

    void loadFxmlXml();

    void loadFxmlJson();

    void saveJaxbXml();

    void saveJaxbJson();

    void loadJaxbXml();

    void loadJaxbJson();

}
