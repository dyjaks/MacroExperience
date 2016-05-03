package com.dyjaks.macroexperience;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Meal {
    private long ID;
    private String date;
    private String time;
    private List<Ingredient> ingredients;

    public Meal(List<Ingredient> ingredients) {
        this.date = getTimeInfo("yyyy-MM-dd");
        this.time = getTimeInfo("hh:mm:ss");
        this.ingredients = ingredients;
    }

    public Meal(String mealTime, List<Ingredient> ingredients) {
        this.date = getTimeInfo("yyyy-MM-dd");
        this.time = mealTime;
        this.ingredients = ingredients;
    }

    public Meal(long id, String date, String time, List<Ingredient> ingredients) {
        this.ID = id;
        this.date = date;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    private String getTimeInfo(String format) {
        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(current);
    }
}
