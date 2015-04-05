package com.da.MonsterCrusade.bullets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import com.da.MonsterCrusade.R;
import com.da.MonsterCrusade.actors.Actor;

/**
 * Created by ihorkroosh on 4/5/15.
 */
public class Fireball implements Bullet {
    private final static String TAG = Fireball.class.getSimpleName();
    private final static int DAMAGE = 10;
    private final static int SPEED = 15;
    private final Bitmap IMAGE;
    private Point position;
    private double angle;

    public Fireball(Point position, double angle, Context context) {
        this.position = position;
        this.angle = angle;

        IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.fireball);
    }

    @Override
    public void draw(Canvas canvas) {
        nextPosition();
        canvas.drawBitmap(IMAGE, position.x, position.y, null);
    }

    @Override
    public boolean isIntersection(Actor actor) {
        return false;
    }

    private void nextPosition() {
        Log.d(TAG, toString());
        position.x +=  Math.cos(angle) * SPEED;
        position.y +=  Math.sin(angle) * SPEED;
    }

    @Override
    public String toString() {
        return "Fireball{" +
                "angle=" + angle +
                ", position=" + position +
                '}';
    }
}
