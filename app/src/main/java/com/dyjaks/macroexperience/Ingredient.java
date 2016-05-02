package com.dyjaks.macroexperience;

import javax.security.auth.Subject;

/**
 * Created by shawnd on 4/25/2016.
 */
public class Ingredient {
    private long ID;
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

    public Ingredient(long Id, String name, String serving, String Size, Double Protein, Double Fat, Double Carb) {
        this.ID = Id;
        this.Name = name;
        this.Serving = serving;
        this.Size = Size;
        this.Protein = Protein;
        this.Fat = Fat;
        this.Carb = Carb;
    }
    public long GetId() {return ID; }

    public void SetId(long i) {this.ID = i;}

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

    public Double GetMonoFat() {
        return Mono_Fat;
    }

    public Double GetPolyFat() {
        return Poly_Fat;
    }

    public Double GetChol() {
        return Cholesterol;
    }

    public Double GetCarb() {
        return Carb;
    }

    public Double GetNetCarb() {
        if (Fiber != null)
            return Carb - Fiber;
        else
            return Carb;
    }

    public Double GetFiber() {
        return Fiber;
    }

    public Double GetSugar() {
        return Sugar;
    }

    public void SetMonoFat(double i) {this.Mono_Fat = i;}
    public void SetSatFat(double i) {this.Saturated_Fat = i;}
    public void SetPolyFat(double i) {this.Poly_Fat = i;}
    public void SetChol(double i) {this.Cholesterol = i;}
    public void SetFiber(double i) {this.Fiber = i;}
    public void SetSugar(double i) {this.Sugar = i;}

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
