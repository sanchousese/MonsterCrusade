package com.da.MonsterCrusade.bullets;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import com.da.MonsterCrusade.actors.Actor;

/**
 * Created by ihorkroosh on 4/5/15.
 */
public interface Bullet {
    void draw(Canvas canvas);

    boolean hasIntersection(Actor actor);

    void setPosition(Point position);

    void setAngle(double angle);

    int getDamage();
}
