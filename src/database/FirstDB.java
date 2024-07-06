package database;

import database.DB.entity.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


/*
------------ first step -----------
 * Connect to Database
 * hostname, port,
 * username, password,
 * store_java
 *

 ----------- second step ---------
 you need to prepare the queries uaamer
 *
 * */
public class FirstDB {
    /*
     * Credentials For connect to db
     * */
    final String URL = "jdbc:mysql://localhost:3306/store_java";
    final String USERNAME = "root";
    final String PASSWORD = "";


    public static void main(String[] args) {

        // server is up and we need to connect to it via java code

    }


    // They are five function CRUD OPERATIONS
    public void insertCategory(Category category) {

        /*
         * This is very essential Tool to check if the driver related to
         * mysql connector is exist or not
         * */
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("We cannot find the DriverClass to connect to database uaAmer  ");
        }

        try (
                // first you need to open connection
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                // second you need to prepare the place where you will type your code Like Editor
                //consider the statement is an editor to type you queries inside
                Statement statement = connection.createStatement();
        ) {
//            String insert = "insert into categories \n"
//                    + "(name,description)\n"
//                    + "values \n"
//                    + "( '" + category.getName() + "' , '" + category.getDescription() + ")";

            int result = statement.executeUpdate("fsdhj");  // used to run the command and update the values by inserting in DB
            /*
             * To check if the query inserted correctly of not uaAmer
             * */
            if (result == 0) {
                System.out.println("No rows affected ");
            } else {
                System.out.println("One rows affected ");
            }
        } catch (SQLException e) {
            System.out.println("We failed to connect to database: " + e.getMessage());
        }

    }

    public void updateCategory(Category category) {

    }

    public void selectAllCategories() {

    }

    public void deleteCategory(Category category) {

    }

}
