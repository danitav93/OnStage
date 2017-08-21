package com.example.danieletavernelli.onstage.database.entity;


public class TabStageEntity {

    private Long _ID;
    private String descStage;

    public TabStageEntity() {}

    public TabStageEntity(long id,String descStage) {
        this._ID=id;
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
