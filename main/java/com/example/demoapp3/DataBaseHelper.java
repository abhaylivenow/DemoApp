package com.example.demoapp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    // initialize all the items for creating the database
    public static final String DATABASE_NAME = "Table.db";
    public static final String TABLE_NAME = "my_Course_list";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM";

    // Constructor for the database class
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    // create table for the first time
    public void onCreate(SQLiteDatabase db) {
        // sql statement for creating the table
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, ITEM TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    // handle if table already exist
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    // add data to the database
    public boolean add(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2,item);

        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1) return false;
        return true;
    }
    // delete data from the database
    public boolean delete(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryStatement = "DELETE FROM "+ TABLE_NAME + " WHERE " + COL1 + " = " + " 1 ";
        Cursor cursor = db.rawQuery(queryStatement,null);

        if(cursor.moveToFirst()) return true;
        return false;
    }
    // for setting all the values of the database to the arrayList
    public Cursor getDataFromTable(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }
}
