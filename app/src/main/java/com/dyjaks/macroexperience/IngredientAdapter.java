package com.dyjaks.macroexperience;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private List<Ingredient> ingredientList;

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vMacro;
        protected TextView vServing;
        protected TextView vServingSize;

        IngredientAdapter ia;

        public IngredientViewHolder(View v) {
            super(v);
            vName =  (TextView)v.findViewById(R.id.foodName);
            vMacro = (TextView)v.findViewById(R.id.macros);
            vServing = (TextView)v.findViewById(R.id.serving);
            vServingSize = (TextView)v.findViewById(R.id.servingSize);
        }

        public void OnBind(final Ingredient ingredient) {
            if (ia == null) {
                List<Ingredient> q = new ArrayList<Ingredient>() {{ add(ingredient); }};
                ia = new IngredientAdapter(q);
            } else
                ia.add(ingredient);
            ia.notifyDataSetChanged();
        }
    }

    public IngredientAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder foodViewHolder, int i) {
        Ingredient item = ingredientList.get(i);

        foodViewHolder.vName.setText(item.GetName());
        foodViewHolder.vMacro.setText("P:" + String.format("%.2f", item.GetProtein() * servingConvert(item.GetServingSize())) +
                " C:" + String.format("%.2f", item.GetCarb() * servingConvert(item.GetServingSize())) + "(" + String.format("%.2f", item.GetFiber() * servingConvert(item.GetServingSize())) + ")" +
                " F:" + String.format("%.2f", item.GetFat() * servingConvert(item.GetServingSize())));
        foodViewHolder.vServing.setText(item.GetServing());
        foodViewHolder.vServingSize.setText(item.GetServingSize());
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredient_list_row, viewGroup, false);
        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setMeals(List<Ingredient> ingredients) {
        this.ingredientList = ingredients;
    }

    public void add(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    private double servingConvert(String serving) {
        try {
            return Double.parseDouble(serving);
        } catch (NumberFormatException e) {
            String[] parts = serving.split("/");
            return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
        }
    }

}
