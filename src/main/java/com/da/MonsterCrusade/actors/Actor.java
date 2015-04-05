package com.da.MonsterCrusade.actors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import com.da.MonsterCrusade.R;
import com.da.MonsterCrusade.bullets.Bullet;
import com.da.MonsterCrusade.bullets.Fireball;
import com.da.MonsterCrusade.utils.BitmapTransformer;

/**
 * Created by sancho on 04.04.15.
 */
public interface Actor {
    void draw(Canvas canvas);

    void damageWithCost(int cost);

    boolean isDead();

    Point getPosition();

    int getImageSize();
}
