package com.example.danieletavernelli.onstage.interfaces.implementation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.service.TabInstrumentService;
import com.example.danieletavernelli.onstage.database.service.TabRelStageInstrumentService;
import com.example.danieletavernelli.onstage.interfaces.AdapterModel;
import com.example.danieletavernelli.onstage.interfaces.ItemTouchAdapterInterface;
import com.example.danieletavernelli.onstage.layout.adapter.model.InstrumentModel;


import java.util.List;

import static com.example.danieletavernelli.onstage.utility.Methods.showShortToast;

/**
 * quando effetto uno swiper per cancellare uno stage di una lista o quando lo sposto
 */

public class InstrumentItemTouchAdapterInterfaceImpl implements ItemTouchAdapterInterface {
    private List<AdapterModel> list;
    private RecyclerView.Adapter recyclerView;
    private Helper helper;
    private Context context;

    public InstrumentItemTouchAdapterInterfaceImpl(List<AdapterModel> list, RecyclerView.Adapter recyclerView, Helper helper, Context context) {
        this.list=list;
        this.recyclerView = recyclerView;
        this.helper=helper;
        this.context=context;
    }

    @Override
    public void onItemDismiss(int position) {
        try {
            list.remove(position);
            recyclerView.notifyItemRemoved(position);
            if (helper == null) {
                helper = new Helper(context);
            }
            new TabRelStageInstrumentService(helper).deleteByIdInstrument(((InstrumentModel) list.get(position)).getId());
            new TabInstrumentService(helper).deleteByInstrumentId(((InstrumentModel) list.get(position)).getId());
        } catch (Exception e) {
            e.printStackTrace();
            showShortToast(context,"Errore nell'eliminazione dello strumento dal database.");
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        AdapterModel prev = list.remove(fromPosition);
        list.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        recyclerView.notifyItemMoved(fromPosition, toPosition);
    }
}
