package com.example.danieletavernelli.onstage.database.service;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.danieletavernelli.onstage.database.Contract;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabInstrumentEntity;

import java.util.ArrayList;


public class TabInstrumentService {

    private Helper helper;


    public TabInstrumentService(Helper helper) {
       this.helper = helper;
    }

    public void insert(TabInstrumentEntity tabInstrumentEntity) {


        ContentValues values = new ContentValues();
        values.put(Contract.TabInstrument.COLUMN_NAME_DESC_INSTRUMENT, tabInstrumentEntity.getDescInstrument());
        values.put(Contract.TabInstrument.COLUMN_NAME_ICON, tabInstrumentEntity.getIcon());

        // Insert the new row, returning the primary key value of the new row
        helper.getWritableDatabase().insert(Contract.TabInstrument.TABLE_NAME, null, values);

    }


    public TabInstrumentEntity getTabStrumentiByDescStrumento(String descStrumento) {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.


        // Filter results WHERE "title" = 'My Title'
        String selection = Contract.TabInstrument.COLUMN_NAME_DESC_INSTRUMENT + " = ?";
        String[] selectionArgs = {descStrumento};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Contract.TabInstrument.COLUMN_NAME_DESC_INSTRUMENT;

        Cursor cursor = helper.getReadableDatabase().query(
                Contract.TabInstrument.TABLE_NAME,                     // The table to query
                Contract.TabInstrumentProjection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<TabInstrumentEntity> strumenti = fromCursorToArrayList(cursor);
        if (strumenti.size()>0) {
            return strumenti.get(0);
        }
        return null;
    }

    public void delete(TabInstrumentEntity tabInstrumentEntity) {

        // Define 'where' part of query.
        String selection = Contract.TabInstrument._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {tabInstrumentEntity.get_ID().toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabInstrument.TABLE_NAME, selection, selectionArgs);


    }

    public void update(TabInstrumentEntity tabInstrumentEntity) {


        /*// New value for one column
        ContentValues values = new ContentValues();
        values.put(Contract.TabInstrumentEntity., title);

        // Which row to update, based on the title
        String selection = FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "MyTitle" };

        int count = db.update(
                FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);*/
    }


    public void insertArrayTabStrumenti (TabInstrumentEntity[] arrayTabInstrumentEntity) {

        for (TabInstrumentEntity tabInstrumentEntity : arrayTabInstrumentEntity) {
            insert(tabInstrumentEntity);
        }

    }

    public ArrayList<TabInstrumentEntity> getAllStrumenti() {
        String[] projection = {
                Contract.TabInstrument._ID,
                Contract.TabInstrument.COLUMN_NAME_DESC_INSTRUMENT,
                Contract.TabInstrument.COLUMN_NAME_ICON
        };

        String sortOrder =
                Contract.TabInstrument.COLUMN_NAME_DESC_INSTRUMENT;

        Cursor cursor = helper.getReadableDatabase().query(
                Contract.TabInstrument.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

       return fromCursorToArrayList(cursor);

    }

    public TabInstrumentEntity getTabStrumentiByIdStrumento(Long instrumentId) {
        String[] projection = {
                Contract.TabInstrument._ID,
                Contract.TabInstrument.COLUMN_NAME_DESC_INSTRUMENT,
                Contract.TabInstrument.COLUMN_NAME_ICON
        };

        // Filter results WHERE
        String selection = Contract.TabInstrument._ID + " = ?";
        String[] selectionArgs = {instrumentId.toString()};

        String sortOrder =
                Contract.TabInstrument.COLUMN_NAME_DESC_INSTRUMENT;

        Cursor cursor = helper.getReadableDatabase().query(
                Contract.TabInstrument.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<TabInstrumentEntity> arrayToReturn = fromCursorToArrayList(cursor);
        if (arrayToReturn.size()>0) {
            return arrayToReturn.get(0);
        }

        return null;
    }

    private ArrayList<TabInstrumentEntity> fromCursorToArrayList (Cursor cursor) {
        ArrayList<TabInstrumentEntity> listToReturn = new ArrayList<>();
        TabInstrumentEntity tabInstrumentEntity;
        while (cursor.moveToNext()) {
            tabInstrumentEntity = new TabInstrumentEntity();
            tabInstrumentEntity.set_ID(cursor.getLong(cursor.getColumnIndexOrThrow(Contract.TabInstrument._ID)));
            tabInstrumentEntity.setDescInstrument(cursor.getString(cursor.getColumnIndexOrThrow(Contract.TabInstrument.COLUMN_NAME_DESC_INSTRUMENT)));
            tabInstrumentEntity.setIcon(cursor.getBlob(cursor.getColumnIndexOrThrow(Contract.TabInstrument.COLUMN_NAME_ICON)));
            listToReturn.add(tabInstrumentEntity);
        }
        cursor.close();
        return listToReturn;
    }


    public void deleteByInstrumentId(Long instrumentId) {
        // Define 'where' part of query.
        String selection = Contract.TabInstrument._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {instrumentId.toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabInstrument.TABLE_NAME, selection, selectionArgs);
    }
}
