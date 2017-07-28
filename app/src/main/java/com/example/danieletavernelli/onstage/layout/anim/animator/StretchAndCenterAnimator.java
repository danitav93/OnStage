package com.example.danieletavernelli.onstage.layout.anim.animator;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;

import com.example.danieletavernelli.onstage.utility.Methods;


public class StretchAndCenterAnimator  {

    private AnimatorSet animatorSet = new AnimatorSet();

    public StretchAndCenterAnimator(Object object,float centerX, float centerY ,float scaleX, float scaleY, int duration ) {

        animatorSet.play(ObjectAnimator.ofFloat(object,"translationX",centerX)).with(ObjectAnimator.ofFloat(object,"translationY",centerY)).with(ObjectAnimator.ofFloat(object,"scaleX",scaleX)).with(ObjectAnimator.ofFloat(object,"scaleY",scaleY));

        animatorSet.setDuration(duration);

    }


    public StretchAndCenterAnimator(final Activity oldActivity, final Class newActivity, Object object, float centerX, float centerY , float scaleX, float scaleY, int duration ) {

        animatorSet.play(ObjectAnimator.ofFloat(object,"translationX",centerX)).with(ObjectAnimator.ofFloat(object,"translationY",centerY)).with(ObjectAnimator.ofFloat(object,"scaleX",scaleX)).with(ObjectAnimator.ofFloat(object,"scaleY",scaleY));

        animatorSet.setDuration(duration);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Methods.startActivity(oldActivity,newActivity);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


    }


    public void start() {
        animatorSet.start();
    }
}
