package org.example;
import org.example.GUI.ViewInterface;
import org.example.dbcreate.Database;
import org.example.dbcreate.DownloadData;
import org.example.dbtransfer.DBFromModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final String USER_HOME = System.getProperty("user.home");
    public static final String APP_NAME = "Currency Viewer";
    public static final String APP_VERSION = "1.0-SNAPSHOT";
    public static final String APP_FULL_NAME = String.format("%s v.%s", APP_NAME, APP_VERSION);
    public static final Path USER_HOME_PATH = Paths.get(USER_HOME != null ? USER_HOME : "./");
    public static final Path APP_DB_PATH = Paths.get("C:\\Users\\ryazh\\IdeaProjects\\CBRF\\CBRF\\src\\main");
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        log.info("{} started", APP_NAME);
        log.info("Version: {}", APP_VERSION);
        onStart();
        DBFromModel.fillDb(DownloadData.downloadXML("25.10.2022"));
        ViewInterface.showInterface();
    }
    public static void onStart() {
        if(!Files.exists(APP_DB_PATH)){
            try{
                Files.createDirectory(APP_DB_PATH);
            }catch(IOException exc){
                log.error("Database directory was not created");
                throw new RuntimeException(exc);
            }
        }
    }
    public static void onClose(){
        boolean isClosed = Database.getInstance().closeConnection();
        if(!isClosed){
            log.warn("Database connection was not closed");
        }
    }
}