package com.maksimfomenko.utils;

import com.maksimfomenko.dao.connector.Connector;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtils {

    public static void transaction(Connector connector, ConnectionConsumer consumer) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            connection.setAutoCommit(false);
            try {
                consumer.consume(connection);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new SQLException("Exception in transaction", e);
            }
        }
    }

    public interface ConnectionConsumer {
        void consume(Connection connection) throws Exception;
    }
}
