package com.example.mysqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = " Data.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "registers";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_MOBILE = "mobile";
    private static final String COLUMN_EMAIL = "email";

    public DataBaseHelper(Context context) {

        super(context, "ProfileData", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_MOBILE + " TEXT, " +
                COLUMN_EMAIL + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteRegisterData(String name, String mobile, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_NAME + " = ? AND " + COLUMN_MOBILE + " = ? AND " + COLUMN_EMAIL + " = ?";
        String[] whereArgs = {name, mobile, email};
        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }

    public void updateRegisterData(String mobile, String newEmail, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, newEmail);
        values.put(COLUMN_NAME, name);
        String whereClause = COLUMN_MOBILE + " = ?";
        String[] whereArgs = {mobile};
        db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }


    public void insertRegisterData(String name, String mobile, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_MOBILE, mobile);
        values.put(COLUMN_EMAIL, email);
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public ArrayList<Profile> readData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Profile> dataList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME);
                String name = cursor.getString(nameColumnIndex);
                //String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                //String mobile = cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE));
                //tring email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                int mobileColumnIndex = cursor.getColumnIndex(COLUMN_MOBILE);
                String mobile = cursor.getString(mobileColumnIndex);
                int emailColumnIndex = cursor.getColumnIndex(COLUMN_EMAIL);
                String email = cursor.getString(emailColumnIndex);
                Profile data = new Profile(name, mobile, email);
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return dataList;
    }

    public void updateData(String mobile, String email, String name) {


        SQLiteDatabase db = this.getWritableDatabase();
//        String strSQL = "UPDATE registers SET name = AAA WHERE &COLUMN_MOBILE = " + mobile;


        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_EMAIL, email);
        db.update(TABLE_NAME, cv, "mobile = ?", new String[]{mobile});
        db.close();


    }

}


