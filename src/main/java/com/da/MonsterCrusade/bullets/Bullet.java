package com.da.MonsterCrusade.bullets;

import android.graphics.Canvas;
import android.graphics.Point;
import com.da.MonsterCrusade.actors.Actor;

/**
 * Created by ihorkroosh on 4/5/15.
 */
public interface Bullet {
    void draw(Canvas canvas);

    boolean isIntersection(Actor actor);

    void setPosition(Point position);

    void setAngle(double angle);
}
