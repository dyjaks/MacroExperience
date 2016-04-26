package com.dyjaks.macroexperience;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;


public class IngredientListAdapter extends BaseAdapter {
    private Activity activity;
    private Context mainContext;
    private LayoutInflater inflater;
    private List<Food> foodItems;

    public IngredientListAdapter(Activity activity, Context mainContext, List<Food> foodItems) {
        this.activity = activity;
        this.mainContext = mainContext;
        this.foodItems = foodItems;
    }

    @Override
    public int getCount() {
        return foodItems.size();
    }

    @Override
    public Object getItem(int location) {
        return foodItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.ingredient_listview_item, null);

        TextView foodName = (TextView) convertView.findViewById(R.id.foodName);
        TextView macros = (TextView) convertView.findViewById(R.id.macros);
        TextView serving = (TextView) convertView.findViewById(R.id.serving);
        TextView servingSize = (TextView) convertView.findViewById(R.id.servingSize);

        final Food item = foodItems.get(position);
        macros.setText("P:" + String.format( "%.2f", item.GetProtein() * servingConvert(item.GetServingSize())) +
                        " C:" + String.format( "%.2f", item.GetCarb() * servingConvert(item.GetServingSize())) + "(" + String.format( "%.2f", item.GetFiber() * servingConvert(item.GetServingSize())) + ")" +
                        " F:" + String.format( "%.2f", item.GetFat() * servingConvert(item.GetServingSize())));
        serving.setText(item.GetServing());
        servingSize.setText(item.GetServingSize());
        foodName.setText(item.GetName());


        if (!foodItems.get(position).anime) {
            Animation animation = AnimationUtils.loadAnimation(mainContext, R.anim.slide_to_right);
            convertView.startAnimation(animation);
            foodItems.get(position).anime=true;
        }

        return convertView;
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
