package com.example.houcem.natco.data;

/**
 * Created by houcem on 22/11/16.
 */

public class PeopleStructure {
    private String name;
    private String image;
    private String description;
    private String email;
    private String facebook;

    public PeopleStructure(String name, String image, String description, String email, String facebook) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.email = email;
        this.facebook = facebook;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
