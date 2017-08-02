package com.example.danieletavernelli.onstage.database.service;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.danieletavernelli.onstage.database.Contract;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabStrumenti;

import java.util.ArrayList;


public class TabStrumentiService {

    private Helper helper;


    public TabStrumentiService(Helper helper) {
       this.helper = helper;
    }

    public void insert(TabStrumenti tabStrumenti) {


        ContentValues values = new ContentValues();
        values.put(Contract.TabStrumenti.COLUMN_NAME_DESC_INSTRUMENT, tabStrumenti.getDescInstrument());
        values.put(Contract.TabStrumenti.COLUMN_NAME_ICON, tabStrumenti.getIcon());

        // Insert the new row, returning the primary key value of the new row
        helper.getWritableDatabase().insert(Contract.TabStrumenti.TABLE_NAME, null, values);

    }


    public ArrayList<TabStrumenti> getTabStrumentiByDescStrumento(String descStrumento) {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Contract.TabStrumenti._ID,
                Contract.TabStrumenti.COLUMN_NAME_DESC_INSTRUMENT,
                Contract.TabStrumenti.COLUMN_NAME_ICON
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = Contract.TabStrumenti.COLUMN_NAME_DESC_INSTRUMENT + " = ?";
        String[] selectionArgs = {descStrumento};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Contract.TabStrumenti.COLUMN_NAME_DESC_INSTRUMENT;

        Cursor cursor = helper.getReadableDatabase().query(
                Contract.TabStrumenti.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return fromCursorToArrayList(cursor);
    }

    public void delete(TabStrumenti tabStrumenti) {

        // Define 'where' part of query.
        String selection = Contract.TabStrumenti._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {tabStrumenti.get_ID().toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabStrumenti.TABLE_NAME, selection, selectionArgs);


    }

    public void update(TabStrumenti tabStrumenti) {


        /*// New value for one column
        ContentValues values = new ContentValues();
        values.put(Contract.TabStrumenti., title);

        // Which row to update, based on the title
        String selection = FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "MyTitle" };

        int count = db.update(
                FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);*/
    }


    public void insertArrayTabStrumenti (TabStrumenti[] arrayTabStrumenti) {

        for (TabStrumenti tabStrumenti: arrayTabStrumenti) {
            insert(tabStrumenti);
        }

    }

    public ArrayList<TabStrumenti> getAllStrumenti() {
        String[] projection = {
                Contract.TabStrumenti._ID,
                Contract.TabStrumenti.COLUMN_NAME_DESC_INSTRUMENT,
                Contract.TabStrumenti.COLUMN_NAME_ICON
        };

        String sortOrder =
                Contract.TabStrumenti.COLUMN_NAME_DESC_INSTRUMENT;

        Cursor cursor = helper.getReadableDatabase().query(
                Contract.TabStrumenti.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

       return fromCursorToArrayList(cursor);

    }


    private ArrayList<TabStrumenti> fromCursorToArrayList (Cursor cursor) {
        ArrayList<TabStrumenti> listToReturn = new ArrayList<>();
        TabStrumenti tabStrumenti;
        while (cursor.moveToNext()) {
            tabStrumenti = new TabStrumenti();
            tabStrumenti.set_ID(cursor.getLong(cursor.getColumnIndexOrThrow(Contract.TabStrumenti._ID)));
            tabStrumenti.setDescInstrument(cursor.getString(cursor.getColumnIndexOrThrow(Contract.TabStrumenti.COLUMN_NAME_DESC_INSTRUMENT)));
            tabStrumenti.setIcon(cursor.getBlob(cursor.getColumnIndexOrThrow(Contract.TabStrumenti.COLUMN_NAME_ICON)));
            listToReturn.add(tabStrumenti);
        }
        cursor.close();
        return listToReturn;
    }
}
