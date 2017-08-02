package com.example.danieletavernelli.onstage.interfaces.implementation;

import com.example.danieletavernelli.onstage.interfaces.AdapterModel;
import com.example.danieletavernelli.onstage.interfaces.ItemTouchViewHolderInterface;

import java.util.List;



public class ItemTouchViewHolderInterfaceImpl implements ItemTouchViewHolderInterface {

    List<AdapterModel> list;

    public ItemTouchViewHolderInterfaceImpl(List<AdapterModel> list) {
        this.list=list;
    }

    /**
     * Quando si seleziona un oggetto della lista.....
     */
    @Override
    public void onItemSelected() {
    }

    /**
     * Quando l'oggetto è stato cancellato
     */
    @Override
    public void onItemClear() {
    }
}
