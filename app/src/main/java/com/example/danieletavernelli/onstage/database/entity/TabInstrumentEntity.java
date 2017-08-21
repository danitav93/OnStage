package com.example.danieletavernelli.onstage.database.entity;


public class TabInstrumentEntity {

    private Long _ID;
    private String descInstrument;
    private byte[] icon;

    public TabInstrumentEntity() {
    }

    public TabInstrumentEntity(Long _ID, String descInstrument, byte[] icon) {
        this._ID = _ID;
        this.descInstrument = descInstrument;
        this.icon = icon;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getDescInstrument() {
        return descInstrument;
    }

    public void setDescInstrument(String descInstrument) {
        this.descInstrument = descInstrument;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }
}
