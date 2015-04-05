package com.da.MonsterCrusade.weapons;

import android.content.Context;
import android.graphics.Point;
import com.da.MonsterCrusade.bullets.Bullet;
import com.da.MonsterCrusade.bullets.Fireball;

/**
 * Created by ihorkroosh on 4/5/15.
 */
public class HeroWeapon implements Weapon {
    @Override
    public Bullet shoot(double angle, Point position, Context context) {
        return new Fireball(position, angle, context);
    }

    @Override
    public int beat() {
        return 0;
    }
}
