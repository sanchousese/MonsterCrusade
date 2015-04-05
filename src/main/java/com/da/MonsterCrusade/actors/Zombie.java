package com.da.MonsterCrusade.actors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import com.da.MonsterCrusade.R;
import com.da.MonsterCrusade.bullets.Bullet;
import com.da.MonsterCrusade.utils.BitmapTransformer;
import com.da.MonsterCrusade.utils.DisplayInfo;
import com.da.MonsterCrusade.weapons.Weapon;
import com.da.MonsterCrusade.weapons.ZombieWeapon;

/**
 * Created by ihorkroosh on 4/5/15.
 */
public class Zombie implements Actor, Monster {
    private static final String TAG = Zombie.class.getSimpleName();
    private final static int IMAGE_SIZE = 50;
    private static final int MAX_HEALTH = 100;

    private final Bitmap IMAGE;

    private Point position;
    private double angle;
    private Weapon weapon;
    private double speed = 10.0;
    private int health;

    public Zombie(Point position, double angle, Context context) {
        Bitmap temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.zombie);
        IMAGE = BitmapTransformer.getResizedBitmap(temp, IMAGE_SIZE, IMAGE_SIZE);

        this.position = position;
        this.angle = angle;
        weapon = new ZombieWeapon();
        health = MAX_HEALTH;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(IMAGE, position.x, position.y, null);
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
    public Bullet shoot(Point position, double angle, Context context) {
        return weapon.shoot(angle, position, context);
    }

    @Override
    public int beat() {
        return weapon.beat();
    }

    @Override
    public boolean isAbleToBeat(Point position) {
        return Math.sqrt(Math.pow((position.x - this.position.x), 2) + Math.pow((position.y - this.position.y), 2)) <= 5;
    }

    @Override
    public void calculateStep(Point position) {
        int distance = (int)Math.sqrt( Math.pow(this.position.x - position.x, 2) + Math.pow(this.position.y - position.y, 2));

        double T = speed / distance;
        this.position.x = (int) ((1 - T) * this.position.x + T * position.x);
        this.position.y = (int) ((1 - T) * this.position.y + T * position.y);
    }

    public static Zombie generate(Context context) {
//        return new Zombie(new Point((int)(Math.random() * DisplayInfo.getSize(context).x), (int) (Math.random() * DisplayInfo.getSize(context).y)), 0, context);
        return new Zombie(new Point((int)(DisplayInfo.getSize(context).x / 2.0), (int) (DisplayInfo.getSize(context).y / 2.0)), 0, context);
    }
}
