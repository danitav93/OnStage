package com.example.danieletavernelli.onstage.layout.listener;


import android.view.DragEvent;
import android.view.View;


/**
 * Listener per le operazioni di drag di uno strumento
 */

public class InstrumentDragEventListener implements View.OnDragListener {


    @Override
    public boolean onDrag(View v, DragEvent event) {

            // Defines a variable to store the action type for the incoming event
            final int action = event.getAction();

            // Handles each of the expected events
            switch (action) {

                case DragEvent.ACTION_DRAG_STARTED:

                    break;

                case DragEvent.ACTION_DRAG_ENTERED:

                    break;

                case DragEvent.ACTION_DRAG_LOCATION:
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    break;

                case DragEvent.ACTION_DROP:

                    View view = (View) event.getLocalState();
                    view.setX(event.getX()-view.getHeight()/2);
                    view.setY(event.getY()-view.getHeight()/2);
                    view.invalidate();
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    break;

                // An unknown action type was received.
                default:
                    break;
            }

        return true;
    }

}

