package com.maksimfomenko.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector implements Connector {
    private static final String JDBC_URL_KEY = "datasource.jdbc-url";
    private static final String USERNAME_KEY = "datasource.username";
    private static final String PASSWORD_KEY = "datasource.password";

    private final HikariDataSource ds;

    public DBConnector(Properties properties) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(properties.getProperty(JDBC_URL_KEY));
        config.setUsername(properties.getProperty(USERNAME_KEY));
        config.setPassword(properties.getProperty(PASSWORD_KEY));

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void close() {
        ds.close();
    }
}

