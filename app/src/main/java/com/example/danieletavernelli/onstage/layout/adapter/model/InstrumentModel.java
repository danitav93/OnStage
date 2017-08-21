package com.example.danieletavernelli.onstage.layout.adapter.model;



import android.graphics.Bitmap;


import com.example.danieletavernelli.onstage.interfaces.AdapterModel;

/**
 * Questa classe è il modello della riga usata nell adapter degli strumenti
 */
public class InstrumentModel implements AdapterModel{

    private Long id;

    private String descInstrument;

    private Bitmap instrumentIcon;


    public String getDescInstrument() {
        return descInstrument;
    }

    public void setDescInstrument(String descInstrument) {
        this.descInstrument = descInstrument;
    }

    public Bitmap getInstrumentIcon() {
        return instrumentIcon;
    }

    public void setInstrumentIcon(Bitmap instrumentIcon) {
        this.instrumentIcon = instrumentIcon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
