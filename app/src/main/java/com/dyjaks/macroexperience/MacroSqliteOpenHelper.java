package com.dyjaks.macroexperience;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MacroSqliteOpenHelper extends SQLiteOpenHelper {
    private static MacroSqliteOpenHelper sInstance;

    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SERVING = "serving";
    public static final String COLUMN_SERVINGSIZE = "size";
    public static final String COLUMN_PROTEIN = "protein";
    public static final String COLUMN_FAT = "fat";
    public static final String COLUMN_SATFAT = "saturated_fat";
    public static final String COLUMN_MONOFAT = "mono_fat";
    public static final String COLUMN_POLYFAT = "poly_fat";
    public static final String COLUMN_CHOL = "cholesterol";
    public static final String COLUMN_CARB = "carb";
    public static final String COLUMN_FIBER = "fiber";
    public static final String COLUMN_SUGAR = "sugar";

    private static final String DATABASE_NAME = "macros.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_INGREDIENTS + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_SERVING + " text not null, "
            + COLUMN_SERVINGSIZE + " text not null, "
            + COLUMN_PROTEIN + " real not null, "
            + COLUMN_FAT + " real not null, "
            + COLUMN_SATFAT + " real null, "
            + COLUMN_MONOFAT + " real null, "
            + COLUMN_POLYFAT + " real null, "
            + COLUMN_CHOL + " real null, "
            + COLUMN_CARB + " real not null, "
            + COLUMN_FIBER + " real null, "
            + COLUMN_SUGAR + " real null);";

    public MacroSqliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized MacroSqliteOpenHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MacroSqliteOpenHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MacroSqliteOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(db);
    }

}