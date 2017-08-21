package com.example.danieletavernelli.onstage.Thread;


import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;


import com.example.danieletavernelli.onstage.Constants.ApplicationCostants;
import com.example.danieletavernelli.onstage.Constants.DefaultInstruments;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.service.TabInstrumentService;

import java.io.File;


public class DatabaseInitializationAsyncTask extends AsyncTask<Void,Void,Void> {


    private final Context context;

    public DatabaseInitializationAsyncTask (Context context) {
        this.context=context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Helper helper = new Helper(context);

        boolean databaseExist = checkDatabase();

        if (!databaseExist) {
            //inserisco la lista di strumenti predefinita
            new TabInstrumentService(helper).insertArrayTabStrumenti(new DefaultInstruments(context).getDEFAULT_INSTRUMENTS());

        }
        return null;

    }

    private boolean checkDatabase() {
        File DB;
        try {
            DB = context.getDatabasePath(ApplicationCostants.DATABASE_NAME);
            return DB.exists();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
            return false;
        }

    }
}
