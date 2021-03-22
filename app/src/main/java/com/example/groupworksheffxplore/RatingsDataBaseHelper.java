package com.example.groupworksheffxplore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.text.Editable;

import java.sql.RowId;


public final class RatingsDataBaseHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ratings.db";
    public static final String TABLE_NAME = "ratings_table";
    public static final String TABLE_COL_1 = "locationRatingsid";
    public static final String TABLE_COL_2 = "ratingCount";
    public static final String TABLE_COL_3 = "sumOfRatings";
    public static final String TABLE_COL_4 = "averageRating";





    public RatingsDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String dbQuery = "CREATE TABLE Ratings (LOCATIONRATINGID REAL PRIMARY KEY AUTOINCREMENT,RATINGCOUNT REAL, SUMOFRATINGS REAL, AVERAGERATING REAL)";
        db.execSQL(dbQuery);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Ratings");
         onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

        public  boolean insertRating(int ratingcount, float sumOfRatings, float averageRating) {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(TABLE_COL_2, ratingcount);
            contentValues.put(TABLE_COL_3, sumOfRatings);
            contentValues.put(TABLE_COL_4, averageRating);

            // Insert contents into database
        long result = db.insert("ratings", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from " +TABLE_NAME,null);
        return cur;
    }

    public void updateRating(int ratingcount, float sumOfRatings, float averageRating, int locationRatingid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); // Accessing content for overwrite
        contentValues.put(TABLE_COL_1,locationRatingid);
        contentValues.put(TABLE_COL_2,ratingcount);
        contentValues.put(TABLE_COL_3,sumOfRatings);
        contentValues.put(TABLE_COL_4,averageRating);
        db.update(TABLE_NAME,contentValues, "LOCATIONRATINGID = ?", new String[]{String.valueOf(locationRatingid)});



    }

    public Integer deleteData(float locationRatingid){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[]{String.valueOf(locationRatingid)});
    }
}


