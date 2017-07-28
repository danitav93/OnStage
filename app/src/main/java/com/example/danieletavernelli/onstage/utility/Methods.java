package com.example.danieletavernelli.onstage.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**

 Classe di metodi utilità

 */

public class Methods {

    //LAYOUT

    //ritorna i dp dati i pixel
    public static float fromPixelToDp(Context c, float pixel) {
        float density = c.getResources().getDisplayMetrics().density;
        return pixel / density;
    }

    //ritorna un array di byte data un drawable
    public static byte[] fromDrawableToByteArray(Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }




    //INTENT

    //comincia una nuova activity terminando l'auutale
    public static void startActivity (Activity actualActivity, Class newActivity) {
        Intent intent = new Intent(actualActivity,newActivity);
        actualActivity.startActivity(intent);
    }

    //comincia una nuova activity terminando l'auutale
    public static void startActivityFinishActual (Activity actualActivity, Class newActivity) {
        Intent intent = new Intent(actualActivity,newActivity);
        actualActivity.startActivity(intent);
        actualActivity.finish();
    }


    //TOAST
    public static void showShortToast(Context context,String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
