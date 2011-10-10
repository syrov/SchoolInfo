package ru.compscicenter.schoolinfo.storage;

import java.sql.*;
import java.util.*;

import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;

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
            universities.add(new University(resultSet.getInt(1), resultSet.getString(2)));
        }

        return universities;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}