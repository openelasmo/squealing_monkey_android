package com.example.pk.openvoidjobs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PK on 04/12/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accounts.db";
    private static final String TABLE_NAME = "accounts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table "+TABLE_NAME+" (id integer primary key not null , " +
            "name text not null, email text not null, pass text not null);";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void insertAccount(Account a) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from accounts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();


        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, a.getName());
        values.put(COLUMN_EMAIL, a.getEmail());
        values.put(COLUMN_PASS, a.getPass());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String email) {

        db = this.getReadableDatabase();
        String query = "select email, pass from accounts";
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "not found";

        if(cursor.moveToFirst()) {

            do {

                a = cursor.getString(0);


                if(a.equals(email)) {

                    b = cursor.getString(1);
                    break;
                }

            } while(cursor.moveToNext());

        }

        return b;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
