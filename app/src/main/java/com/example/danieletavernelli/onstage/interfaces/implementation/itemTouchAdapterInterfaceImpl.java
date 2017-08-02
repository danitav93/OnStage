package com.example.danieletavernelli.onstage.interfaces.implementation;

import android.support.v7.widget.RecyclerView;

import com.example.danieletavernelli.onstage.interfaces.AdapterModel;
import com.example.danieletavernelli.onstage.interfaces.ItemTouchAdapterInterface;


import java.util.List;


/**
 * quando effetto uno swiper per cancellare un elemento di una lista o quando lo sposto
 */
public class ItemTouchAdapterInterfaceImpl implements ItemTouchAdapterInterface {

    private List<AdapterModel> list;
    private RecyclerView.Adapter recyclerView;

    public ItemTouchAdapterInterfaceImpl(List<AdapterModel> list,RecyclerView.Adapter recyclerView) {
        this.list=list;
        this.recyclerView = recyclerView;
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        recyclerView.notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        AdapterModel prev = list.remove(fromPosition);
        list.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        recyclerView.notifyItemMoved(fromPosition, toPosition);
    }
}
