package com.example.danieletavernelli.onstage.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabStrumenti;
import com.example.danieletavernelli.onstage.database.service.TabStrumentiService;
import com.example.danieletavernelli.onstage.interfaces.AdapterModel;
import com.example.danieletavernelli.onstage.interfaces.implementation.ItemTouchAdapterInterfaceImpl;
import com.example.danieletavernelli.onstage.layout.adapter.InstrumentAdapter;
import com.example.danieletavernelli.onstage.layout.adapter.model.InstrumentModel;
import com.example.danieletavernelli.onstage.layout.callback.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import static com.example.danieletavernelli.onstage.utility.Methods.fromByteArrayToBitmap;
import static com.example.danieletavernelli.onstage.utility.Methods.getScreenHeight;
import static com.example.danieletavernelli.onstage.utility.Methods.showShortToast;

/**
 * Questa classe implementa la dialog che appare quando si vuole aggiungere un strumento su uno stage
 */

public class AddInstrumentOnStageDialog extends DialogFragment {


    //Data
    private List<AdapterModel> listStrumenti = new ArrayList<>();


    //Adapter
    private InstrumentAdapter instrumentAdapter;

    //Database
    private Helper helper;

    //Layout
    private RelativeLayout dragAndDropLayoutLayout;


    public static AddInstrumentOnStageDialog newInstance(Context context, RelativeLayout dragAndDropLayoutLayout) {
        AddInstrumentOnStageDialog instrumentOnStageDialog = new AddInstrumentOnStageDialog();
        instrumentOnStageDialog.helper = new Helper(context);
        instrumentOnStageDialog.dragAndDropLayoutLayout = dragAndDropLayoutLayout;
        instrumentOnStageDialog.prepareListInstruments();
        return instrumentOnStageDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getDialog().getWindow() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        View v = inflater.inflate(R.layout.dialog_add_instrument_on_stage, container, false);


        prepareRecycleView(v);

        prepareLayout();

        return v;
    }


    private void prepareRecycleView(View v) {

        RecyclerView instrumentRecyclerView = (RecyclerView) v.findViewById(R.id.dialog_add_instrument_recycler_view);

        instrumentAdapter = new InstrumentAdapter(getContext(), listStrumenti, dragAndDropLayoutLayout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        instrumentRecyclerView.setLayoutManager(layoutManager);
        instrumentRecyclerView.setItemAnimator(new DefaultItemAnimator());
        instrumentRecyclerView.setAdapter(instrumentAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(new ItemTouchAdapterInterfaceImpl(listStrumenti, instrumentAdapter));
        new ItemTouchHelper(callback).attachToRecyclerView(instrumentRecyclerView);
    }

    private void prepareListInstruments() {
        try {
            InstrumentModel strumentoModel;
            for (TabStrumenti tabStrumenti : new TabStrumentiService(helper).getAllStrumenti()) {
                strumentoModel = new InstrumentModel();
                strumentoModel.setDescInstrument(tabStrumenti.getDescInstrument());
                strumentoModel.setInstrumentIcon(fromByteArrayToBitmap(tabStrumenti.getIcon()));
                listStrumenti.add(strumentoModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showShortToast(getContext(), "Errore nel caricamento degli strumenti dal database.");
        }
    }

    private void prepareLayout() {
        instrumentAdapter.setItemHeight(getScreenHeight(getActivity()) / 6f);
    }

}
