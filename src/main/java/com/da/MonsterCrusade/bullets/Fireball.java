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
    private Point position;
    private double angle;

    public Fireball(Point position, double angle, Context context) {
        this.position = position;
        this.angle = angle;

        Bitmap temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.fireball);
        IMAGE = BitmapTransformer.getResizedBitmap(temp, IMAGE_SIZE, IMAGE_SIZE);
    }

    @Override
    public void draw(Canvas canvas) {
        nextPosition();
        float rotateAngle = (float) (angle * 180 / Math.PI);
        Bitmap bitmap = BitmapTransformer.rotateBitmap(IMAGE, -rotateAngle);
        canvas.drawBitmap(bitmap, position.x, position.y, null);
    }

    @Override
    public boolean isIntersection(Actor actor) {
        return false;
    }

    private void nextPosition() {
        Log.d(TAG, toString());
        position.x +=  Math.cos(angle) * SPEED;
        position.y -=  Math.sin(angle) * SPEED;
    }


public void setPosition(Point position) {
        this.position = position;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

        @Override
    public String toString() {
        return "Fireball{" +
                "angle=" + angle +
                ", position=" + position +
                '}';
    }
}
