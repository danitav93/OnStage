package com.example.danieletavernelli.onstage.Constants;


import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;

import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.database.entity.TabInstrumentEntity;

import static com.example.danieletavernelli.onstage.utility.Methods.fromDrawableToByteArray;

public class DefaultInstruments {



    private  TabInstrumentEntity[] DEFAULT_INSTRUMENTS;

    public DefaultInstruments(Context context)  {

        DEFAULT_INSTRUMENTS = new TabInstrumentEntity[] {
                new TabInstrumentEntity(1L,context.getResources().getString(R.string.acoustic_guitar),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.acoustic_guitar, null))),
                new TabInstrumentEntity(2L,context.getResources().getString(R.string.electric_guitar),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.electric_guitar, null))),
                new TabInstrumentEntity(3L,context.getResources().getString(R.string.bass),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bass, null))),
                new TabInstrumentEntity(4L,context.getResources().getString(R.string.drum),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.drum, null))),
                new TabInstrumentEntity(5L,context.getResources().getString(R.string.voice),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.voice, null))),
                new TabInstrumentEntity(6L,context.getResources().getString(R.string.keyboard),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.keyboard, null))),
                new TabInstrumentEntity(7L,context.getString(R.string.monitor1),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.monitor1, null))),
                new TabInstrumentEntity(8L,context.getString(R.string.monitor2),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.monitor1, null))),
                new TabInstrumentEntity(9L,context.getString(R.string.monitor3),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.monitor1, null))),
                new TabInstrumentEntity(10L,context.getString(R.string.monitor4),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.monitor1, null))),


        };
    }

    public TabInstrumentEntity[] getDEFAULT_INSTRUMENTS() {
        return DEFAULT_INSTRUMENTS;
    }
}
