package com.dyjaks.macroexperience;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private List<Ingredient> ingredientList;

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vMacro;
        protected TextView vServing;
        protected TextView vServingSize;

        public IngredientViewHolder(View v) {
            super(v);
            vName =  (TextView)v.findViewById(R.id.foodName);
            vMacro = (TextView)v.findViewById(R.id.macros);
            vServing = (TextView)v.findViewById(R.id.serving);
            vServingSize = (TextView)v.findViewById(R.id.servingSize);
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
        foodViewHolder.vMacro.setText("P:" + String.format(item.GetProtein() + " C:" + item.GetCarb() + " F:" + item.GetFat()));
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

}
