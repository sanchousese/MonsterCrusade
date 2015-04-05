package com.da.MonsterCrusade.entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import com.da.MonsterCrusade.R;
import com.da.MonsterCrusade.bullets.Bullet;
import com.da.MonsterCrusade.bullets.Fireball;
import com.da.MonsterCrusade.entities.components.Speed;

/**
 * Created by sancho on 04.04.15.
 */
public class Hero {
    private static final String TAG = Hero.class.getSimpleName();

    private final Bitmap IMAGE;
    private int x;
    private int y;
    private boolean touched;
    private double speed;
    private double angle;
    private Context context;

    public Hero(int x, int y, double angle, Context context) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        speed = 1 / 10.0;
        this.context = context;
        IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.wizard);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(IMAGE, x - (IMAGE.getWidth() / 2), y - (IMAGE.getHeight() / 2), null);
    }

    public Bullet shoot() {
        return new Fireball(new Point(x, y), angle, context);
    }

    public void handleActionDown(int eventX, int eventY) {
        setTouched(eventX >= (x - IMAGE.getWidth() / 2) && (eventX <= (x + IMAGE.getWidth() / 2)) &&
                eventY >= (y - IMAGE.getHeight() / 2) && (eventY <= (y + IMAGE.getHeight() / 2)));
    }

    public void goWithCost(double costX, double costY) {
        x += costX * speed;
        y += costY * speed;
    }


    public void turnOnAngle(double angle) {
        Log.d(TAG, "agle: " + angle);
        this.angle = angle;
    }
}
