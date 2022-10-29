package com.maksimfomenko;

import com.maksimfomenko.dao.connector.DBConnector;
import com.maksimfomenko.utils.Generator;
import com.maksimfomenko.utils.ResourceUtil;
import com.maksimfomenko.utils.SqlUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class SchoolApp {
    private final DBConnector connector;

    public SchoolApp(DBConnector connector) {
        this.connector = connector;
    }

    public void run() throws SQLException, IOException {
        // init db
        SqlUtil sqlUtil = new SqlUtil();
        sqlUtil.executeSQLScriptFile(connector.getConnection(), "C:\\Java\\workspace\\task-7-sql-schoolapp\\src\\main\\resources\\sql\\init_schema.sql");
//        sqlUtil.executeSQLScriptFile(connector.getConnection(), "C:\\Java\\workspace\\task-7-sql-schoolapp\\src\\main\\resources\\sql\\drop_schema.sql");
//        connector.close();

        //  generate data
        Generator generator = new Generator();
        generator.insertData(connector.getConnection());
        connector.close();

        //  loop in menu
    }

    public static void main(String[] args) throws IOException, SQLException {
        Properties properties = ResourceUtil.loadPropertiesFromResources("db.properties");

        try (DBConnector dbConnector = new DBConnector(properties)) {
            new SchoolApp(dbConnector).run();
        }
    }
}
