package com.da.MonsterCrusade;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.da.MonsterCrusade.bullets.Bullet;
import com.da.MonsterCrusade.controls.JoystickView;
import com.da.MonsterCrusade.entities.Hero;
import com.da.MonsterCrusade.utils.DisplayInfo;

import java.util.LinkedList;

/**
 * Created by sancho on 04.04.15.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();
    private Hero hero;

    private MainThread mainThread;
    public static JoystickView joystickView;
    public static JoystickView angleView;
    private Point screenSize;
    private LinkedList<Bullet> bullets;
    private static final long SHOOT_DELAY = 300L;
    private long lastShootTime;

    public MainGamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);
        hero = new Hero(DisplayInfo.getSize(context).x / 2, DisplayInfo.getSize(context).y / 2, 0, context);
        mainThread = new MainThread(getHolder(), this);

        screenSize = DisplayInfo.getSize(context);
        setFocusable(true);
        bullets = new LinkedList<Bullet>();
        lastShootTime = System.currentTimeMillis();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mainThread.setRunning(true);
        mainThread.start();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        while (true) {
            try {
                mainThread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        hero.goWithCost(joystickView.getTouchX(), joystickView.getTouchY());
        hero.turnOnAngle(Math.atan2(angleView.getTouchX(), angleView.getTouchY()));
        if(System.currentTimeMillis() - lastShootTime > SHOOT_DELAY) {
            if(bullets.size() > 5)
                bullets.removeFirst();
            bullets.add(hero.shoot());
            lastShootTime = System.currentTimeMillis();
        }

        hero.draw(canvas);

        for(Bullet bullet: bullets) {
            bullet.draw(canvas);
        }
    }


}
