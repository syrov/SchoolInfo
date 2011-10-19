package ru.compscicenter.schoolinfo.storage;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// закачать библиотеку !!!

public class Database {

    private SimpleJdbcTemplate jdbcTemplate;

    private Connection connection;

    public void connectToDB() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/SchoolInfo",
                "user", "");

        if (connection == null) {
            throw new SQLException();
        }
    }

    public void performQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        stmt.close();
    }

    public void addUniversity(University university) throws SQLException {
        /*
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO UNIVERSITIES (NAME) VALUES ('" + university.getName() + "')", Statement.RETURN_GENERATED_KEYS);
        stmt.close();
        */

        try {
            jdbcTemplate.update("INSERT INTO university (name, city, description) VALUES(?, ?, ?);", university.getName(), university.getCity(), university.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
 public List<University> getAll() throws SQLException {
     Statement stmt = connection.createStatement();
     ResultSet resultSet = stmt.executeQuery("SELECT ID, NAME FROM UNIVERSITIES");

     List<University> universities = new ArrayList<University>();
     while (resultSet.next()) {
         // ????????? подлежит исправлению
         universities.add(resultSet.getInt(1), (University) resultSet.getObject(2));
     }

     return universities;
 }   */

    public void closeConnection() throws SQLException {
        connection.close();
    }
}