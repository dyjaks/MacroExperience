package com.dyjaks.macroexperience;

import android.media.Image;

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

    public String GetProtein() {
        return String.format("%.2f", Protein * ServingSizeAsDouble());
    }

    public String GetFat() {
        return String.format("%.2f", Fat * ServingSizeAsDouble());
    }

    public String GetSatFat() {
        return (Saturated_Fat == null) ? "" : String.format("%.2f", Saturated_Fat * ServingSizeAsDouble());
    }

    public String GetMonoFat() {
        return (Mono_Fat == null) ? "" : String.format("%.2f", Mono_Fat * ServingSizeAsDouble());
    }

    public String GetPolyFat() {
        return (Poly_Fat == null) ? "" :  String.format("%.2f", Poly_Fat * ServingSizeAsDouble());
    }

    public String GetChol() {
        return (Cholesterol == null) ? "" : String.format("%.2f", Cholesterol * ServingSizeAsDouble());
    }

    public String GetCarb() {
        return String.format("%.2f", Carb * ServingSizeAsDouble());
    }

    public String GetNetCarb() {
        if (Fiber != null)
            return String.format("%.2f", (Carb - Fiber) * ServingSizeAsDouble());
        else
            return String.format("%.2f", Carb * ServingSizeAsDouble());
    }

    public String GetFiber() {
        return (Fiber == null) ? "" :  String.format("%.2f", Fiber * ServingSizeAsDouble());
    }

    public String GetSugar() {
        return (Sugar == null) ? "" : String.format("%.2f", Sugar * ServingSizeAsDouble());
    }

    public void SetMonoFat(double i) {this.Mono_Fat = i;}
    public void SetSatFat(double i) {this.Saturated_Fat = i;}
    public void SetPolyFat(double i) {this.Poly_Fat = i;}
    public void SetChol(double i) {this.Cholesterol = i;}
    public void SetFiber(double i) {this.Fiber = i;}
    public void SetSugar(double i) {this.Sugar = i;}

    public String GetCalories() {
        return String.format("%.2f", ((Fat * 9) + (Protein * 4) + (Carb * 4) * ServingSizeAsDouble()));
    }

    private Double ServingSizeAsDouble() {
        try {
            return Double.parseDouble(Serving);
        } catch (NumberFormatException e) {
            String[] parts = Serving.split("/");
            return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
        }
    }
}
