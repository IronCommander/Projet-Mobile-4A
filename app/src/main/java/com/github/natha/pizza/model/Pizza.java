package com.github.natha.pizza.model;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pizza implements Comparable, Parcelable {
    private String name,image;
    private ArrayList<String> ingredients,recette,videos;
    private String comparable;
    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }
    public ArrayList<String> getIngredients() {
        return ingredients;
    }
    public ArrayList<String> getRecette() {
        return recette;
    }
    public ArrayList<String> getVideos() {
        return videos;
    }
    private static SharedPreferences sharedPreferences = null;
    public Pizza(String name,String image,String comparable,ArrayList<String> ingredients,ArrayList<String> recette,ArrayList<String> videos){
        this.name = name;
        this.image = image;
        this.ingredients = ingredients;
        this.recette = recette;
        this.videos = videos;
        this.comparable = comparable;
    }
    // Save the data
    public static void setListPizza(SharedPreferences sharedPreferences,List<Pizza> lists){
        Pizza.sharedPreferences = sharedPreferences;
        SharedPreferences.Editor editor = Pizza.sharedPreferences.edit();
        editor.putString("lists", new Gson().toJson(lists));
        editor.apply();
    }
    public static List<Pizza> getListPizza() {
        if(Pizza.sharedPreferences == null) return null;
        return new Gson().fromJson(Pizza.sharedPreferences.getString("lists",null),new TypeToken<List<Pizza>>() {}.getType());
    }
    public static List<Pizza> getListPizza(SharedPreferences sharedPreferences) {
        Pizza.sharedPreferences = sharedPreferences;
        return new Gson().fromJson(Pizza.sharedPreferences.getString("lists",null),new TypeToken<List<Pizza>>() {}.getType());
    }
    public static void clearListPizza() {
        Pizza.sharedPreferences.edit().remove("lists").apply();
    }
    // Comparer pour l'affichage des fragments, selon qu'elle bouton cliqué
    public int compareTo(Object o) {
        Pizza pizza = (Pizza) o;
        switch (comparable){
            case "alphabetique": return this.name.compareTo(pizza.name);
            case "ingredient": return this.ingredients.size()>pizza.ingredients.size()?1:(this.ingredients.size()<pizza.ingredients.size()?-1:0);
            case "video": return this.videos.size()>pizza.videos.size()?1:(this.videos.size()<pizza.videos.size()?-1:0);
            case "recette": return this.recette.size()>pizza.recette.size()?1:(this.recette.size()<pizza.recette.size()?-1:0);
            default: return 0;
        }
    }
    // Pour exporter entre activity
    protected Pizza(Parcel in){
        this.name = in.readString();
        this.image = in.readString();
        this.comparable = in.readString();
        this.ingredients = new ArrayList<>(Arrays.asList(in.createStringArray()));
        this.recette = new ArrayList<>(Arrays.asList(in.createStringArray()));
        this.videos = new ArrayList<>(Arrays.asList(in.createStringArray()));
    }
    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        public Pizza createFromParcel(Parcel in) { return new Pizza(in); }
        public Pizza[] newArray(int size) { return new Pizza[size]; }
    };
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.image);
        dest.writeString(this.comparable);
        dest.writeStringArray(this.ingredients.toArray(new String[this.ingredients.size()]));
        dest.writeStringArray(this.recette.toArray(new String[this.recette.size()]));
        dest.writeStringArray(this.videos.toArray(new String[this.videos.size()]));
    }
}
