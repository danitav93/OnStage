package com.example.danieletavernelli.onstage.Thread;

import android.content.Context;
import android.os.AsyncTask;


import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabRelStageInstrumentEntity;
import com.example.danieletavernelli.onstage.database.service.TabRelStageInstrumentService;
import com.example.danieletavernelli.onstage.database.service.TabStageService;
import com.example.danieletavernelli.onstage.singleton.SelectedStageSingleton;


import static com.example.danieletavernelli.onstage.utility.Methods.showShortToast;

/**
 * Questo thread salva lo stage appena disegnato
 */

public class SaveStageAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private Helper helper;


    public SaveStageAsyncTask(Context context) {
        this.context = context;
        this.helper = new Helper(context);
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        try {
            TabStageService tabStageService = new TabStageService(helper);
            tabStageService.update(SelectedStageSingleton.getInstance().getTabStageEntity());
            TabRelStageInstrumentService tabRelStageInstrumentService = new TabRelStageInstrumentService(helper);
            tabRelStageInstrumentService.deleteByTabStage(SelectedStageSingleton.getInstance().getTabStageEntity());
            if (SelectedStageSingleton.getInstance().getArrayRelStageInstrumentEntity() != null) {
                for (TabRelStageInstrumentEntity tabRelStageInstrumentEntity : SelectedStageSingleton.getInstance().getArrayRelStageInstrumentEntity()) {
                    tabRelStageInstrumentService.insert(tabRelStageInstrumentEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        helper.close();
        if (!result) {
            showShortToast(context, "Errore nel salvataggio dello stage " + SelectedStageSingleton.getInstance().getTabStageEntity().getDescStage());

        }
    }
}
