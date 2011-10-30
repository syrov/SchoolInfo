package ru.compscicenter.schoolinfo.storage;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 */

public class Speciality_faculty
{
    private int speciality_id;
    private int faculty_id;

    public Speciality_faculty (int speciality_id, int faculty_id)
    {

        this.speciality_id = speciality_id;
        this.faculty_id = faculty_id;
    }
    public int getSpeciality_id()
    {
        return speciality_id;
    }

    public int getFaculty_id()
    {
        return faculty_id;
    }
}