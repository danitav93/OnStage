package com.example.danieletavernelli.onstage.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.activity.Mixer.DesignStageActivity;
import com.example.danieletavernelli.onstage.database.entity.TabInstrumentEntity;
import com.example.danieletavernelli.onstage.singleton.SelectedStageSingleton;

/**
 *Questa dialog esce quando viene premuto uno strumento sul palco. Si possono aggiungere varie funzionalità che riguardano la gestione dello strumento
 */

public class InstrumentOnStageDialog extends Dialog {

    private View imageInstrument;
    private Context context;


    public InstrumentOnStageDialog(Context context,View imageInstrument) {
        super(context);
        this.context=context;
        this.imageInstrument = imageInstrument;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_instrument_on_stage);
        setTitle(((TabInstrumentEntity)imageInstrument.getTag()).getDescInstrument());
        Button removeInstrumentFromStage = (Button) findViewById(R.id.dialog_instrument_on_stage_remove_instrument_button);
        removeInstrumentFromStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DesignStageActivity)context).setSavedSinceLastModifica(false);
                ((ViewGroup)imageInstrument.getParent()).removeView(imageInstrument);
                SelectedStageSingleton.getInstance().removeInstrumentStage((TabInstrumentEntity)imageInstrument.getTag());
                dismiss();
            }
        });
    }
}
