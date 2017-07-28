package com.example.danieletavernelli.onstage.database;


import android.provider.BaseColumns;

public final class Contract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Contract() {
    }

    /* Inner classes that defines the tables contents */

    public static class TabStrumenti implements BaseColumns {
        public static final String TABLE_NAME = "TAB_INSTRUMENTS";
        public static final String COLUMN_NAME_DESC_INSTRUMENT = "DESC_INSTRUMENT";
        public static final String COLUMN_NAME_ICON = "ICON";
    }


    public static class TabStage implements BaseColumns {
        public static final String TABLE_NAME = "TAB_STAGE";
        public static final String COLUMN_NAME_DESC_STAGE = "DESC_STAGE";

    }


    public static class TabRelStageInstrument implements BaseColumns {
        public static final String TABLE_NAME = "TAB_REL_STAGE_INSTRUMENT";
        public static final String COLUMN_NAME_STAGE_ID = "STAGE_ID";
        public static final String COLUMN_NAME_INSTRUMENT_ID = "INSTRUMENT_ID";
        public static final String COLUMN_NAME_COORDINATE_X = "COORDINATE_X";
        public static final String COLUMN_NAME_COORDINATE_Y = "COORDINATE_Y";

    }


    /*Script create table*/


    public static final String SQL_CREATE_TAB_INSTRUMENTS =
            "CREATE TABLE " + TabStrumenti.TABLE_NAME + " (" +
                    TabStrumenti._ID + " INTEGER PRIMARY KEY," +
                    TabStrumenti.COLUMN_NAME_DESC_INSTRUMENT + " TEXT," +
                    TabStrumenti.COLUMN_NAME_ICON + " BLOB);";

    public static final String SQL_CREATE_TAB_STAGE =
            "CREATE TABLE " + TabStage.TABLE_NAME + " (" +
                    TabStage._ID + " INTEGER PRIMARY KEY," +
                    TabStage.COLUMN_NAME_DESC_STAGE + " TEXT);";

    public static final String SQL_CREATE_TAB_REL_STAGE_INSTRUMENT =
            "CREATE TABLE " + TabRelStageInstrument.TABLE_NAME + " (" +
                    TabRelStageInstrument._ID + " INTEGER PRIMARY KEY," +
                    TabRelStageInstrument.COLUMN_NAME_STAGE_ID + " INTEGER," +
                    TabRelStageInstrument.COLUMN_NAME_INSTRUMENT_ID + " INTEGER," +
                    TabRelStageInstrument.COLUMN_NAME_COORDINATE_X + " INTEGER," +
                    TabRelStageInstrument.COLUMN_NAME_COORDINATE_Y + " INTEGER);";





    /*Script delete table */
    public static final String SQL_DELETE_TAB_INSTRUMENTS =
            "DROP TABLE IF EXISTS " + TabStrumenti.TABLE_NAME;

    public static final String SQL_DELETE_TAB_STAGE =
            "DROP TABLE IF EXISTS " + TabStage.TABLE_NAME;

    public static final String SQL_DELETE_TAB_REL_STAGE_INSTRUMENT =
            "DROP TABLE IF EXISTS " + TabRelStageInstrument.TABLE_NAME;


}
