package com.example.yourturnmobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import models.User;


/**
 * Created by Keval on 30-11-2015.
 */
public class RegisterDatabaseHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "YOURTURN_DBASE";

        private static final String USER_DATABASE_NAME = "PERSONS";
        public static final String COLUMN_USER_ID = "_id";
        public static final String COLUMN_USER_NAME = "uname";
        public static final String COLUMN_USER_PHNO = "uphno";
        public static final String COLUMN_USER_BDATE = "ubdate";
        public static final String COLUMN_USER_EMAIL = "uemail";
        public static final String COLUMN_USER_GROUP = "uGroup";
        private static final String USER_DATABASE_CREATE =
                "CREATE TABLE " + USER_DATABASE_NAME + " ( " +
                        COLUMN_USER_ID + " integer primary key autoincrement, " +
                        COLUMN_USER_NAME + " varchar(25), " +
                        COLUMN_USER_PHNO + " text, " +
                        COLUMN_USER_BDATE + " date, " +
                        COLUMN_USER_EMAIL + " varchar(25), " +
                        COLUMN_USER_GROUP + "varchar(25) " +" )";

        private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS ";

        private static final String FETCH_USERS_QUERY = "SELECT * FROM " + USER_DATABASE_NAME;

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(USER_DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_TABLE_QUERY+USER_DATABASE_NAME);
            onCreate(sqLiteDatabase);
        }

        public RegisterDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public long insertUser(User user) {
            ContentValues values = new ContentValues();

            values.put(COLUMN_USER_NAME, user.getUserName());
            Log.d("User Name: ", user.getUserName());
            values.put(COLUMN_USER_PHNO, user.getUserNumber());
            Log.d("User Number: ", user.getUserNumber());
            values.put(COLUMN_USER_BDATE, user.getUserBDate());
            values.put(COLUMN_USER_EMAIL, user.getUserEmail());
            values.put(COLUMN_USER_GROUP,user.getUserGroup());

            return getWritableDatabase().insert(USER_DATABASE_NAME, null, values);
        }

        public Cursor fetchTasks() {
            Cursor cursor = getReadableDatabase().rawQuery(FETCH_USERS_QUERY, null);
            return cursor;
        }

}
