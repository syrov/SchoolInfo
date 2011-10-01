package ru.pritykovskaya;

import java.sql.SQLException;
import java.util.List;

/**
 * User: pritykovskaya
 * Date: 26.09.11
 */
public class UniversitiesDAOTest {
    public static void main(String[] args) throws SQLException {
        UniversitiesDAO universitiesDAO = new UniversitiesDAO();
        universitiesDAO.connectToDB();

        University newUniversity = new University("SPbGU");
        universitiesDAO.add(newUniversity);

        List<University> universities = universitiesDAO.getAll();

//        for (int i = 0; i < universities.size(); ++i) {
//            System.out.println(universities.get(i).toString());
//        }
//
        for (University university : universities) {
            System.out.println(university.toString());
        }

        universitiesDAO.closeConnection();
    }
}
