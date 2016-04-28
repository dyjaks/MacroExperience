package com.dyjaks.macroexperience;

import javax.security.auth.Subject;

/**
 * Created by shawnd on 4/25/2016.
 */
public class Ingredient {
    private int ID;
    private String Name;
    private String Serving;
    private String Size;
    private Double Protein;
    private Double Fat;
    private Double Saturated_Fat;
    private Double Mono_Fat;
    private Double Poly_Fat;
    private Double Cholesterol;
    private Double Carb;
    private Double Fiber;
    private Double Sugar;

    public Ingredient(String name, String serving, String Size, Double Protein, Double Fat, Double Carb, Double Fiber, Double Sugar) {
        this.Name = name;
        this.Serving = serving;
        this.Size = Size;
        this.Protein = Protein;
        this.Fat = Fat;
        this.Carb = Carb;
        this.Fiber = Fiber;
        this.Sugar = Sugar;
    }

    public String GetName(){
        return Name;
    }

    public String GetServing() {
        return Serving;
    }

    public String GetServingSize() {
        return Size;
    }

    public Double GetProtein() {
        return Protein;
    }

    public Double GetFat() {
        return Fat;
    }

    public Double GetSatFat() {
        return Saturated_Fat;
    }

    public Double GetMononFat() {
        return Mono_Fat;
    }

    public Double GetChol() {
        return Cholesterol;
    }

    public Double GetCarb() {
        return Carb;
    }

    public Double GetNetCarb() {
        return Carb - Fiber;
    }

    public Double GetFiber() {
        return Fiber;
    }

    public Double GetSubar() {
        return Sugar;
    }

    public Double GetCalories() {
        return ((Fat * 9) + (Protein * 4) + (Carb * 4) * ServingSizeAsDouble());
    }

    private Double ServingSizeAsDouble() {
        try {
            return Double.parseDouble(Size);
        } catch (NumberFormatException e) {
            String[] parts = Size.split("/");
            return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
        }
    }
}
