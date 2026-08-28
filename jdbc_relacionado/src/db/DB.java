package db;

import exception.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection connection = null;
    public static Connection getConnections(){
        try{
            if (connection == null){
                String url = System.getenv("DB_URL");
                String username = System.getenv("DB_USERNAME");
                String password = System.getenv("DB_PASSWORD");

                connection = DriverManager.getConnection(
                        url,
                        username,
                        password
                );
            }
        } catch (SQLException sqlException) {
            throw new DBException(sqlException.getMessage());
        }
        return connection;
    }
    private static Properties loadProperties(){
        try (FileInputStream fileInputStream = new FileInputStream("jdbc_relacionado/db.properties")){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        }catch (IOException ioException){
            throw new DBException(ioException.getMessage());
        }
    }

}
