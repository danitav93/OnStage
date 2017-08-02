package com.example.danieletavernelli.onstage.Thread;


import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;


import com.example.danieletavernelli.onstage.Constants.ApplicationCostants;
import com.example.danieletavernelli.onstage.Constants.DefaultInstruments;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabRelStageInstrumentEntity;
import com.example.danieletavernelli.onstage.database.entity.TabStageEntity;
import com.example.danieletavernelli.onstage.database.service.TabRelStageInstrumentService;
import com.example.danieletavernelli.onstage.database.service.TabStageService;
import com.example.danieletavernelli.onstage.database.service.TabStrumentiService;

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
            new TabStageService(helper).insert(new TabStageEntity("gino_1"));
            new TabStageService(helper).insert(new TabStageEntity("gino_2"));
            new TabStageService(helper).insert(new TabStageEntity("gino_3"));
            new TabStageService(helper).insert(new TabStageEntity("gino_4"));
            new TabStageService(helper).insert(new TabStageEntity("gino_5"));
            new TabStageService(helper).insert(new TabStageEntity("gino_6"));
            new TabStageService(helper).insert(new TabStageEntity("gino_7"));
            new TabStageService(helper).insert(new TabStageEntity("gino_8"));
            new TabStageService(helper).insert(new TabStageEntity("gino_9"));
            new TabStageService(helper).insert(new TabStageEntity("gino_10"));
            new TabStageService(helper).insert(new TabStageEntity("gino_11"));
            new TabStageService(helper).insert(new TabStageEntity("gino_12"));
            new TabStageService(helper).insert(new TabStageEntity("gino_13"));
            new TabStrumentiService(helper).insertArrayTabStrumenti(new DefaultInstruments(context).getDEFAULT_INSTRUMENTS());
            new TabRelStageInstrumentService(helper).insert(new TabRelStageInstrumentEntity(1L,1L,0f,0f));

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
