package ru.compscicenter.schoolinfo.storage;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 */

public class Ranking_result
{
    private int method_id;
    private int faculty_id;
    private int rank;

    public Ranking_result(int rank)
    {
        //this.method_id = method_id;
        //this.faculty_id = faculty_id;
        this.rank = rank;
    }
    public int getMethod_id()
    {
        return method_id;
    }

    public int fetFaculty_id()
    {
        return faculty_id;
    }

    public int getRank()
    {
        return rank;
    }
}