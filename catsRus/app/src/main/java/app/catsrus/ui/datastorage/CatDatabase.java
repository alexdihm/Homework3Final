package app.catsrus.ui.datastorage;

import java.util.HashMap;
import java.util.HashSet;

import app.catsrus.ui.Cat;

public class CatDatabase {
    public static HashMap<Integer, String> cats = new HashMap<>();
    public static HashMap<Integer, String> favourites = new HashMap<>();
    public static HashSet<String> set = new HashSet<>();
    public static void fillDB(Cat[] catArray){
        for(int i = 0; i < catArray.length; i++) {
            cats.put(i, catArray[i].getName());
        }
    }
    public static void addFavourite(String newFavourite){
        if(set.contains(newFavourite)){
            return;
        }
        favourites.put(favourites.size(), newFavourite);
        set.add(newFavourite);
    }
}
