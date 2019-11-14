package app.catsrus.ui;


import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.HashMap;

public class Cat implements Serializable {
    private String id;
    private String name;
    private String description;
    private HashMap<String, String> weight;
    private String temperament;
    private String origin;
    private String life_span;
    private String wikipedia_url;
    private String dog_friendly;
    private Bitmap image;
    public static String url;

    public Cat(String name){
        this.name=name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, String> getWeight() {
        return weight;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public String getDog_friendly() {
        return dog_friendly;
    }
}
