package com.example.danieletavernelli.onstage.interfaces;



/**
 * Created by andrea.ebano on 04/07/2017.
 * Interface to notify a {@link android.support.v7.widget.RecyclerView.Adapter}  of moving and dismissal event from a {@link
 * android.support.v7.widget.helper.ItemTouchHelper.Callback}.
 */
public interface ItemTouchAdapterInterface {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
