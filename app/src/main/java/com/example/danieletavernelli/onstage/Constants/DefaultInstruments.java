package com.example.danieletavernelli.onstage.Constants;


import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;

import com.example.danieletavernelli.onstage.R;
import com.example.danieletavernelli.onstage.database.entity.TabStrumenti;

import static com.example.danieletavernelli.onstage.utility.Methods.fromDrawableToByteArray;

public class DefaultInstruments {



    private  TabStrumenti[] DEFAULT_INSTRUMENTS;

    public DefaultInstruments(Context context)  {

        DEFAULT_INSTRUMENTS = new TabStrumenti[] {
                new TabStrumenti(1L,context.getResources().getString(R.string.acoustic_guitar),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.acoustic_guitar, null))),
                new TabStrumenti(2L,context.getResources().getString(R.string.electric_guitar),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.electric_guitar, null))),
                new TabStrumenti(3L,context.getResources().getString(R.string.bass),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bass, null))),
                new TabStrumenti(4L,context.getResources().getString(R.string.drum),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.drum, null))),
                new TabStrumenti(5L,context.getResources().getString(R.string.voice),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.voice, null))),
                new TabStrumenti(6L,context.getResources().getString(R.string.keyboard),fromDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.keyboard, null))),
        };
    }

    public TabStrumenti[] getDEFAULT_INSTRUMENTS() {
        return DEFAULT_INSTRUMENTS;
    }
}
