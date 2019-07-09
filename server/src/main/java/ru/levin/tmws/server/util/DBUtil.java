package ru.levin.tmws.server.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.exception.DbConnectionException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

    public static Connection getConnection() throws Exception {
        @NotNull final Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("db.properties")) {
            props.load(in);
        }

        @Nullable final String driver = props.getProperty("jdbc.driver");
        if (driver != null) {
            Class.forName(driver);
        }
        @Nullable final String url = props.getProperty("jdbc.url");
        @Nullable final String username = props.getProperty("jdbc.username");
        @Nullable final String password = props.getProperty("jdbc.password");
        if (url == null) throw new DbConnectionException();
        return DriverManager.getConnection(url, username, password);
    }

}
