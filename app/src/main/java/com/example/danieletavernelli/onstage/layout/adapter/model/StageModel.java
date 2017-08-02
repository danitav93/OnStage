package com.example.danieletavernelli.onstage.layout.adapter.model;


import com.example.danieletavernelli.onstage.interfaces.AdapterModel;

/**
 * Questa classe è il modello della riga usata nell adapter degli stage
 */
public class StageModel implements AdapterModel {

    private String descName;

    public StageModel (String descName) {
        this.descName = descName;
    }

    public String getDescName() {
        return descName;
    }

    public void setDescName(String descName) {
        this.descName = descName;
    }
}
