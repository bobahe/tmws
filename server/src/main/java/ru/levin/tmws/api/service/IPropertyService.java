package ru.levin.tmws.api.service;

import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.service.PropertyService;

public interface IPropertyService extends IService<PropertyService> {

    void init();
    @Nullable String getJdbcUsername();
    @Nullable String getJdbcPassword();
    @Nullable String getJdbcUrl();
    @Nullable String getJdbcDriver();

}
