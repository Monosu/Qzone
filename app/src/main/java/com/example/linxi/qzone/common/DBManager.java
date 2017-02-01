package com.example.linxi.qzone.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by linxi on 2017/1/28.
 */

public class DBManager extends SQLiteOpenHelper {
    private static final String dbName="myqzone.db";
    private static final int dbVersion=1;
    private String oSQL="CREATE TABLE IF NOT EXITS USERINFO(" +
            "id INTEGER," +
            "loginname VARCHAR," +
            "password VARCHAR," +
            "lasttime VARCHAR," +
            "registertime VARCHAR," +
            "sex VARCHAR," +
            "nickname VARCHAR," +
            "islogin INTEGER)";

    public DBManager(Context context,int version){

        super(context,dbName, null,version);

    }

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(oSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
