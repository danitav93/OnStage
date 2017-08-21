package com.example.danieletavernelli.onstage.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;


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

    //ritorna un Bitmap from array di byte
    public static Bitmap fromByteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    //ritorna drawable from array di Byte
    public static Drawable fromByteArrayToDrawable(Context context,byte[] byteArray) {
        return new BitmapDrawable(context.getResources(),BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
    }

    //ritorna l'altezza dello schermo
    public static float getScreenHeight(Activity c) {
        Display display = c.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    //ritorna l'altezza dello schermo
    public static float getScreenWidth(Activity c) {
        Display display = c.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    //resize bitmap
    public static Bitmap scaleBitmap(Bitmap bitmap, int targetW) {
        int targetH;
        float percentage;
        percentage = (float) (targetW) / (float) bitmap.getWidth();
        targetH = (int)(Math.ceil(bitmap.getHeight() * percentage));
        return Bitmap.createScaledBitmap(bitmap, targetW, targetH, true);

    }



    //INTENT

    //comincia una nuova activity
    public static void startActivity (Activity actualActivity, Class newActivity) {
        Intent intent = new Intent(actualActivity,newActivity);
        actualActivity.startActivity(intent);
    }

    //comincia una nuova activity inserendo degli argomenti
    public static void startActivityWithExtra (Activity actualActivity, Class newActivity, String extraTag, Serializable extra) {
        Intent intent = new Intent(actualActivity,newActivity);
        intent.putExtra(extraTag,extra);
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
