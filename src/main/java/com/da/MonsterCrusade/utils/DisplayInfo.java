package com.da.MonsterCrusade.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by ihorkroosh on 4/5/15.
 */
public class DisplayInfo {
    public static Point getSize(Context context) {
        Activity activity = (Activity) context;
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size;
    }
}
