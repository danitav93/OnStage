package com.example.danieletavernelli.onstage.layout.adapter;


import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.danieletavernelli.onstage.Constants.ApplicationCostants;
import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.activity.Mixer.DesignStageActivity;
import com.example.danieletavernelli.onstage.database.entity.TabInstrumentEntity;

import com.example.danieletavernelli.onstage.dialog.InstrumentOnStageDialog;
import com.example.danieletavernelli.onstage.interfaces.AdapterModel;
import com.example.danieletavernelli.onstage.interfaces.ItemTouchViewHolderInterface;
import com.example.danieletavernelli.onstage.layout.adapter.model.InstrumentModel;
import com.example.danieletavernelli.onstage.layout.listener.OnLongClickForDragAndDropListener;
import com.example.danieletavernelli.onstage.singleton.SelectedStageSingleton;




import java.util.List;


import static com.example.danieletavernelli.onstage.utility.Methods.showShortToast;

/**
 * Questa classe implementa l'adapter per la recycle view nella dialog della lista degli strumenti da aggiungere al cellulare.
 */
public class InstrumentAdapter extends RecyclerView.Adapter<InstrumentAdapter.InstrumentAdapterHolder>  {

    //Context
    private Context context;

    //Data
    private List<AdapterModel> instrumentList;








    class InstrumentAdapterHolder extends RecyclerView.ViewHolder implements ItemTouchViewHolderInterface {

        private TextView descInstrumentTextViw;
        private ImageView iconInstrumentImgViw;
        private LinearLayout item;



        InstrumentAdapterHolder(View v) {
            super(v);
            descInstrumentTextViw =(TextView) v.findViewById(R.id.row_instrument_model_desc_instrument);
            iconInstrumentImgViw = (ImageView) v.findViewById(R.id.row_instrument_model_instrument_icon);
            item = (LinearLayout) v.findViewById(R.id.row_instrument_model_linear_layout);
            item.getLayoutParams().height = ApplicationCostants.instrumentIconsSide;
            iconInstrumentImgViw.getLayoutParams().height = ApplicationCostants.instrumentIconsSide;
            iconInstrumentImgViw.getLayoutParams().width = ApplicationCostants.instrumentIconsSide;
        }

        //nota bene non è quando l'item viene selezionato
        @Override
        public void onItemSelected() {
        }

        @Override
        public void onItemClear() {

        }
    }

    public InstrumentAdapter(Context context, List<AdapterModel> instrumentList) {
        this.context = context;
        this.instrumentList = instrumentList;
    }

    @Override
    public InstrumentAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_instrument_model,parent,false);
        final InstrumentAdapterHolder instrumentAdapterHolder = new InstrumentAdapterHolder(itemView);
        instrumentAdapterHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se non esiste già lo strumento
                if (!SelectedStageSingleton.getInstance().containsInstrument((TabInstrumentEntity)instrumentAdapterHolder.iconInstrumentImgViw.getTag())) {
                    ((DesignStageActivity)context).setSavedSinceLastModifica(false);
                    final ImageView imageViewInstrument = new ImageView(context);
                    imageViewInstrument.setImageDrawable(instrumentAdapterHolder.iconInstrumentImgViw.getDrawable());
                    imageViewInstrument.setTag(instrumentAdapterHolder.iconInstrumentImgViw.getTag());
                    imageViewInstrument.setOnLongClickListener(new OnLongClickForDragAndDropListener());
                    imageViewInstrument.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new InstrumentOnStageDialog(context,imageViewInstrument).show();
                        }
                    });
                    SelectedStageSingleton.getInstance().getDragAndDropLayout().addView(imageViewInstrument);
                    SelectedStageSingleton.getInstance().addRelInstrumentStage((TabInstrumentEntity)instrumentAdapterHolder.iconInstrumentImgViw.getTag());
                    showShortToast(context,instrumentAdapterHolder.descInstrumentTextViw.getText().toString()+" is added on the stage");

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
        holder.iconInstrumentImgViw.setImageBitmap(((InstrumentModel)instrumentList.get(position)).getInstrumentIcon());
        holder.iconInstrumentImgViw.setTag(SelectedStageSingleton.getInstance().getAddInstrumentOnStageDialog().getListStrumentiEntity().get(position));
    }

    @Override
    public int getItemCount() {
        return instrumentList.size();
    }


}
