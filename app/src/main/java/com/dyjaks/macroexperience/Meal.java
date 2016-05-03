package com.dyjaks.macroexperience;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by shawnd on 4/25/2016.
 */
public class Meal {
    private int ID;
    public String mealName;
    private String date;
    private String time;
    private List<Ingredient> ingredients;

    public Meal(String name, List<Ingredient> ingredients) {
        this.mealName = name;
        this.date = getTimeInfo("yyyy-MM-dd");
        this.time = getTimeInfo("hh:mm:ss");
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

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
