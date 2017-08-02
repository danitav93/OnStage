package com.example.danieletavernelli.onstage.layout.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.interfaces.AdapterModel;
import com.example.danieletavernelli.onstage.interfaces.ItemTouchViewHolderInterface;
import com.example.danieletavernelli.onstage.layout.adapter.model.InstrumentModel;
import com.example.danieletavernelli.onstage.layout.listener.InstrumentDragEventListener;
import com.example.danieletavernelli.onstage.layout.listener.OnLongClickForDragAndDropListener;


import java.util.ArrayList;
import java.util.List;

import static com.example.danieletavernelli.onstage.utility.Methods.showShortToast;

/**
 * Questa classe implementa l'adapter per la recycle view nella dialog della lista degli strumenti da aggiungere al cellulare.
 */
public class InstrumentAdapter extends RecyclerView.Adapter<InstrumentAdapter.InstrumentAdapterHolder>  {

    //Context
    private Context context;

    //Gestione del flusso
    private ArrayList<String> arrayAddedInstrument;

    //Data
    private List<AdapterModel> instrumentList;

    //Layout
    private float itemHeight=0;
    private RelativeLayout dragAndDropLayout;






    class InstrumentAdapterHolder extends RecyclerView.ViewHolder implements ItemTouchViewHolderInterface {

        private TextView descInstrumentTextViw;
        private ImageView iconInstrumentImgViw;
        private LinearLayout item;


        InstrumentAdapterHolder(View v) {
            super(v);
            descInstrumentTextViw =(TextView) v.findViewById(R.id.row_instrument_model_desc_instrument);
            iconInstrumentImgViw = (ImageView) v.findViewById(R.id.row_instrument_model_instrument_icon);
            item = (LinearLayout) v.findViewById(R.id.row_instrument_model_linear_layout);
            item.getLayoutParams().height=(int)itemHeight;

        }

        //nota bene non è quando l'item viene selezionato
        @Override
        public void onItemSelected() {
        }

        @Override
        public void onItemClear() {

        }
    }

    public InstrumentAdapter(Context context, List<AdapterModel> instrumentList, RelativeLayout dragAndDropLayout) {
        this.context = context;
        this.instrumentList = instrumentList;
        this.dragAndDropLayout = dragAndDropLayout;
        this.arrayAddedInstrument = new ArrayList<>();
    }

    @Override
    public InstrumentAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_instrument_model,parent,false);
        final InstrumentAdapterHolder instrumentAdapterHolder = new InstrumentAdapterHolder(itemView);
        instrumentAdapterHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!arrayAddedInstrument.contains(instrumentAdapterHolder.descInstrumentTextViw.getText().toString())) {
                    ImageView iconInstrument = new ImageView(context);
                    iconInstrument.setImageDrawable(instrumentAdapterHolder.iconInstrumentImgViw.getDrawable());
                    iconInstrument.setTag(instrumentAdapterHolder.descInstrumentTextViw.getText().toString());
                    iconInstrument.setOnLongClickListener(new OnLongClickForDragAndDropListener());
                    dragAndDropLayout.addView(iconInstrument);
                    arrayAddedInstrument.add(instrumentAdapterHolder.descInstrumentTextViw.getText().toString());
                } else {
                    showShortToast(context,instrumentAdapterHolder.descInstrumentTextViw.getText().toString()+" is already on the stage");
                }

            }
        });
        return instrumentAdapterHolder;
    }

    @Override
    public void onBindViewHolder(InstrumentAdapterHolder holder, int position) {
        holder.descInstrumentTextViw.setText(((InstrumentModel)instrumentList.get(position)).getDescInstrument());
        Bitmap iconResized = Bitmap.createScaledBitmap(((InstrumentModel)instrumentList.get(position)).getInstrumentIcon(), (int)itemHeight,(int)itemHeight*6/7, true);
        holder.iconInstrumentImgViw.setImageBitmap(iconResized);


    }

    @Override
    public int getItemCount() {
        return instrumentList.size();
    }

    public void setItemHeight(float itemHeight) {
        this.itemHeight = itemHeight;
    }


}
