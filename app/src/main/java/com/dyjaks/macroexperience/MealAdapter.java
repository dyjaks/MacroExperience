package com.dyjaks.macroexperience;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private List<Meal> mealList;

    public static class MealViewHolder extends RecyclerView.ViewHolder {
        IngredientAdapter ia = new IngredientAdapter(new ArrayList<Ingredient>());
        protected TextView mealTitle;
        protected TextView mealCalories;

        public MealViewHolder(View v) {
            super(v);
            mealTitle = (TextView)v.findViewById(R.id.mealTitle);
            mealCalories = (TextView)v.findViewById(R.id.mealCalories);

            RecyclerView foodRecyclerView = (RecyclerView)v.findViewById(R.id.ingredientRecyclerView);
            foodRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
            foodRecyclerView.setAdapter(ia);
        }

        public void OnBind(Meal meal) {
            ia.setMeals(meal.getIngredients());
            ia.notifyDataSetChanged();
        }
    }

    public MealAdapter(List<Meal> mealList) {
        this.mealList = mealList;
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    @Override
    public void onBindViewHolder(MealViewHolder mealViewHolder, int i) {
        mealViewHolder.mealTitle.setText(mealList.get(i).mealName);
        mealViewHolder.mealCalories.setText(mealList.get(i).getCalories() + " Cals");

        Meal ml = mealList.get(i);
        mealViewHolder.OnBind(ml);
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meal_card, viewGroup, false);
        return new MealViewHolder(itemView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
