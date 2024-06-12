package com.example.ktra.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ktra.Model.Foods;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cart_db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String TABLE_CART = "Cart";

    // Column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_NUMBER_IN_CART = "number_in_cart";
    public static final String COLUMN_IMAGE_PATH = "image_path";

    // Create table SQL query
    private static final String CREATE_TABLE_CART = "CREATE TABLE " + TABLE_CART + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TITLE + " TEXT,"
            + COLUMN_PRICE + " REAL,"
            + COLUMN_NUMBER_IN_CART + " INTEGER,"
            + COLUMN_IMAGE_PATH + " TEXT"
            + ")";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    public void insertFood(Foods food) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_TITLE, food.getTitle());
        values.put(SQLiteHelper.COLUMN_PRICE, food.getPrice());
        values.put(SQLiteHelper.COLUMN_NUMBER_IN_CART, food.getNumberInCart());
        values.put(SQLiteHelper.COLUMN_IMAGE_PATH, food.getImagePath());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(SQLiteHelper.TABLE_CART, null, values);
        db.close();
    }

    public ArrayList<Foods> getAllFoods() {
        ArrayList<Foods> foodList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SQLiteHelper.TABLE_CART, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Foods food = new Foods();
                food.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_TITLE)));
                food.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_PRICE)));
                food.setImagePath(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_IMAGE_PATH)));
                food.setNumberInCart(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_NUMBER_IN_CART)));
                foodList.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodList;
    }

    public void updateFood(Foods food) {
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_NUMBER_IN_CART, food.getNumberInCart());

        db.update(SQLiteHelper.TABLE_CART, values, SQLiteHelper.COLUMN_TITLE + " = ?", new String[]{food.getTitle()});
        db.close();
    }

    public void deleteFood(String title) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SQLiteHelper.TABLE_CART, SQLiteHelper.COLUMN_TITLE + " = ?", new String[]{title});
        db.close();
    }

    public void clearCart() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SQLiteHelper.TABLE_CART, null, null);
        db.close();
    }
}
