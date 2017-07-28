package com.example.danieletavernelli.onstage.database.service;

import android.content.ContentValues;


import com.example.danieletavernelli.onstage.database.Contract;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabStage;





public class TabStageService {

    private Helper helper;


    public TabStageService(Helper helper) {
        this.helper = helper;
    }

    public void insert(TabStage tabStage) {


        ContentValues values = new ContentValues();
        values.put(Contract.TabStage.COLUMN_NAME_DESC_STAGE, tabStage.getDescStage());


        // Insert the new row, returning the primary key value of the new row
        helper.getWritableDatabase().insert(Contract.TabStage.TABLE_NAME, null, values);

    }




    public void delete(TabStage tabStage) {

        // Define 'where' part of query.
        String selection = Contract.TabStage._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {tabStage.get_ID().toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabRelStageInstrument.TABLE_NAME, selection, selectionArgs);


    }



}
