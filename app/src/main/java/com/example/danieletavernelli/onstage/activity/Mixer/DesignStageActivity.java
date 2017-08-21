package com.example.danieletavernelli.onstage.activity.Mixer;





import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.Thread.DrawStageAsyncTask;
import com.example.danieletavernelli.onstage.Thread.SaveStageAsyncTask;
import com.example.danieletavernelli.onstage.database.Helper;



import com.example.danieletavernelli.onstage.database.service.TabStageService;
import com.example.danieletavernelli.onstage.dialog.AddInstrumentOnStageDialog;
import com.example.danieletavernelli.onstage.dialog.NewStageDialog;
import com.example.danieletavernelli.onstage.layout.listener.InstrumentDragEventListener;
import com.example.danieletavernelli.onstage.singleton.SelectedStageSingleton;

import static com.example.danieletavernelli.onstage.utility.Methods.showShortToast;


public class DesignStageActivity extends AppCompatActivity {

    //Dialog
    private AddInstrumentOnStageDialog addInstrumentOnStageDialog;

    //Context
    private Context context=this;

    //Database
    private Helper helper;

    //Layout
    private TextView descStageTxtView;

    //utility
    private boolean savedButtonPressed=false; //bottone salva mai stato spinto
    private boolean savedSinceLastModifica=false; //bottone salvato dall'ultima modifica

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_stage);

        prepareDialog();

        prepareTabStage();

        prepareLayout();

        addListeners();

        prepareUtility();

    }

    private void prepareUtility() {
        //se ho un vecchio stage metto che è come se avessi già salvato tutto così se esco subito non esce la dialog
        if (!SelectedStageSingleton.getInstance().isNewStage()) {
            setSavedSinceLastModifica(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SelectedStageSingleton.getInstance().reset();
        if (helper!=null) {
            helper.close();
        }
    }

    @Override
    public void onBackPressed() {
        //se ho salvato tutte le modifiche esco senza problemi
        if (savedSinceLastModifica) {
            super.onBackPressed();
        } else {
            //altrimenti chiedo se si vuole uscire senza salvare
            new AlertDialog.Builder(this)
                    .setTitle("Attenzione!")
                    .setMessage("Non sono state salvate le ultime modifiche. Uscire senza salvare?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                //se lo stage è nuovo e esco senza salvare, devo eliminare la tab stage creata in precedenza
                                if (!savedButtonPressed && SelectedStageSingleton.getInstance().isNewStage()) {
                                    new TabStageService(helper).delete(SelectedStageSingleton.getInstance().getTabStageEntity());
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                showShortToast(getApplicationContext(), "Errore nella eliminazione dello stage inizializzato.");
                            }
                            savedSinceLastModifica=true;
                            onBackPressed();

                        }
                    })
                    .setNegativeButton(android.R.string.no,null)
                    .show();
        }
    }

    private void prepareTabStage() {
        if (SelectedStageSingleton.getInstance().isNewStage()) {
            helper = new Helper(this);
            SelectedStageSingleton.getInstance().setTabStageEntity(new TabStageService(helper).getNewTabStage());
            new NewStageDialog(this).show();
        }

    }

    private void addListeners() {
        final Button addInstrumentButton = (Button) findViewById(R.id.activity_design_stage_add_instrument_button);
        addInstrumentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInstrumentOnStageDialog.show(getSupportFragmentManager(),"fragment_check_out_in_ritardo");
            }
        });


        Button saveStageButton = (Button) findViewById(R.id.activity_design_stage_save_stage_button);
        saveStageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedButtonPressed=true;
                savedSinceLastModifica=true;
                new SaveStageAsyncTask(context).execute();
            }
        });

    }

    private void prepareLayout() {

        RelativeLayout dragAndDropLayout = (RelativeLayout) findViewById(R.id.activity_design_stage_drag_stage_layout);
        dragAndDropLayout.setOnDragListener(new InstrumentDragEventListener());
        SelectedStageSingleton.getInstance().setDragAndDropLayout(dragAndDropLayout);

        descStageTxtView =(TextView)findViewById(R.id.activity_design_stage_desc_stage);

        if (!SelectedStageSingleton.getInstance().isNewStage()) {
            new DrawStageAsyncTask(this).execute();
            descStageTxtView.setText(SelectedStageSingleton.getInstance().getTabStageEntity().getDescStage());
        }


    }

    private void prepareDialog() {
        addInstrumentOnStageDialog = AddInstrumentOnStageDialog.newInstance(this);
        SelectedStageSingleton.getInstance().setAddInstrumentOnStageDialog(addInstrumentOnStageDialog);
    }

    public TextView getDescStageTxtView() {
        return descStageTxtView;
    }

    public void setSavedSinceLastModifica(boolean savedSinceLastModifica) {
        this.savedSinceLastModifica = savedSinceLastModifica;
    }
}
