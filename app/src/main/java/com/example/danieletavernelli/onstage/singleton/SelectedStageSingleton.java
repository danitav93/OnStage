package com.example.danieletavernelli.onstage.singleton;

import android.widget.RelativeLayout;

import com.example.danieletavernelli.onstage.database.entity.TabInstrumentEntity;
import com.example.danieletavernelli.onstage.database.entity.TabRelStageInstrumentEntity;
import com.example.danieletavernelli.onstage.database.entity.TabStageEntity;
import com.example.danieletavernelli.onstage.dialog.AddInstrumentOnStageDialog;


import java.util.ArrayList;

/**
 * Singleton per lo stage selezionato
 */

public class SelectedStageSingleton {

    private static SelectedStageSingleton selectedStageSingleton;

    private TabStageEntity tabStageEntity;
    private ArrayList<TabRelStageInstrumentEntity> arrayRelStageInstrumentEntity;
    private ArrayList<TabInstrumentEntity> arrayStrumentiOnStage;
    private RelativeLayout dragAndDropLayout;
    private AddInstrumentOnStageDialog addInstrumentOnStageDialog;
    private boolean newStage=true;

    private SelectedStageSingleton () {
    }

    public void reset() {
        selectedStageSingleton=null;
        tabStageEntity = null;
        arrayRelStageInstrumentEntity = null;
        arrayStrumentiOnStage = null;
        dragAndDropLayout = null;
        addInstrumentOnStageDialog = null;
    }

    public static SelectedStageSingleton getInstance() {
       if (selectedStageSingleton == null) {
           selectedStageSingleton = new SelectedStageSingleton();
       }
        return selectedStageSingleton;
    }



    public TabStageEntity getTabStageEntity() {
        return tabStageEntity;
    }

    public void setTabStageEntity(TabStageEntity tabStageEntity) {
        this.tabStageEntity = tabStageEntity;
    }

    public ArrayList<TabRelStageInstrumentEntity> getArrayRelStageInstrumentEntity() {
        return arrayRelStageInstrumentEntity;
    }

    public void setArrayRelStageInstrumentEntity(ArrayList<TabRelStageInstrumentEntity> arrayRelStageInstrumentEntity) {
        this.arrayRelStageInstrumentEntity = arrayRelStageInstrumentEntity;
    }

    public RelativeLayout getDragAndDropLayout() {
        return dragAndDropLayout;
    }

    public void setDragAndDropLayout(RelativeLayout dragAndDropLayout) {
        this.dragAndDropLayout = dragAndDropLayout;
    }

    public void addRelInstrumentStage(TabInstrumentEntity tabInstrumentEntity) {
        if (arrayStrumentiOnStage==null) {
            arrayStrumentiOnStage = new ArrayList<>();
        }
        if (arrayRelStageInstrumentEntity==null) {
            arrayRelStageInstrumentEntity = new ArrayList<>();
        }
        arrayStrumentiOnStage.add(tabInstrumentEntity);
        arrayRelStageInstrumentEntity.add(new TabRelStageInstrumentEntity(tabStageEntity.get_ID(),tabInstrumentEntity.get_ID(),0f,0f));
    }


    public void setArrayStrumentiOnStage(ArrayList<TabInstrumentEntity> arrayStrumentiOnStage) {
        this.arrayStrumentiOnStage = arrayStrumentiOnStage;
    }

    public void removeInstrumentStage(TabInstrumentEntity tabInstrumentEntity) {
        TabRelStageInstrumentEntity tabRelToRemove=null;
        for (TabRelStageInstrumentEntity tabRelStageInstrumentEntity:arrayRelStageInstrumentEntity) {
            if (tabRelStageInstrumentEntity.getInstrumentId().equals(tabInstrumentEntity.get_ID())) {
                tabRelToRemove = tabRelStageInstrumentEntity;
                break;
            }
        }
        arrayRelStageInstrumentEntity.remove(tabRelToRemove);
        TabInstrumentEntity tabInstrumentToRemove=null;
        for (TabInstrumentEntity tabInstrumentEntity1: arrayStrumentiOnStage) {
            if (tabInstrumentEntity1.get_ID().equals(tabInstrumentEntity.get_ID())) {
                tabInstrumentToRemove = tabInstrumentEntity;
                break;
            }
        }
        arrayStrumentiOnStage.remove(tabInstrumentToRemove);
    }

    public void updateInstrumentCoordinate(TabInstrumentEntity instrument, float x, float y) {
        for (TabRelStageInstrumentEntity tabRelStageInstrumentEntity:arrayRelStageInstrumentEntity) {
            if (tabRelStageInstrumentEntity.getInstrumentId().equals(instrument.get_ID())) {
                tabRelStageInstrumentEntity.setCoordinateX(x);
                tabRelStageInstrumentEntity.setCoordinateY(y);
                return;
            }
        }
    }


    public boolean containsInstrument(TabInstrumentEntity tabInstrumentEntity) {
        if (arrayStrumentiOnStage==null) {
            return false;
        }
        for (TabInstrumentEntity tabInstrumentEntity1:arrayStrumentiOnStage) {
            if (tabInstrumentEntity1.get_ID().equals(tabInstrumentEntity.get_ID())) {
                return true;
            }
        }
        return false;
    }

    public AddInstrumentOnStageDialog getAddInstrumentOnStageDialog() {
        return addInstrumentOnStageDialog;
    }

    public void setAddInstrumentOnStageDialog(AddInstrumentOnStageDialog addInstrumentOnStageDialog) {
        this.addInstrumentOnStageDialog = addInstrumentOnStageDialog;
    }

    public void setNewStage(boolean newStage) {
        this.newStage = newStage;
    }

    public boolean isNewStage() {
        return newStage;
    }
}
