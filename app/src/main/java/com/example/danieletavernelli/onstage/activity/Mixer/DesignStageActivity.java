package com.example.danieletavernelli.onstage.activity.Mixer;




import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.dialog.AddInstrumentOnStageDialog;
import com.example.danieletavernelli.onstage.layout.listener.InstrumentDragEventListener;

public class DesignStageActivity extends AppCompatActivity {



    //Layout
    private RelativeLayout dragAndDropLayout;

    //Dialog
    private AddInstrumentOnStageDialog addInstrumentOnStageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_stage);

        prepareLayout();

        prepareDialog();

        addListeners();
    }

    private void addListeners() {
        final Button addInstrumentButton = (Button) findViewById(R.id.activity_design_stage_add_instrument_button);
        addInstrumentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInstrumentOnStageDialog.show(getSupportFragmentManager(),"fragment_check_out_in_ritardo");
            }
        });

    }

    private void prepareLayout() {
        dragAndDropLayout = (RelativeLayout) findViewById(R.id.activity_design_stage_drag_stage_layout);
        dragAndDropLayout.setOnDragListener(new InstrumentDragEventListener());
    }

    private void prepareDialog() {
        addInstrumentOnStageDialog = AddInstrumentOnStageDialog.newInstance(this,dragAndDropLayout);
    }
}
