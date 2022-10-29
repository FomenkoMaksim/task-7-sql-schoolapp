package com.maksimfomenko.dao.connector;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

public interface Connector extends Closeable {
    Connection getConnection() throws SQLException;
}
