package ru.compscicenter.schoolinfo.storage;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 */

public class Ranking_raw_info_description
{

    private int method_id;
    private String description;

    public Ranking_raw_info_description(int method_id, String description)
    {

        this.method_id = method_id;
        this.description = description;
    }
    public int getMethod_id()
    {
        return method_id;
    }

    public String getDescription()
    {
        return description;
    }
}
