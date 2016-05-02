package com.dyjaks.macroexperience;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class IngredientDataSource {
    private int mOpenCounter;
    private SQLiteDatabase mDatabase;
    private IngredientDataSource ingData;

    // Database fields
    private MacroSqliteOpenHelper dbHelper;
    private String[] allColumns = { MacroSqliteOpenHelper.COLUMN_ID,
            MacroSqliteOpenHelper.COLUMN_NAME, MacroSqliteOpenHelper.COLUMN_SERVING, MacroSqliteOpenHelper.COLUMN_SERVINGSIZE,
            MacroSqliteOpenHelper.COLUMN_PROTEIN, MacroSqliteOpenHelper.COLUMN_FAT, MacroSqliteOpenHelper.COLUMN_SATFAT,
            MacroSqliteOpenHelper.COLUMN_MONOFAT, MacroSqliteOpenHelper.COLUMN_POLYFAT, MacroSqliteOpenHelper.COLUMN_CHOL,
            MacroSqliteOpenHelper.COLUMN_CARB, MacroSqliteOpenHelper.COLUMN_FIBER, MacroSqliteOpenHelper.COLUMN_SUGAR};

    public synchronized void initializeInstance(MacroSqliteOpenHelper helper) {
        if (dbHelper == null) {
            dbHelper = helper;
            mDatabase = dbHelper.getWritableDatabase();
        }
    }

    public synchronized MacroSqliteOpenHelper getInstance() {
        if (dbHelper == null) {
            throw new IllegalStateException(IngredientDataSource.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return dbHelper;
    }

    public void close() {
        dbHelper.close();
    }

    public Ingredient createIngredient(String name, String serving, String servingSize, Double protein,
                                        Double fat, Double sFat, Double mFat, Double pFat, Double chol,
                                        Double carb, Double fiber, Double sugar) {
        ContentValues values = new ContentValues();
        values.put(MacroSqliteOpenHelper.COLUMN_NAME, name);
        values.put(MacroSqliteOpenHelper.COLUMN_SERVING, serving);
        values.put(MacroSqliteOpenHelper.COLUMN_SERVINGSIZE, servingSize);
        values.put(MacroSqliteOpenHelper.COLUMN_PROTEIN, protein);
        values.put(MacroSqliteOpenHelper.COLUMN_FAT, fat);
        values.put(MacroSqliteOpenHelper.COLUMN_SATFAT, sFat);
        values.put(MacroSqliteOpenHelper.COLUMN_MONOFAT, mFat);
        values.put(MacroSqliteOpenHelper.COLUMN_POLYFAT, pFat);
        values.put(MacroSqliteOpenHelper.COLUMN_CHOL, chol);
        values.put(MacroSqliteOpenHelper.COLUMN_CARB, carb);
        values.put(MacroSqliteOpenHelper.COLUMN_FIBER, fiber);
        values.put(MacroSqliteOpenHelper.COLUMN_SUGAR, sugar);

        long insertId = mDatabase.insert(MacroSqliteOpenHelper.TABLE_INGREDIENTS, null,
                values);
        Cursor cursor = mDatabase.query(MacroSqliteOpenHelper.TABLE_INGREDIENTS,
                allColumns, MacroSqliteOpenHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Ingredient newIngredient = cursorToIngredient(cursor);
        cursor.close();
        return newIngredient;
    }

    public void deleteIngredient(Ingredient ingredient) {
        long id = ingredient.GetId();
        System.out.println("Comment deleted with id: " + id);
        mDatabase.delete(MacroSqliteOpenHelper.TABLE_INGREDIENTS, MacroSqliteOpenHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        Cursor cursor = mDatabase.query(MacroSqliteOpenHelper.TABLE_INGREDIENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ingredient ing = cursorToIngredient(cursor);
            ingredients.add(ing);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return ingredients;
    }

    private Ingredient cursorToIngredient(Cursor cursor) {
        return new Ingredient(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getDouble(4),
                cursor.getDouble(10),
                cursor.getDouble(5)
        );
    }
}