package com.example.danieletavernelli.onstage.Thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;


import com.example.danieletavernelli.onstage.Constants.ApplicationCostants;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabInstrumentEntity;
import com.example.danieletavernelli.onstage.database.entity.TabRelStageInstrumentEntity;

import com.example.danieletavernelli.onstage.database.service.TabRelStageInstrumentService;
import com.example.danieletavernelli.onstage.database.service.TabInstrumentService;

import com.example.danieletavernelli.onstage.dialog.InstrumentOnStageDialog;
import com.example.danieletavernelli.onstage.layout.listener.OnLongClickForDragAndDropListener;
import com.example.danieletavernelli.onstage.singleton.SelectedStageSingleton;

import com.example.danieletavernelli.onstage.utility.Methods;

import java.util.ArrayList;

import static com.example.danieletavernelli.onstage.utility.Methods.fromByteArrayToBitmap;
import static com.example.danieletavernelli.onstage.utility.Methods.showShortToast;

/**
 * Questo async task si va a prendere gli strumenti di uno stage
 * e li mette nel layout nelle loro posizioni precedentemente salvate
 */

public class DrawStageAsyncTask extends AsyncTask<Void,Integer,Boolean> {

    private ProgressDialog progressDialog;
    private Helper helper;
    private Context context;
    private ArrayList<TabInstrumentEntity> arrayStrumentiOnStage = new ArrayList<>();
    private ArrayList<Bitmap> arrayImageBitmap = new ArrayList<>();


    public DrawStageAsyncTask(Context context) {
        this.context = context;
        this.helper = new Helper(context);
        this.progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            TabRelStageInstrumentService tabRelStageInstrumentService = new TabRelStageInstrumentService(helper);
            TabInstrumentService tabInstrumentService = new TabInstrumentService(helper);
            SelectedStageSingleton.getInstance().setArrayRelStageInstrumentEntity(tabRelStageInstrumentService.getListByStage(SelectedStageSingleton.getInstance().getTabStageEntity()));
            for (TabRelStageInstrumentEntity tabRelStageInstrumentEntity: SelectedStageSingleton.getInstance().getArrayRelStageInstrumentEntity()) {
                arrayStrumentiOnStage.add(tabInstrumentService.getTabStrumentiByIdStrumento(tabRelStageInstrumentEntity.getInstrumentId()));
            }
            progressDialog.setMax(arrayStrumentiOnStage.size());
            for (int i=0;i<arrayStrumentiOnStage.size();i++) {
                Bitmap image = fromByteArrayToBitmap(arrayStrumentiOnStage.get(i).getIcon());
                arrayImageBitmap.add(image);
                publishProgress(i);
            }
            SelectedStageSingleton.getInstance().setArrayStrumentiOnStage(arrayStrumentiOnStage);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        final ImageView imageViewInstrument = new ImageView(context);
        Bitmap imageIconResized = Methods.scaleBitmap( arrayImageBitmap.get(progress[0]), ApplicationCostants.instrumentIconsSide);
        imageViewInstrument.setImageBitmap(imageIconResized);
        imageViewInstrument.setTag(arrayStrumentiOnStage.get(progress[0]));
        imageViewInstrument.setX(SelectedStageSingleton.getInstance().getArrayRelStageInstrumentEntity().get(progress[0]).getCoordinateX());
        imageViewInstrument.setY(SelectedStageSingleton.getInstance().getArrayRelStageInstrumentEntity().get(progress[0]).getCoordinateY());
        imageViewInstrument.setOnLongClickListener(new OnLongClickForDragAndDropListener());
        imageViewInstrument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InstrumentOnStageDialog(context,imageViewInstrument).show();
            }
        });
        SelectedStageSingleton.getInstance().getDragAndDropLayout().addView(imageViewInstrument);
        progressDialog.setProgress(progress[0]+1);
    }

    @Override
    protected void onPostExecute (Boolean result) {
        helper.close();
        progressDialog.dismiss();
        if (!result) {
            showShortToast(context,"Errore nel caricamento degli strumenti di "+SelectedStageSingleton.getInstance().getTabStageEntity().getDescStage());
        }
    }
}
