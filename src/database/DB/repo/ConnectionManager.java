package database.DB.repo;

import java.sql.*;

/*
 * This is the main Class used to connect to database
 * And this class will provide objects for any repo that needs to
 * connect to database to its CRUD operations
 * */
public class ConnectionManager {
    final String URL = "jdbc:mysql://localhost:3306/store_java";
    final String USERNAME = "root";
    final String PASSWORD = "";

    public Connection connect() {
        Connection connection = null;
        // This is used to make sure // For making sure that the class is in libraries
        // You must not use Try with resource here uaAmer
        // because you need to return the connection Not close it before returning the connection
        try {
            Class.forName("com.mysql.jdbc.Driver"); // For making sure that the class is in libraries
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("WE failed to open connection to our database Thanks very much : " + e.getMessage());
        }

        // This is used to make connection

        // method should return connection
        return connection;
    }
}
