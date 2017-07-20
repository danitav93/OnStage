package com.example.danieletavernelli.onstage.layout.anim.animator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.animation.BounceInterpolator;


public class BounceAnimator extends CustomAnimator {

    private ObjectAnimator objectAnimator;

    public BounceAnimator(Object object, String propertyToAnimate,float startValue, float finalValue, int duration ) {

        objectAnimator = ObjectAnimator.ofFloat(object,propertyToAnimate,startValue,finalValue);

        objectAnimator.setDuration(duration);

        objectAnimator.setInterpolator(new BounceInterpolator());

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animationFinished = true;
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
        objectAnimator.start();
    }


}
