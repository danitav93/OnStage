package com.example.danieletavernelli.onstage.database.service;

import android.content.ContentValues;


import com.example.danieletavernelli.onstage.database.Contract;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabRelStageInstrument;


public class TabRelStageInstrumentService {

    private Helper helper;


    public TabRelStageInstrumentService(Helper helper) {
        this.helper = helper;
    }


    public void insert(TabRelStageInstrument tabRelStageInstrument) {

        ContentValues values = new ContentValues();

        values.put(Contract.TabRelStageInstrument.COLUMN_NAME_STAGE_ID, tabRelStageInstrument.getStageId());
        values.put(Contract.TabRelStageInstrument.COLUMN_NAME_INSTRUMENT_ID, tabRelStageInstrument.getInstrumentId());
        values.put(Contract.TabRelStageInstrument.COLUMN_NAME_COORDINATE_X, tabRelStageInstrument.getCoordinateX());
        values.put(Contract.TabRelStageInstrument.COLUMN_NAME_COORDINATE_Y, tabRelStageInstrument.getCoordinateY());


        // Insert the new row, returning the primary key value of the new row
        helper.getWritableDatabase().insert(Contract.TabRelStageInstrument.TABLE_NAME, null, values);

    }




    public void delete(TabRelStageInstrument tabRelStageInstrument) {

        // Define 'where' part of query.
        String selection = Contract.TabStage._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {tabRelStageInstrument.get_ID().toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabRelStageInstrument.TABLE_NAME, selection, selectionArgs);


    }



}
