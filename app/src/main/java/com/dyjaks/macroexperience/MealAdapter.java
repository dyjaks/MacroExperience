package com.dyjaks.macroexperience;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private List<Meal> mealList;

    public static class MealViewHolder extends RecyclerView.ViewHolder {
        IngredientAdapter ia = new IngredientAdapter(new ArrayList<Ingredient>());
        protected TextView mealMacros;
        protected TextView mealTime;
        protected TextView mealCalories;
        protected ImageView mealOverflow;

        public MealViewHolder(View v) {
            super(v);
            mealMacros = (TextView)v.findViewById(R.id.mealMacros);
            mealTime = (TextView)v.findViewById(R.id.mealTime);
            mealCalories = (TextView)v.findViewById(R.id.mealCalories);
            mealOverflow = (ImageView)v.findViewById(R.id.mealOverflow);

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
        mealViewHolder.mealMacros.setText("P:" + mealList.get(i).getProtein() + " C:" + mealList.get(i).getCarbs() + " F:" + mealList.get(i).getFat());
        mealViewHolder.mealCalories.setText(mealList.get(i).getCalories() + " Cals");
        mealViewHolder.mealTime.setText(" @ " + mealList.get(i).getTime());

        mealViewHolder.mealOverflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is an android.support.v7.widget.PopupMenu;
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view) {
                    @Override
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.meal_overflow_delete:
                                return true;

                            case R.id.meal_overflow_rename:
                                return true;

                            case R.id.meal_overflow_copy:
                                return true;

                            default:
                                return super.onMenuItemSelected(menu, item);
                        }
                    }
                };

                popupMenu.inflate(R.menu.meal_overflow);
                popupMenu.show();
            }
        }
        );

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
