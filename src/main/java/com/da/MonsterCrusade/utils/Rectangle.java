package com.da.MonsterCrusade.utils;

import android.graphics.Rect;

/**
 * Created by sancho on 05.04.15.
 */
public class Rectangle {
    public static Rect getRect(int x, int y, int imageSize){
        return new Rect(x - imageSize, y - imageSize, x + imageSize, y + imageSize);
    }
}
