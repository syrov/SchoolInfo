package ru.compscicenter.schoolinfo.storage;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.sql.*;

// закачать библиотеку !!!

public class Database {

    private Connection connection = null;

    public void connectToDB() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            String userName = "root";
            String password = "Mat-mex2012";
            String url = "jdbc:mysql://localhost/UNIINFO";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, userName, password);
            if (connection == null) {
                throw new SQLException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUniversity(University university) throws SQLException {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM university");
            int id = 0, flag = 0;
            while (rs.next())
                id = rs.getInt("id");
            id++;
            rs = st.executeQuery("SELECT * FROM university");
            while (rs.next())
                if (rs.getString("name").equals(university.getName())) flag = 1;
            if (flag == 0)
                st.executeUpdate("INSERT INTO university VALUES ('" + id + "','" + university.getName() + "','" + university.getCity() + "','" + university.getDescription() + "')");
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDirection(Direction direct) throws SQLException {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM direction");
            int id = 0, flag = 0;
            while (rs.next())
                id = rs.getInt("id");
            id++;
            rs = st.executeQuery("SELECT * FROM direction");
            while (rs.next())
                if (rs.getString("name").equals(direct.getName())) flag = 1;
            if (flag == 0)
                st.executeUpdate("INSERT INTO direction VALUES ('" + id + "','" + direct.getName() + "')");
            st.close();
        } catch (Exception e) {
        }
    }

    public void addFaculty(Faculty facult) throws SQLException {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM faculty");
            int id = 0, flag = 0;
            while (rs.next())
                id = rs.getInt("id");
            id++;
            rs = st.executeQuery("SELECT * FROM university");
            while (rs.next())
                if (rs.getString("name").equals(facult.getName()) && rs.getString("university_id").equals(facult.getUniversity_id()))
                    flag = 1;
            if (flag == 0)
                st.executeUpdate("INSERT INTO faculty VALUES ('" + id + "','" + facult.getUniversity_id() + "','" + facult.getName() + "','" + facult.getDescription() + "')");
            st.close();
        } catch (Exception e) {
        }
    }

    public void addRanking_method(Ranking_method rank) throws SQLException {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM ranking_method");
            int id = 0;
            while (rs.next())
                id = rs.getInt("id");
            id++;
            st.executeUpdate("INSERT INTO ranking_method VALUES ('" + id + "','" + rank.getDerection_id() + "','" + rank.getCoeff() + "','" + rank.getImplement_class() + "')");
            st.close();
        } catch (Exception e) {
        }
    }

    public void addRanking_raw_info_description(Ranking_raw_info_description rank) throws SQLException {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM ranking_raw_info_description");
            int id = 0;
            while (rs.next())
                id = rs.getInt("id");
            id++;
            st.executeUpdate("INSERT INTO ranking_raw_info_description VALUES ('" + id + "','" + rank.getMethod_id() + "','" + rank.getDescription() + "')");
            st.close();
        } catch (Exception e) {
        }
    }

    public void addRanking_raw_info_result(Ranking_raw_info_result rank) throws SQLException {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM ranking_raw_info_result");
            int id = 0;
            while (rs.next())
                id = rs.getInt("id");
            id++;
            st.executeUpdate("INSERT INTO ranking_raw_info_result VALUES ('" + id + "','" + rank.getFaculty_id() + "','" + rank.getRanking_raw_info_description_id() + "','" + rank.getValue() + "')");
            st.close();
        } catch (Exception e) {
        }
    }

    public void addSpeciality(Speciality spec) throws SQLException {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM speciality");
            int id = 0, flag = 0;
            while (rs.next())
                id = rs.getInt("id");
            id++;
            rs = st.executeQuery("SELECT * FROM university");
            while (rs.next())
                if (rs.getString("name").equals(spec.getName()) && rs.getString("direction_id").equals(spec.getDirection_id()))
                    flag = 1;
            if (flag == 0)
                st.executeUpdate("INSERT INTO speciality VALUES ('" + id + "','" + spec.getDirection_id() + "','" + spec.getName() + "')");
            st.close();
        } catch (Exception e) {
        }
    }

    public void addSpeciality_faculty(Speciality_faculty spec) throws SQLException {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO speciality_faculty VALUES ('" + spec.getSpeciality_id() + "','" + spec.getFaculty_id() + "')");
            st.close();
        } catch (Exception e) {
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }


}