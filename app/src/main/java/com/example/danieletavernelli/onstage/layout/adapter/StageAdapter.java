package com.example.danieletavernelli.onstage.layout.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.activity.Mixer.DesignStageActivity;
import com.example.danieletavernelli.onstage.database.entity.TabStageEntity;
import com.example.danieletavernelli.onstage.interfaces.AdapterModel;
import com.example.danieletavernelli.onstage.interfaces.ItemTouchViewHolderInterface;
import com.example.danieletavernelli.onstage.layout.adapter.model.StageModel;
import com.example.danieletavernelli.onstage.singleton.SelectedStageSingleton;
import com.example.danieletavernelli.onstage.utility.Methods;


import java.util.List;

/**
 * Questa classe implementa l'adapter per la recycle view in MixerActivity degli stage salvati localmente nel cellulare.
 */
public class StageAdapter extends RecyclerView.Adapter<StageAdapter.StageAdapterHolder>  {

    private Activity activity;

    private List<AdapterModel> stageList;

    private int itemHeight=0;
    
    class StageAdapterHolder extends RecyclerView.ViewHolder implements ItemTouchViewHolderInterface {

        private StageModel stageModel;
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

    public StageAdapter(Activity activity, List<AdapterModel> stageList) {
        this.activity = activity;
        this.stageList = stageList;
    }

    @Override
    public StageAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_stage_model,parent,false);
        final StageAdapterHolder stageAdapterHolder = new StageAdapterHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedStageSingleton.getInstance().setTabStageEntity(new TabStageEntity(stageAdapterHolder.stageModel.getId(),stageAdapterHolder.stageModel.getDescName()));
                SelectedStageSingleton.getInstance().setNewStage(false);
                Methods.startActivity(activity, DesignStageActivity.class);
            }
        });
        return stageAdapterHolder;
    }

    @Override
    public void onBindViewHolder(StageAdapterHolder holder, int position) {
       holder.descStageTextViw.setText(((StageModel)stageList.get(position)).getDescName());
        holder.stageModel = (StageModel)stageList.get(position);
    }

    @Override
    public int getItemCount() {
        return stageList.size();
    }

    public void setItemHeight(float itemHeight) {
        this.itemHeight=(int)itemHeight;
    }



}
