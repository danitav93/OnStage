package com.example.danieletavernelli.onstage.activity;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.layout.anim.animator.BounceAnimator;
import com.example.danieletavernelli.onstage.layout.anim.animator.LinearAnimator;
import com.example.danieletavernelli.onstage.layout.anim.animator.StretchAndCenterAnimator;
import com.example.danieletavernelli.onstage.utility.Methods;

// Main activity: possibilità di scelta tra la modalità "onStage" e la modalità "mixer"


public class MainActivity extends AppCompatActivity {

    //Context
    Context context = this;
    Activity actvity = this;

    //Animators
    BounceAnimator objectAnimatorStage;
    LinearAnimator objectAnimatorMixer;

    //Constants
    private final int WIDTH = 0;
    private final int HEIGHT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareLayout();

    }

    //questo metodo setta i listener per le animazioni.
    private void prepareLayout() {

        //immagine onStage
        final ImageView onStageImageView = (ImageView) findViewById(R.id.main_activity_on_stage);

        //animazione onStage di entrata
        onStageImageView.post(new Runnable() {
            @Override
            public void run() {
                objectAnimatorStage = new BounceAnimator(onStageImageView, "Y", -Methods.pixelTodp(context, onStageImageView.getHeight()), Methods.pixelTodp(context, getLayoutParams()[HEIGHT] / 2 + onStageImageView.getHeight() / 2), 1000);
                objectAnimatorStage.start();
            }
        });


        //Immagine mixer
        final ImageView mixerImageView = (ImageView) findViewById(R.id.main_activity_mixer);

        //animazione mixer di entrata
        mixerImageView.post(new Runnable() {
            @Override
            public void run() {
                objectAnimatorMixer = new LinearAnimator(mixerImageView, "Y", Methods.pixelTodp(context, getLayoutParams()[HEIGHT] + mixerImageView.getHeight()), Methods.pixelTodp(context, getLayoutParams()[HEIGHT] / 2 + mixerImageView.getHeight() / 2), 1000);
                objectAnimatorMixer.start();
            }
        });

        //animazione onStage quando selezione la modalità "onStage"
        onStageImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (objectAnimatorStage.isAnimationFinished()) {
                    new StretchAndCenterAnimator(actvity,OnStageActivity.class,onStageImageView, getLayoutParams()[WIDTH] / 2 - onStageImageView.getLeft() - onStageImageView.getWidth() / 2, onStageImageView.getTranslationY(), 1500).start();
                    ObjectAnimator.ofFloat(mixerImageView, "alpha", 0).setDuration(500).start();
                }

            }
        });

        //quando seleziono la modalità "mixer"
        mixerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (objectAnimatorMixer.isAnimationFinished()) {
                    new StretchAndCenterAnimator(actvity,MixerActivity.class,mixerImageView, -getLayoutParams()[WIDTH] / 2 + onStageImageView.getLeft() + onStageImageView.getWidth() / 2, mixerImageView.getTranslationY(), 1500).start();
                    ObjectAnimator.ofFloat(onStageImageView, "alpha", 0).setDuration(500).start();
                }

            }
        });

    }


    private int[] getLayoutParams() {

        final RelativeLayout relativeLayoutFather = (RelativeLayout) findViewById(R.id.main_activity_relative_layout_father);
        return new int[]{relativeLayoutFather.getMeasuredWidth(), relativeLayoutFather.getMeasuredHeight()};

    }
}

