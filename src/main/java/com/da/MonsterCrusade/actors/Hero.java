package com.da.MonsterCrusade.actors;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import com.da.MonsterCrusade.R;
import com.da.MonsterCrusade.bullets.Bullet;
import com.da.MonsterCrusade.bullets.Fireball;
import com.da.MonsterCrusade.utils.BitmapTransformer;

/**
 * Created by sancho on 04.04.15.
 */
public class Hero {
    private static final String TAG = Hero.class.getSimpleName();
    private final static int IMAGE_SIZE = 50;

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
        speed = 1 / 5.0;
        this.context = context;
        Bitmap temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.wizard);
        IMAGE = BitmapTransformer.getResizedBitmap(temp, IMAGE_SIZE, IMAGE_SIZE);
    }


    public void draw(Canvas canvas) {
        float rotateAngle = (float) (angle * 180 / Math.PI);
        Bitmap bitmap = BitmapTransformer.rotateBitmap(IMAGE, 180-rotateAngle);
        canvas.drawBitmap(bitmap, x, y, null);
    }

    public Bullet shoot() {
        return new Fireball(new Point(x, y), angle, context);
    }


    public void goWithCost(double costX, double costY) {
        x += costX * speed;
        y += costY * speed;
    }


    public void turnOnAngle(double y, double x) {
        Log.d(TAG, "agle: " + angle);
        this.angle = Math.atan2(y, x);
    }
}
