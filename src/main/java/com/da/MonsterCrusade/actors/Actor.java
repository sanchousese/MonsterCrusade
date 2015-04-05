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
public abstract class Actor {
    protected final static int IMAGE_SIZE = 50;

    protected final Bitmap IMAGE;
    protected int x;
    protected int y;
    protected double speed;
    protected double angle;
    protected Context context;

    public Actor(Bitmap IMAGE, int x, int y, double speed, double angle, Context context) {
        this.IMAGE = IMAGE;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
        this.context = context;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(IMAGE, x - (IMAGE.getWidth() / 2), y - (IMAGE.getHeight() / 2), null);
    }

    public abstract Bullet attack();


    public abstract void goWithCost(double costX, double costY);


    public abstract void turnOnAngle(double y, double x);
}
