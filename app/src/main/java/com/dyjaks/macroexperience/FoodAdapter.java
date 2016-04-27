package com.dyjaks.macroexperience;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<Food> foodItems;

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        protected CardView cv;
        protected TextView vName;
        protected TextView vMacro;
        protected TextView vServing;
        protected TextView vServingSize;

        public FoodViewHolder(View v) {
            super(v);
            cv = (CardView)itemView.findViewById(R.id.ingredientCardView);
            vName =  (TextView) v.findViewById(R.id.foodName);
            vMacro = (TextView)  v.findViewById(R.id.macros);
            vServing = (TextView)  v.findViewById(R.id.serving);
            vServingSize = (TextView) v.findViewById(R.id.servingSize);
        }
    }

    public FoodAdapter(List<Food> foodList) {
        this.foodItems = foodList;
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    @Override
    public void onBindViewHolder(FoodViewHolder foodViewHolder, int i) {
        Food item = foodItems.get(i);
        foodViewHolder.vName.setText(item.GetName());
        foodViewHolder.vMacro.setText("P:" + String.format( "%.2f", item.GetProtein() * servingConvert(item.GetServingSize())) +
                " C:" + String.format( "%.2f", item.GetCarb() * servingConvert(item.GetServingSize())) + "(" + String.format( "%.2f", item.GetFiber() * servingConvert(item.GetServingSize())) + ")" +
                " F:" + String.format( "%.2f", item.GetFat() * servingConvert(item.GetServingSize())));
        foodViewHolder.vServing.setText(item.GetServing());
        foodViewHolder.vServingSize.setText(item.GetServingSize());
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredient_card, viewGroup, false);

        return new FoodViewHolder(itemView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
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
