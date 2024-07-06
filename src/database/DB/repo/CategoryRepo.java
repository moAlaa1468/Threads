package database.DB.repo;

import database.DB.entity.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/*
* If you have 12 table in the database
* you need 12 DTO | BOJO | DTO , 12 class and single ConnectionManager
*
* */


// Category DTO data transfer object | DAO DataAccessObject
// via this class you could access the table in database
// DAO data access Object
// Model ==> Repo used to access data in database
public class CategoryRepo {

    /*
     * DTO = entity = BOJO = class = data Transfer Object [ This CategoryRepo]
     *
     * */


    // This will be for the class ==> and you need to make this for every table
    ConnectionManager connectionManager = new ConnectionManager();


    // They are five function CRUD OPERATIONS
    public void insert(Category category) {
        // Every function of the CRUD operation will use the Object from this class uaAlaa
        try (
                Connection connection = connectionManager.connect();
                Statement statement = connection.createStatement();
        ) {
            // You need to put all you queries here uaAmer
            //for insertCategory

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void update(Category category) {
        // This is the best way yaAlaa to make connection
        try (
                Connection connection = connectionManager.connect();
                Statement statement = connection.createStatement();
        ) {
            /*
             * This is very essential Tool to check if the driver related to
             * mysql connector is exist or not
             * */

            try {
//            String insert = "insert into categories \n"
//                    + "(name,description)\n"
//                    + "values \n"
//                    + "( '" + category.getName() + "' , '" + category.getDescription() + ")";

                int noAffectedRows = statement.executeUpdate("fsdhj");  // used to run the command and update the values by inserting in DB
                /*
                 * To check if the query inserted correctly of not uaAmer
                 * */

            } catch (SQLException e) {
                System.out.println("We failed to connect to database: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void select() {
        try (
                Connection connection = connectionManager.connect();
                Statement statement = connection.createStatement();
        ) {
            // You need to put all you queries here uaAmer
            //for insertCategory

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(int id) {
        try (
                Connection connection = connectionManager.connect();
                Statement statement = connection.createStatement();
        ) {
            // You need to put all you queries here uaAmer
            //for insertCategory

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
