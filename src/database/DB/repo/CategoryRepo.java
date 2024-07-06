package database.DB.repo;

import database.DB.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
----------------- Repo | Repository | repositories for all Classes in java --------------
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
            String query = "INSERT INTO categories (name, description) VALUES ('" +
                    category.getName() + "', '" + category.getDescription() + "');";
            System.out.println("Executing query: " + query);

            int rowsAffected = statement.executeUpdate(query);
            if (rowsAffected == 1) {
                System.out.println("one row affected ");
            } else {
                System.out.println("No Rows affected");
            }

        } catch (SQLException e) {
            System.out.println("We cannot insert you row to the database : " + e.getMessage());
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


    public Category select(String id) {
        Category currentCategory = new Category();
        try (
                Connection connection = connectionManager.connect();
                Statement statement = connection.createStatement();
        ) {
            // You need to put all you queries here uaAmer
            //for insertCategory
            //2. prepare the query
            String query = "SELECT * FROM CATEGORIES WHERE id = " + id + ";";
            //3.execute the query
            ResultSet result = statement.executeQuery(query);
            //4. fetch result

            if (result.next() == true) {
                currentCategory.setId(result.getInt("id"));
                currentCategory.setName(result.getString("name"));
                currentCategory.setName(result.getString("description"));
                return currentCategory;
            } else {
                return null;
            }
            //5.close connection
            // Try with resource will close this connection uaAlaa

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Category> selectAll() {
        List<Category> categories = new ArrayList<Category>();
        try (
                Connection connection = connectionManager.connect();
                Statement statement = connection.createStatement();
        ) {
            // You need to put all you queries here uaAmer
            //for insertCategory
            //2. prepare the query
            String query = "SELECT * FROM CATEGORIES ";
            //3.execute the query
            ResultSet result = statement.executeQuery(query);
            //4. fetch result

            while (result.next() == true) {
                Category currentCategory = new Category(); // You need to make Object to fill fields
                currentCategory.setId(result.getInt("id"));
                currentCategory.setName(result.getString("name"));
                currentCategory.setName(result.getString("description"));
                categories.add(currentCategory);
            }
            //5.close connection
            // Try with resource will close this connection uaAlaa

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
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

class Test {
    public static void main(String[] args) {

        Category category = new Category();
        category.setName("Gamal");
        category.setDescription("he is a friend ");
        CategoryRepo categoryRepo = new CategoryRepo();
        categoryRepo.insert(category);

    }
}
