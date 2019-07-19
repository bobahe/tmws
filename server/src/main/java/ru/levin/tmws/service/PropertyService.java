package ru.levin.tmws.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.levin.tmws.api.service.IPropertyService;

import java.io.InputStream;
import java.util.Properties;

@Service
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

    @Override
    public @Nullable String getJdbcDialect() {
        return props.getProperty("jdbc.dialect");
    }

    @SneakyThrows
    public void init() {
        try (InputStream in = this.getClass().getResourceAsStream("/db.properties")) {
            props.load(in);
        }
    }

}
