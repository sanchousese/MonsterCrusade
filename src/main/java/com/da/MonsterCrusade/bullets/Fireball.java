package com.da.MonsterCrusade.bullets;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import com.da.MonsterCrusade.R;
import com.da.MonsterCrusade.actors.Actor;
import com.da.MonsterCrusade.utils.BitmapTransformer;

/**
 * Created by ihorkroosh on 4/5/15.
 */
public class Fireball implements Bullet {
    private final static String TAG = Fireball.class.getSimpleName();
    private final static int DAMAGE = 10;
    private final static int SPEED = 25;
    private final static int IMAGE_SIZE = 40;
    private final Bitmap IMAGE;
    private int x;
    private int y;
    private double angle;

    public Fireball(Point position, double angle, Context context) {
        x = position.x;
        y = position.y;

        this.angle = angle;

        Bitmap temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.fireball);
        IMAGE = BitmapTransformer.getResizedBitmap(temp, IMAGE_SIZE, IMAGE_SIZE);
    }

    @Override
    public void draw(Canvas canvas) {
        nextPosition();
        float rotateAngle = (float) (angle * 180 / Math.PI);
        Bitmap bitmap = BitmapTransformer.rotateBitmap(IMAGE, -rotateAngle);
        canvas.drawBitmap(bitmap, x, y, null);
    }

    @Override
    public boolean hasIntersection(Actor actor) {
        return false;
    }

    @Override
    public void setPosition(Point position) {

    }

    private void nextPosition() {
        x += Math.cos(angle) * SPEED;
        y -= Math.sin(angle) * SPEED;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }


}
