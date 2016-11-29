package com.example.houcem.natco.data;

/**
 * Created by houcem on 24/11/16.
 */

public class HotelStructure {

    private String name;
    private String image;
    private String description;
    private String directions;

    public HotelStructure(String name, String image, String description, String directions) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.directions = directions;
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

    public String getDirections() {
        return directions;
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

    public void setDirections(String email) {
        this.directions = email;
    }

}
