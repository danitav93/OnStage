package com.example.danieletavernelli.onstage.database.service;

        import android.content.ContentValues;
        import android.database.Cursor;


        import com.example.danieletavernelli.onstage.database.Contract;
        import com.example.danieletavernelli.onstage.database.Helper;
        import com.example.danieletavernelli.onstage.database.entity.TabRelStageInstrumentEntity;
        import com.example.danieletavernelli.onstage.database.entity.TabStageEntity;

        import java.util.ArrayList;



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







    public ArrayList<TabRelStageInstrumentEntity> getListByStage(TabStageEntity tabStageEntity) {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = Contract.TabRelStageInstrumentProjection;

        // Filter results WHERE
        String selection = Contract.TabRelStageInstrument.COLUMN_NAME_STAGE_ID + " = ?";
        String[] selectionArgs = {tabStageEntity.get_ID().toString()};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =Contract.TabStage._ID;


        Cursor cursor = helper.getReadableDatabase().query(
                Contract.TabRelStageInstrument.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                     // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        return fromCursorToArrayList(cursor);



    }

    private ArrayList<TabRelStageInstrumentEntity> fromCursorToArrayList (Cursor cursor) {
        ArrayList<TabRelStageInstrumentEntity> listToReturn = new ArrayList<>();
        TabRelStageInstrumentEntity tabRelStageInstrumentEntity;
        while (cursor.moveToNext()) {
            tabRelStageInstrumentEntity = new TabRelStageInstrumentEntity();
            tabRelStageInstrumentEntity.set_ID(cursor.getLong(cursor.getColumnIndexOrThrow(Contract.TabInstrument._ID)));
            tabRelStageInstrumentEntity.setInstrumentId(cursor.getLong(cursor.getColumnIndexOrThrow(Contract.TabRelStageInstrument.COLUMN_NAME_INSTRUMENT_ID)));
            tabRelStageInstrumentEntity.setStageId(cursor.getLong(cursor.getColumnIndexOrThrow(Contract.TabRelStageInstrument.COLUMN_NAME_STAGE_ID)));
            tabRelStageInstrumentEntity.setCoordinateX(cursor.getFloat(cursor.getColumnIndexOrThrow(Contract.TabRelStageInstrument.COLUMN_NAME_COORDINATE_X)));
            tabRelStageInstrumentEntity.setCoordinateY(cursor.getFloat(cursor.getColumnIndexOrThrow(Contract.TabRelStageInstrument.COLUMN_NAME_COORDINATE_Y)));
            listToReturn.add(tabRelStageInstrumentEntity);
        }
        cursor.close();
        return listToReturn;
    }


    public void deleteByTabStage(TabStageEntity tabStageEntity) {
        // Define 'where' part of query.
        String selection = Contract.TabRelStageInstrument.COLUMN_NAME_STAGE_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {tabStageEntity.get_ID().toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabRelStageInstrument.TABLE_NAME, selection, selectionArgs);
    }

    public void deleteByIdStage(Long idStage) {
        // Define 'where' part of query.
        String selection = Contract.TabRelStageInstrument.COLUMN_NAME_STAGE_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {idStage.toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabRelStageInstrument.TABLE_NAME, selection, selectionArgs);
    }

    public void deleteByIdInstrument(Long instrumentId) {
        // Define 'where' part of query.
        String selection = Contract.TabRelStageInstrument.COLUMN_NAME_INSTRUMENT_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {instrumentId.toString()};

        // Issue SQL statement.
        helper.getWritableDatabase().delete(Contract.TabRelStageInstrument.TABLE_NAME, selection, selectionArgs);
    }
}
