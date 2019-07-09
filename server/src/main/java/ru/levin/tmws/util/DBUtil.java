package ru.levin.tmws.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.exception.DbConnectionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    @NotNull
    public static Connection getConnection() {
        @NotNull final Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("db.properties")) {
            props.load(in);
        } catch (IOException ioe) {
            throw new DbConnectionException();
        }

        @Nullable final String driver = props.getProperty("jdbc.driver");
        try {
            if (driver != null) {
                Class.forName(driver);
            }
        } catch (ClassNotFoundException cnfe) {
            throw new DbConnectionException();
        }

        @Nullable final String url = props.getProperty("jdbc.url");
        @Nullable final String username = props.getProperty("jdbc.username");
        @Nullable final String password = props.getProperty("jdbc.password");
        if (url == null) throw new DbConnectionException();

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DbConnectionException();
        }
    }

}
