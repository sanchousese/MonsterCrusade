package com.da.MonsterCrusade.weapons;

import android.content.Context;
import android.graphics.Point;
import com.da.MonsterCrusade.bullets.Bullet;

/**
 * Created by ihorkroosh on 4/5/15.
 */
public interface Weapon {
    Bullet shoot(double angle, Point position, Context context);
    int beat();
}
