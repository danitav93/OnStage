package com.example.danieletavernelli.onstage.layout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.interfaces.AdapterModel;
import com.example.danieletavernelli.onstage.interfaces.ItemTouchViewHolderInterface;
import com.example.danieletavernelli.onstage.layout.adapter.model.StageModel;


import java.util.List;

/**
 * Questa classe implementa l'adapter per la recycle view in MixerActivity degli stage salvati localmente nel cellulare.
 */
public class StageAdapter extends RecyclerView.Adapter<StageAdapter.StageAdapterHolder>  {

    private List<AdapterModel> stageList;

    private int itemHeight=0;
    
    class StageAdapterHolder extends RecyclerView.ViewHolder implements ItemTouchViewHolderInterface {

        private TextView descStageTextViw;

        StageAdapterHolder(View v) {
            super(v);
            descStageTextViw =(TextView) v.findViewById(R.id.row_stage_model_descNome);
            LinearLayout rowLinearLayout =(LinearLayout) v.findViewById(R.id.row_stage_model_linear_layout);
            if (itemHeight!=0) {
                rowLinearLayout.getLayoutParams().height = itemHeight;
            }
        }

        @Override
        public void onItemSelected() {

        }

        @Override
        public void onItemClear() {

        }
    }

    public StageAdapter(List<AdapterModel> stageList) {
        this.stageList = stageList;
    }

    @Override
    public StageAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_stage_model,parent,false);
        return new StageAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StageAdapterHolder holder, int position) {
       holder.descStageTextViw.setText(((StageModel)stageList.get(position)).getDescName());
    }

    @Override
    public int getItemCount() {
        return stageList.size();
    }

    public void setItemHeight(float itemHeight) {
        this.itemHeight=(int)itemHeight;
    }



}
