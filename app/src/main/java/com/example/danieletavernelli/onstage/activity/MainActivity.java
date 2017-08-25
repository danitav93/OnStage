package com.example.danieletavernelli.onstage.activity;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.danieletavernelli.onstage.Constants.ApplicationCostants;
import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.Thread.DatabaseInitializationAsyncTask;
import com.example.danieletavernelli.onstage.activity.Mixer.MixerActivity;
import com.example.danieletavernelli.onstage.activity.OnStage.OnStageActivity;
import com.example.danieletavernelli.onstage.layout.anim.animator.BounceAnimator;
import com.example.danieletavernelli.onstage.layout.anim.animator.LinearAnimator;
import com.example.danieletavernelli.onstage.layout.anim.animator.StretchAndCenterAnimator;
import com.example.danieletavernelli.onstage.utility.Methods;

import static com.example.danieletavernelli.onstage.utility.Methods.getScreenHeight;
import static com.example.danieletavernelli.onstage.utility.Methods.showShortToast;

// Main activity: possibilità di scelta tra la modalità "onStage" e la modalità "mixer"


public class MainActivity extends AppCompatActivity {

    //Context
    private Context context = this;
    private Activity actvity = this;

    //Animators
    private BounceAnimator objectAnimatorStage;
    private LinearAnimator objectAnimatorMixer;


    //Layout
    private ImageView onStageImageView;
    private ImageView mixerImageView;

    //Trnslations
    float onStageImageTranslation=0;
    float mixerImageViewTranslation=0;

    //Constants
    private final int WIDTH = 0;
    private final int HEIGHT = 1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();

        initConstants();

        prepareLayout();

    }

    private void initConstants() {
        ApplicationCostants.instrumentIconsSide = (int)getScreenHeight(this)/8;

    }

    private void initDatabase() {
        try {
            new DatabaseInitializationAsyncTask(context).execute();
        } catch (Exception e) {
            showShortToast(context,"Errore nel caricamento del database.");
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        //se l'immagine onStage ha alpha = 0 allora vuol dire che ho chiamato l'activity mixer, siccome
        //sono tornato indietro devo eseguire l'animazione all'indietro
        if (onStageImageView!=null && onStageImageView.getAlpha()==0) {
            new StretchAndCenterAnimator(mixerImageView, 0, mixerImageView.getTranslationY(),1f,1f ,800).start();
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(onStageImageView, "alpha", 100).setDuration(1000);
            objectAnimator.addListener(new AlphaOnResumeListener());
            objectAnimator.start();

        } else if (mixerImageView!=null && mixerImageView.getAlpha()==0) {
            new StretchAndCenterAnimator(onStageImageView, 0, onStageImageView.getTranslationY(),1f,1f, 800).start();
            ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(mixerImageView, "alpha", 100).setDuration(1000);
            objectAnimator.addListener(new AlphaOnResumeListener());
            objectAnimator.start();
        }
    }


    //questo metodo setta i listener per le animazioni.
    private void prepareLayout() {

        //immagine onStage
        onStageImageView = (ImageView) findViewById(R.id.main_activity_on_stage);

        //animazione onStage di entrata
        onStageImageView.post(new Runnable() {
            @Override
            public void run() {
                objectAnimatorStage = new BounceAnimator(onStageImageView, "Y", -Methods.fromPixelToDp(context, onStageImageView.getHeight()), Methods.fromPixelToDp(context, getLayoutParams()[HEIGHT] / 2 + onStageImageView.getHeight() / 2), 1000);
                objectAnimatorStage.start();
            }
        });


        //Immagine mixer
        mixerImageView = (ImageView) findViewById(R.id.main_activity_mixer);

        //animazione mixer di entrata
        mixerImageView.post(new Runnable() {
            @Override
            public void run() {
                objectAnimatorMixer = new LinearAnimator(mixerImageView, "Y", Methods.fromPixelToDp(context, getLayoutParams()[HEIGHT] + mixerImageView.getHeight()), Methods.fromPixelToDp(context, getLayoutParams()[HEIGHT] / 2 + mixerImageView.getHeight() / 2), 1000);
                objectAnimatorMixer.start();
            }
        });

        //animazione onStage quando selezione la modalità "onStage"
        onStageImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (objectAnimatorStage.isAnimationFinished()) {
                    onStageImageTranslation = getLayoutParams()[WIDTH] / 2 - onStageImageView.getLeft() - onStageImageView.getWidth() / 2;
                    new StretchAndCenterAnimator(actvity,OnStageActivity.class,onStageImageView, onStageImageTranslation , onStageImageView.getTranslationY(), 1.5f,1.5f,1500).start();
                    ObjectAnimator.ofFloat(mixerImageView, "alpha", 0).setDuration(500).start();
                    onStageImageView.setClickable(false);
                    mixerImageView.setClickable(false);
                }

            }
        });

        //quando seleziono la modalità "mixer"
        mixerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (objectAnimatorMixer.isAnimationFinished()) {
                    mixerImageViewTranslation = -getLayoutParams()[WIDTH] / 2 + onStageImageView.getLeft() + onStageImageView.getWidth() / 2;
                    new StretchAndCenterAnimator(actvity,MixerActivity.class,mixerImageView,mixerImageViewTranslation , mixerImageView.getTranslationY(),1.5f,1.5f, 1500).start();
                    ObjectAnimator.ofFloat(onStageImageView, "alpha", 0).setDuration(500).start();
                    mixerImageView.setClickable(false);
                    onStageImageView.setClickable(false);
                }

            }
        });

    }


    private int[] getLayoutParams() {

        final RelativeLayout relativeLayoutFather = (RelativeLayout) findViewById(R.id.main_activity_relative_layout_father);
        return new int[]{relativeLayoutFather.getMeasuredWidth(), relativeLayoutFather.getMeasuredHeight()};

    }

    private class AlphaOnResumeListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {
                mixerImageView.setClickable(true);
                onStageImageView.setClickable(true);
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    }
}

