package ru.levin.tmws.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.service.IPropertyService;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyService implements IPropertyService {

    @NotNull
    final Properties props = new Properties();

    @Override
    public @Nullable String getJdbcUsername() {
        return props.getProperty("jdbc.username");
    }

    @Override
    public @Nullable String getJdbcPassword() {
        return props.getProperty("jdbc.password");
    }

    @Override
    public @Nullable String getJdbcUrl() {
        return props.getProperty("jdbc.url");
    }

    @Override
    public @Nullable String getJdbcDriver() {
        return props.getProperty("jdbc.driver");
    }

    @SneakyThrows
    public void init() {
        try (FileInputStream in = new FileInputStream("db.properties")) {
            props.load(in);
        }
    }

}
