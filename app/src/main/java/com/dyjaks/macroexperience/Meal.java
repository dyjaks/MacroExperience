package com.dyjaks.macroexperience;

import java.util.Date;
import java.util.List;

/**
 * Created by shawnd on 4/25/2016.
 */
public class Meal {
    private int ID;
    private String mealName;
    private Date date;
    private List<Ingredient> ingredients;

    public Meal(String name, List<Ingredient> ingredients) {
        this.mealName = name;
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }
}
