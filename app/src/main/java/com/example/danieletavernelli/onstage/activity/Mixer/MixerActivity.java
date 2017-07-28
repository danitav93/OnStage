package com.example.danieletavernelli.onstage.activity.Mixer;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.utility.Methods;

//Activity che gestisce la modalità "mixer"

public class MixerActivity extends AppCompatActivity {

    //Context
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixer);

        Button button = (Button) findViewById(R.id.mixer_activity_create_stage_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Methods.startActivityFinishActual(activity ,DesignStageActivity.class);
            }
        });

    }
}
