package com.conormeaney.galileo;
/**
 * Created by CONOR MEANEY K00178122 on 13/04/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";
    private static final String TABLE_NAME = "database";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table database (id integer primary key not null , " +
            "name text not null , username text not null , password text not null);";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertData(database c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from database";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();
        values.put(COLUMN_ID , count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_PASSWORD, c.getPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public String PasswordCheck(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select username, password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String u, p;
        p = "not found";
     if(cursor.moveToFirst())
        {
            do{
                u = cursor.getString(0);

                if(u.equals(uname))
                {
                    p = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return p;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
