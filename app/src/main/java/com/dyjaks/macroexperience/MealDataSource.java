package com.dyjaks.macroexperience;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MealDataSource {
    private int mOpenCounter;
    private SQLiteDatabase mDatabase;
    private MealDataSource mData;

    // Database fields
    private MacroSqliteOpenHelper dbHelper;
    private String[] allColumns = { MacroSqliteOpenHelper.COLUMN_ID,
            MacroSqliteOpenHelper.COLUMN_DATE, MacroSqliteOpenHelper.COLUMN_TIME, MacroSqliteOpenHelper.COLUMN_INGREDIENTS};

    public synchronized void initializeInstance(MacroSqliteOpenHelper helper) {
        if (dbHelper == null) {
            dbHelper = helper;
            mDatabase = dbHelper.getWritableDatabase();
        }
    }

    public synchronized MacroSqliteOpenHelper getInstance() {
        if (dbHelper == null) {
            throw new IllegalStateException(MealDataSource.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return dbHelper;
    }

    public void close() {
        dbHelper.close();
    }

    public Meal createMeal(String date, String time, String ingredients) {
        ContentValues values = new ContentValues();
        values.put(MacroSqliteOpenHelper.COLUMN_DATE, date);
        values.put(MacroSqliteOpenHelper.COLUMN_TIME, time);
        values.put(MacroSqliteOpenHelper.COLUMN_INGREDIENTS, ingredients);

        long insertId = mDatabase.insert(MacroSqliteOpenHelper.TABLE_MEALS, null,
                values);
        Cursor cursor = mDatabase.query(MacroSqliteOpenHelper.TABLE_MEALS,
                allColumns, MacroSqliteOpenHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Meal newMeal = cursorToMeal(cursor);
        cursor.close();
        return newMeal;
    }

    public void deleteMeal(Meal meal) {
        long id = meal.getId();
        System.out.println("Comment deleted with id: " + id);
        mDatabase.delete(MacroSqliteOpenHelper.TABLE_MEALS, MacroSqliteOpenHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Meal> getAllMealsForDay(String date) {
        List<Meal> meals = new ArrayList<Meal>();

        String whereClause = MacroSqliteOpenHelper.COLUMN_DATE + " = ?";
        String[] whereArgs = new String[] {date};
        Cursor cursor = mDatabase.query(MacroSqliteOpenHelper.TABLE_MEALS,
                allColumns,whereClause, whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Meal ml = cursorToMeal(cursor);
            meals.add(ml);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return meals;
    }

    private Meal cursorToMeal(Cursor cursor) {
        JSONArray ja;
        List<Ingredient> ig = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(cursor.getString(3));
            ja = jo.getJSONArray("ingredients");

            for (int i = 0; i < ja.length(); i++) {
                JSONObject jObj = ja.getJSONObject(i);
                Ingredient ing = new Ingredient(
                    jObj.getLong("id"),
                    jObj.getString("name"),
                    jObj.getString("serving"),
                    jObj.getString("size"),
                    jObj.getDouble("protein"),
                    jObj.getDouble("fat"),
                    jObj.getDouble("carb ")
                );
                ig.add(ing);
            }
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + cursor.getString(3) + "\"");
        }
        return new Meal(
                cursor.getLong(0),
                new DateTime(cursor.getString(1)),
                new LocalTime(cursor.getString(2)),
                ig);
    }
}
