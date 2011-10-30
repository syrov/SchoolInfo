package ru.compscicenter.schoolinfo.storage;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 */

public class Ranking_raw_info_result
{

    private int faculty_id;
    private int  ranking_raw_info_description_id;
    private double value;

    public Ranking_raw_info_result (double value)
    {
        //this.faculty_id = faculty_id;
        //this.ranking_raw_info_description_id = ranking_raw_info_description_id;
        this.value = value;
    }
    public int getFaculty_id()
    {
        return faculty_id;
    }

    public int getRanking_raw_info_description_id()
    {
        return ranking_raw_info_description_id;
    }

    public double getValue()
    {
        return value;
    }

}