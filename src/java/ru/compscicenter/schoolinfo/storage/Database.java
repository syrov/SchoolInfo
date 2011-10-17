package ru.compscicenter.schoolinfo.storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
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

    public void add(University university) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO UNIVERSITIES (NAME) VALUES ('" + university.getName() + "')", Statement.RETURN_GENERATED_KEYS);
        stmt.close();
    }


    public List<University> getAll() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT ID, NAME FROM UNIVERSITIES");

        List<University> universities = new ArrayList<University>();
        while (resultSet.next()) {
            // ????????? подлежит исправлению
            universities.add(resultSet.getInt(1), (University) resultSet.getObject(2));
        }

        return universities;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}