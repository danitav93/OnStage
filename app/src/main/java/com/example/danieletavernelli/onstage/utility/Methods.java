package com.example.danieletavernelli.onstage.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**

 Classe di metodi utilità

 */

public class Methods {

    //ritorna i dp dati i pixel
    public static float pixelTodp(Context c, float pixel) {
        float density = c.getResources().getDisplayMetrics().density;
        return pixel / density;
    }

    //comincia una nuova activity terminando l'auutale
    public static void startActivity (Activity actualActivity, Class newActivity) {
        Intent intent = new Intent(actualActivity,newActivity);
        actualActivity.startActivity(intent);
    }
}
