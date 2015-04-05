package com.da.MonsterCrusade.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by sancho on 05.04.15.
 */
public class BitmapTransformer {
    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();

        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;

        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map

        matrix.postScale(scaleWidth, scaleHeight);
        // matrix.postRotate(90);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }
}
