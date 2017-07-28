package com.example.danieletavernelli.onstage.database.entity;



public class TabRelStageInstrument {

    private Long _ID;
    private Long stageId;
    private Long instrumentId;
    private float coordinateX;
    private float coordinateY;

    public TabRelStageInstrument() {}

    public TabRelStageInstrument(Long stageId, Long instrumentId, Float x, Float y) {
        this.stageId = stageId;
        this.instrumentId = instrumentId;
        this.coordinateX = x;
        this.coordinateY = y;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }
}
