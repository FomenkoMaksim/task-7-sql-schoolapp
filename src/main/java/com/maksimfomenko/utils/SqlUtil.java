package com.maksimfomenko.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtil {
    public void executeSQLScriptFile(Connection connection, String sqlFileName) throws IOException, SQLException {
        try (Statement statement = connection.createStatement()) {
            String text = Files.readString(Path.of(sqlFileName));
            statement.executeUpdate(text);
        }
    }

    public void executeSQLScript(Connection connection, String sqlQuery) throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.executeQuery(sqlQuery);
        }
    }

//    private String getSqlQuery(String sqlFileName) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        String text = Files.readString(Path.of(sqlFileName));
//
////        String[] array = text.split("(\\r\\n|\\n\\r|\\r|\\n)");
////
////        for (String s: array) {
////            if (!s.contains("--")) {
////                sb.append(s).append(" ");
////            } else {
////                sb.append(s, 0, s.indexOf("--"));
////            }
////        }
////        return sb.toString();
//        return text;
//    }
}
