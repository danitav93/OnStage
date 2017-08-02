package com.example.danieletavernelli.onstage.layout.listener;

/**
 * Questo listener viene assegnato alle immagini sullo stage
 */

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;


public class OnLongClickForDragAndDropListener implements View.OnLongClickListener {

    @Override
    public boolean onLongClick(View v) {
        View.DragShadowBuilder shadow = new InstrumentDragShadowBuilder(v);
        ClipData dragData = ClipData.newPlainText("","");
        v.startDrag(dragData,shadow,v,0);
        return false;
    }

    private static class InstrumentDragShadowBuilder extends View.DragShadowBuilder {

        // The drag shadow image, defined as a drawable thing
        private static View shadow;

        InstrumentDragShadowBuilder(View v) {
            super(v);
            shadow = v;
        }

        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {
            int width, height;

            // Sets the width of the shadow to half the width of the original View
            width = getView().getWidth() ;

            // Sets the height of the shadow to half the height of the original View
            height = getView().getHeight();

            // Sets the size parameter's width and height values. These get back to the system
            // through the size parameter.
            size.set(width, height);

            // Sets the touch point's position to be in the middle of the drag shadow
            touch.set(width / 2, height / 2);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {

            shadow.draw(canvas);
        }


    }
}
