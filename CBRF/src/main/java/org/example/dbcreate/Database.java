package org.example.dbcreate;

import org.example.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;

public class Database {
    private static final Logger log = LoggerFactory.getLogger(Database.class);
    private static final String JDBC_URL = "jdbc:sqlite:C:\\Users\\ryazh\\IdeaProjects\\CBRF\\CBRF\\src\\main\\valutedb.sqlite";
    private static final String SCHEMA_SQL = """
			CREATE TABLE IF NOT EXISTS CurrencyExchange (
			    id INTEGER PRIMARY KEY AUTOINCREMENT,
			    "value" REAL NOT NULL,
			    nominal INTEGER NOT NULL,
			    currency_name VARCHAR(100) NOT NULL,
			    currency_code VARCHAR(3) NOT NULL,
			    "date" DATE NOT NULL
			);""";
    private static Database instance;
    private static Connection connection;
    public static Database getInstance() {
        if (instance == null) {
            log.debug("Initializing database");
            instance = new Database();
            log.debug("Database initialized");
        }
        return instance;
    }
    public Connection getConnection() {
        if(connection == null){
            try{
                log.info("Creating connection to database");
                prepareDirectory();
                connection = DriverManager.getConnection(JDBC_URL);
                connection.setAutoCommit(true);
                initDb();
            }catch(SQLException exception){
                log.error("Error while getting connection");
                throw new RuntimeException(exception);
            }
        }
        return connection;
    }
    private Database() {
    }
    private void prepareDirectory() {
        if(!Files.exists(Main.APP_DB_PATH)){
            try{
                Files.createDirectory(Main.APP_DB_PATH);
            }catch(IOException exc){
                log.error("Database directory was not created");
                throw new RuntimeException(exc);
            }
        }
    }
    private void initDb() {
        try (PreparedStatement stmt = connection.prepareStatement(SCHEMA_SQL)){
            stmt.execute();
        } catch (SQLException e) {
            log.error("Error while initializing database");
            throw new RuntimeException(e);
        }

    }
    public boolean closeConnection() {
        if (connection != null) {
            log.info("Closing connection to database", JDBC_URL);
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Cannot close database connection", e);
                try {
                    connection.close();
                } catch (SQLException ex) {
                    log.error("Cannot finally close database connection", e);
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }
            connection = null;
            return true;
        }
        return false;
    }
}
