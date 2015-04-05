package com.da.MonsterCrusade.actors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import com.da.MonsterCrusade.bullets.Bullet;

/**
 * Created by ihorkroosh on 4/5/15.
 */
public interface Monster extends Actor {
    Bullet shoot(Point position, double angle, Context context);

    int beat();

    boolean isAbleToBeat(Point position);

    void calculateStep(Point position);
}
