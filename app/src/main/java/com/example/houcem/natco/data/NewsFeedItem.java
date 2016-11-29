package com.example.houcem.natco.data;


/**
 * Created by houcem on 22/11/16.
 */

public class NewsFeedItem {
    private String description;
    private String imageURL;

    public NewsFeedItem(String description,String URL){
        this.description = description;
        this.imageURL = URL;

    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {

        return description;
    }
}
