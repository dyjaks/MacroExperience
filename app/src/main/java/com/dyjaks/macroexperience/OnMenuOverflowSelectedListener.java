package com.dyjaks.macroexperience;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.PopupMenu;

public class OnMenuOverflowSelectedListener implements View.OnClickListener {
    private Meal meal;
    private Context mContext;

    public OnMenuOverflowSelectedListener(Context context, Meal meal) {
        mContext = context;
        meal = meal;
    }

    @Override
    public void onClick(View v) {
        // This is an android.support.v7.widget.PopupMenu;
        PopupMenu popupMenu = new PopupMenu(mContext, v) {
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