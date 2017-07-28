package com.example.danieletavernelli.onstage.database.entity;


public class TabStage {

    private Long _ID;
    private String descStage;

    public TabStage() {};

    public TabStage (String descStage) {
        this.descStage=descStage;
    }

    public String getDescStage() {
        return descStage;
    }

    public void setDescStage(String descStage) {
        this.descStage = descStage;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }
}
