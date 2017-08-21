package com.example.danieletavernelli.onstage.database.service;

import android.content.ContentValues;
import android.database.Cursor;


import com.example.danieletavernelli.onstage.database.Contract;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabStageEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TabStageService implements Serializable {

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
        String selection = Contract.TabStage._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {tabStageEntity.get_ID().toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabStage.TABLE_NAME, selection, selectionArgs);


    }

    public void update(TabStageEntity tabStageEntity) {


        // New value for one column
        ContentValues values = new ContentValues();
        values.put(Contract.TabStage.COLUMN_NAME_DESC_STAGE,tabStageEntity.getDescStage() );

        // Which row to update, based on the title
        String selection = Contract.TabStage._ID + " = ?";
        String[] selectionArgs = { tabStageEntity.get_ID().toString() };

        int count = helper.getWritableDatabase().update(
                Contract.TabStage.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public List<TabStageEntity> getStageList() {

        // How you want the results sorted in the resulting Cursor
        String sortOrder =Contract.TabStage._ID;

        Cursor cursor = helper.getReadableDatabase().query(
                Contract.TabStage.TABLE_NAME,                     // The table to query
                Contract.TabStageProjection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        return fromCursorToArrayList(cursor);
    }


    public TabStageEntity getNewTabStage() {
        ContentValues values = new ContentValues();
        values.put(Contract.TabStage.COLUMN_NAME_DESC_STAGE,"");
        Long newId = helper.getWritableDatabase().insert(Contract.TabStage.TABLE_NAME, null, values);
        return getStageById(newId);
    }

    public TabStageEntity getStageById(Long id) {
        String selection = Contract.TabStage._ID + " = ?";
        String[] selectionArgs = {id.toString()};
        Cursor cursor = helper.getReadableDatabase().query(
                Contract.TabStage.TABLE_NAME,                     // The table to query
                Contract.TabStageProjection,                               // The columns to return
                selection,                                     // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ArrayList<TabStageEntity> listStage = fromCursorToArrayList(cursor);
        if(listStage.size()>0) {
            return listStage.get(0);
        }
        return null;
    }

    public void deleteByIdStage(Long id) {
        // Define 'where' part of query.
        String selection = Contract.TabStage._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {id.toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabStage.TABLE_NAME, selection, selectionArgs);
    }

    private ArrayList<TabStageEntity> fromCursorToArrayList(Cursor cursor) {
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
