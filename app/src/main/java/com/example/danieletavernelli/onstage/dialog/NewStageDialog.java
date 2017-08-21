package com.example.danieletavernelli.onstage.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.activity.Mixer.DesignStageActivity;
import com.example.danieletavernelli.onstage.singleton.SelectedStageSingleton;

/**
 * Questa dialog viene chiamata quando si crea un nuovo stage
 */

public class NewStageDialog extends Dialog {


    private Context context;

    public NewStageDialog(Context context) {
        super(context);
        this.context=context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_new_stage);
        final EditText editText =(EditText) findViewById(R.id.dialog_new_stage_desc_stage);
        setTitle("New stage features");
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                SelectedStageSingleton.getInstance().getTabStageEntity().setDescStage(editText.getText().toString());
                ((DesignStageActivity)context).getDescStageTxtView().setText(editText.getText().toString());
            }
        });
    }


}
