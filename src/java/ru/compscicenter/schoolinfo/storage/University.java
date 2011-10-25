package ru.compscicenter.schoolinfo.storage;

import org.webharvest.runtime.variables.Variable;

/**
 * User: pritykovskaya
 * Date: 26.09.11
 */

public class University {

    private String name;
    private String city;
    private String description;

    public University(String name)
    {

        this.name = name;
      //  this.city = city;
       // this.description = description;
    }
    public String getName()
    {
        return name;
    }

    public String getCity()
    {
        return city;
    }

    public String getDescription()
    {
        return description;
    }
}