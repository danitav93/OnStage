package com.example.danieletavernelli.onstage.database.service;

import android.content.ContentValues;


import com.example.danieletavernelli.onstage.database.Contract;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabRelStageInstrumentEntity;


public class TabRelStageInstrumentService {

    private Helper helper;


    public TabRelStageInstrumentService(Helper helper) {
        this.helper = helper;
    }


    public void insert(TabRelStageInstrumentEntity tabRelStageInstrumentEntity) {

        ContentValues values = new ContentValues();

        values.put(Contract.TabRelStageInstrument.COLUMN_NAME_STAGE_ID, tabRelStageInstrumentEntity.getStageId());
        values.put(Contract.TabRelStageInstrument.COLUMN_NAME_INSTRUMENT_ID, tabRelStageInstrumentEntity.getInstrumentId());
        values.put(Contract.TabRelStageInstrument.COLUMN_NAME_COORDINATE_X, tabRelStageInstrumentEntity.getCoordinateX());
        values.put(Contract.TabRelStageInstrument.COLUMN_NAME_COORDINATE_Y, tabRelStageInstrumentEntity.getCoordinateY());


        // Insert the new row, returning the primary key value of the new row
        helper.getWritableDatabase().insert(Contract.TabRelStageInstrument.TABLE_NAME, null, values);

    }




    public void delete(TabRelStageInstrumentEntity tabRelStageInstrumentEntity) {

        // Define 'where' part of query.
        String selection = Contract.TabStage._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {tabRelStageInstrumentEntity.get_ID().toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabRelStageInstrument.TABLE_NAME, selection, selectionArgs);


    }



}
