package com.example.danieletavernelli.onstage.interfaces.implementation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.service.TabRelStageInstrumentService;
import com.example.danieletavernelli.onstage.database.service.TabStageService;
import com.example.danieletavernelli.onstage.interfaces.AdapterModel;
import com.example.danieletavernelli.onstage.interfaces.ItemTouchAdapterInterface;
import com.example.danieletavernelli.onstage.layout.adapter.model.StageModel;


import java.util.List;

import static com.example.danieletavernelli.onstage.utility.Methods.showShortToast;


/**
 * quando effetto uno swiper per cancellare uno stage di una lista o quando lo sposto
 */
public class StageItemTouchAdapterInterfaceImpl implements ItemTouchAdapterInterface {

    private List<AdapterModel> list;
    private RecyclerView.Adapter recyclerView;
    private Helper helper;
    private Context context;

    public StageItemTouchAdapterInterfaceImpl(List<AdapterModel> list, RecyclerView.Adapter recyclerView, Helper helper, Context context) {
        this.list=list;
        this.recyclerView = recyclerView;
        this.helper=helper;
        this.context=context;
    }

    @Override
    public void onItemDismiss(int position) {
        try {
            if (helper == null) {
                helper = new Helper(context);
            }
            new TabRelStageInstrumentService(helper).deleteByIdStage(((StageModel) list.get(position)).getId());
            new TabStageService(helper).deleteByIdStage(((StageModel) list.get(position)).getId());
            list.remove(position);
            recyclerView.notifyItemRemoved(position);
        } catch (Exception e) {
            e.printStackTrace();
            showShortToast(context,"Errore nell'eliminazione dello stage dal database.");
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        AdapterModel prev = list.remove(fromPosition);
        list.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        recyclerView.notifyItemMoved(fromPosition, toPosition);
    }
}
