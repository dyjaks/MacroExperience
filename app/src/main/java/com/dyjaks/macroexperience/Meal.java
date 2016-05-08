package com.dyjaks.macroexperience;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;


public class Meal {
    private long ID;
    private DateTime date;
    private List<Ingredient> ingredients;

    public Meal(DateTime date) {
        this.date = date;
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Meal(List<Ingredient> ingredients) {
        this.date = DateTime.now();
        this.ingredients = ingredients;
    }

    public Meal(long id, DateTime date, List<Ingredient> ingredients) {
        this.ID = id;
        this.date = date;
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public long getId() {return ID;}

    public String getCalories() {
        double calories = 0.0;
        for(Ingredient ig : ingredients) {
            calories += Double.parseDouble(ig.GetCalories());
        }
        return String.format("%.2f", calories);
    }

    public String getProtein() {
        double i = 0.0;
        for(Ingredient ig : ingredients) {
            i += Double.parseDouble(ig.GetProtein());
        }
        return String.format("%.2f", i);
    }

    public String getCarbs() {
        double i = 0.0;
        for(Ingredient ig : ingredients) {
            i += Double.parseDouble(ig.GetCarb());
        }
        return String.format("%.2f", i);
    }

    public String getFat() {
        double i = 0.0;
        for(Ingredient ig : ingredients) {
            i += Double.parseDouble(ig.GetFat());
        }
        return String.format("%.2f", i);
    }

    public String getTime() {
        return date.toLocalTime().toString();
    }
}
