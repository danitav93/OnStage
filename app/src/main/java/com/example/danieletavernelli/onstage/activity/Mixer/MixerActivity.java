package com.example.danieletavernelli.onstage.activity.Mixer;



import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;


import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.database.Helper;
import com.example.danieletavernelli.onstage.database.entity.TabStageEntity;
import com.example.danieletavernelli.onstage.database.service.TabStageService;
import com.example.danieletavernelli.onstage.interfaces.AdapterModel;
import com.example.danieletavernelli.onstage.interfaces.implementation.StageItemTouchAdapterInterfaceImpl;
import com.example.danieletavernelli.onstage.layout.adapter.StageAdapter;
import com.example.danieletavernelli.onstage.layout.adapter.model.StageModel;
import com.example.danieletavernelli.onstage.layout.callback.ItemTouchHelperCallback;
import com.example.danieletavernelli.onstage.utility.Methods;


import java.util.ArrayList;
import java.util.List;

import static com.example.danieletavernelli.onstage.utility.Methods.getScreenHeight;
import static com.example.danieletavernelli.onstage.utility.Methods.getScreenWidth;
import static com.example.danieletavernelli.onstage.utility.Methods.showShortToast;
import static java.lang.Math.min;


//Activity che gestisce la modalità "mixer"

public class MixerActivity extends AppCompatActivity {

    //Context
    Activity activity = this;

    //Layout
    private RecyclerView stageRecyclerView;
    private ImageButton addConcertButton;

    //Adapter
    private StageAdapter stageAdapter;

    //Data
    private List<AdapterModel> stageList = new ArrayList<>();


    //Database
    Helper helper = new Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixer);


        prepareRecycleView();

        prepareLayout();

        prepareListeners();

    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareStageData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    private void  prepareRecycleView() {
        stageRecyclerView = (RecyclerView) findViewById(R.id.activity_mixer_recycler_view);

        stageAdapter = new StageAdapter(this,stageList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        stageRecyclerView.setLayoutManager(layoutManager);
        stageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        stageRecyclerView.setAdapter(stageAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(new StageItemTouchAdapterInterfaceImpl(stageList,stageAdapter));
        new ItemTouchHelper(callback).attachToRecyclerView(stageRecyclerView);
    }

    private void prepareStageData() {
        try {
            stageList.clear();
            for (TabStageEntity tabStageEntity : new TabStageService(helper).getStageList())  {
                stageList.add(new StageModel(tabStageEntity.get_ID(),tabStageEntity.getDescStage()));
            }
            stageAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
            showShortToast(getApplicationContext(),"Errore nel caricamento degli stage dal database.");
        }
    }

    private void prepareLayout() {
        stageRecyclerView.getLayoutParams().height= (int)getScreenHeight(this)/2;
        stageAdapter.setItemHeight(getScreenHeight(this)/2.2f);
        addConcertButton = (ImageButton) findViewById(R.id.activity_mixer_add_concert_button);
        int sizeAddButton = min((int) getScreenHeight(this)/6,(int) getScreenWidth(this)/6);
        addConcertButton.getLayoutParams().height= sizeAddButton;
        addConcertButton.getLayoutParams().width= sizeAddButton;

    }

    private void prepareListeners()  {
        addConcertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(addConcertButton, "rotation", 3600).setDuration(1000);
                objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                objectAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        setAllButtonDisabled();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Methods.startActivity(activity,DesignStageActivity.class);
                        addConcertButton.setRotation(0);
                        enableAllButton();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                objectAnimator.start();

            }
        });
    }

    private void setAllButtonDisabled() {
        addConcertButton.setClickable(false);
    }

    private void enableAllButton() {
        addConcertButton.setClickable(true);
    }
}
