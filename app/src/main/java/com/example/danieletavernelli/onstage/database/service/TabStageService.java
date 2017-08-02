package com.example.danieletavernelli.onstage.database.service;

import android.content.ContentValues;
import android.database.Cursor;


import com.example.danieletavernelli.onstage.database.Contract;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabStageEntity;

import java.util.ArrayList;
import java.util.List;


public class TabStageService {

    private Helper helper;


    public TabStageService(Helper helper) {
        this.helper = helper;
    }



    public void insert(TabStageEntity tabStageEntity) {


        ContentValues values = new ContentValues();
        values.put(Contract.TabStage.COLUMN_NAME_DESC_STAGE, tabStageEntity.getDescStage());


        // Insert the new row, returning the primary key value of the new row
        helper.getWritableDatabase().insert(Contract.TabStage.TABLE_NAME, null, values);

    }




    public void delete(TabStageEntity tabStageEntity) {

        // Define 'where' part of query.
        String selection = Contract.TabStage._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {tabStageEntity.get_ID().toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabRelStageInstrument.TABLE_NAME, selection, selectionArgs);


    }

    public List<TabStageEntity> getStageList() {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Contract.TabStage._ID,
                Contract.TabStage.COLUMN_NAME_DESC_STAGE,
        };



        // How you want the results sorted in the resulting Cursor
        String sortOrder =Contract.TabStage._ID;


        Cursor cursor = helper.getReadableDatabase().query(
                Contract.TabStage.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<TabStageEntity> listToReturn = new ArrayList<>();
        TabStageEntity tabStage;
        while (cursor.moveToNext()) {
            tabStage = new TabStageEntity();
            tabStage.set_ID(cursor.getLong(cursor.getColumnIndexOrThrow(Contract.TabStage._ID)));
            tabStage.setDescStage(cursor.getString(cursor.getColumnIndexOrThrow(Contract.TabStage.COLUMN_NAME_DESC_STAGE)));
            listToReturn.add(tabStage);
        }
        cursor.close();
        return listToReturn;
    }



}
