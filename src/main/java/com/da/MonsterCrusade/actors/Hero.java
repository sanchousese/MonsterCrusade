package com.da.MonsterCrusade.actors;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import com.da.MonsterCrusade.R;
import com.da.MonsterCrusade.bullets.Bullet;
import com.da.MonsterCrusade.bullets.Fireball;
import com.da.MonsterCrusade.utils.BitmapTransformer;
import com.da.MonsterCrusade.weapons.Weapon;

import java.util.WeakHashMap;

/**
 * Created by sancho on 04.04.15.
 */
public class Hero implements Actor {
    private static final String TAG = Hero.class.getSimpleName();
    private final static int IMAGE_SIZE = 50;
    private static final int MAX_HEALTH = 100;

    private final Bitmap IMAGE;
    private Point position;
    private double speed = 1 / 5.0;
    private double angle;
    private Context context;
    private int health;
    private Weapon weapon;

    public Hero(Point position, double angle, Context context, Weapon weapon) {
        this.position = position;
        this.angle = angle;
        this.context = context;
        Bitmap temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.wizard);
        IMAGE = BitmapTransformer.getResizedBitmap(temp, IMAGE_SIZE, IMAGE_SIZE);
        health = MAX_HEALTH;
        this.weapon = weapon;
    }

    @Override
    public void draw(Canvas canvas) {
        float rotateAngle = (float) (angle * 180 / Math.PI);
        Bitmap bitmap = BitmapTransformer.rotateBitmap(IMAGE, 180 - rotateAngle);
        canvas.drawBitmap(bitmap, position.x, position.y, null);
    }

    @Override
    public void damageWithCost(int cost) {
        health -= cost;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public int getImageSize() {
        return IMAGE_SIZE;
    }

    public Bullet shoot() {
        return weapon.shoot(angle, position, context);
    }

    public void goWithCost(double costX, double costY) {
        position.x += costX * speed;
        position.y += costY * speed;
    }

    public void turnOnAngle(double y, double x) {
        this.angle = Math.atan2(y, x);
    }
}
