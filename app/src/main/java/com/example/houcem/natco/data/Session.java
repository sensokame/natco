package com.example.houcem.natco.data;

/**
 * Created by houcem on 05/12/16.
 */

public class Session {
    private String description;
    private String Name;


    public Session(String description, String name) {
        this.description = description;
        this.Name = name;
    }





    public String getDescription() {
        return description;
    }

    public String getName() {
        return Name;
    }




    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        Name = name;
    }
}
