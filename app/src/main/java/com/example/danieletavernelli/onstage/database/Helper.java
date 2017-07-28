package com.example.danieletavernelli.onstage.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import static com.example.danieletavernelli.onstage.database.Contract.SQL_CREATE_TAB_INSTRUMENTS;
import static com.example.danieletavernelli.onstage.database.Contract.SQL_CREATE_TAB_REL_STAGE_INSTRUMENT;
import static com.example.danieletavernelli.onstage.database.Contract.SQL_CREATE_TAB_STAGE;
import static com.example.danieletavernelli.onstage.database.Contract.SQL_DELETE_TAB_INSTRUMENTS;
import static com.example.danieletavernelli.onstage.database.Contract.SQL_DELETE_TAB_REL_STAGE_INSTRUMENT;
import static com.example.danieletavernelli.onstage.database.Contract.SQL_DELETE_TAB_STAGE;

public class Helper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ON_STAGE.db";

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TAB_INSTRUMENTS);
        db.execSQL(SQL_CREATE_TAB_STAGE);
        db.execSQL(SQL_CREATE_TAB_REL_STAGE_INSTRUMENT);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_TAB_INSTRUMENTS);
        db.execSQL(SQL_DELETE_TAB_STAGE);
        db.execSQL(SQL_DELETE_TAB_REL_STAGE_INSTRUMENT);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
