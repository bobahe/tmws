package ru.levin.tmws.server.util;

import org.jetbrains.annotations.NotNull;

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

        String driver = props.getProperty("jdbc.driver");
        if (driver != null) {
            Class.forName(driver);
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }

}
