package com.da.MonsterCrusade;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.da.MonsterCrusade.actors.Actor;
import com.da.MonsterCrusade.actors.Monster;
import com.da.MonsterCrusade.actors.Zombie;
import com.da.MonsterCrusade.bullets.Bullet;
import com.da.MonsterCrusade.controls.JoystickView;
import com.da.MonsterCrusade.actors.Hero;
import com.da.MonsterCrusade.utils.DisplayInfo;
import com.da.MonsterCrusade.weapons.HeroWeapon;

import java.util.LinkedList;

/**
 * Created by sancho on 04.04.15.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();
    private MainThread mainThread;

    private Hero hero;
    public static JoystickView joystickView;
    public static JoystickView angleView;
    private Point screenSize;
    private LinkedList<Bullet> bullets;
    private LinkedList<Monster> monsters;
    private static final long SHOOT_DELAY = 300L;
    private long lastShootTime;

    public MainGamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);
        Point startHeroPosition = new Point(DisplayInfo.getSize(getContext()).x / 2, DisplayInfo.getSize(getContext()).y / 2);
        hero = new Hero(startHeroPosition, 0, getContext(), new HeroWeapon());
        screenSize = DisplayInfo.getSize(getContext());
        setFocusable(true);
        bullets = new LinkedList<Bullet>();
        monsters = new LinkedList<Monster>();

        monsters.add(Zombie.generate(context));
        monsters.add(Zombie.generate(context));

        lastShootTime = System.currentTimeMillis();
    }

    public void resume() {
        if (mainThread == null || mainThread.getState() == Thread.State.TERMINATED) {
            mainThread = new MainThread(getHolder(), this);
            mainThread.setRunning(true);
            mainThread.start();
        } else {
            mainThread.setRunning(true);
            mainThread.start();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        resume();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        killThread();
    }

    public void killThread() {
        if (mainThread != null)
            return;

        boolean retry = true;
        mainThread.setRunning(false);
        while (retry) {
            try {
                mainThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        hero.goWithCost(joystickView.getTouchX(), joystickView.getTouchY());

        hero.turnOnAngle(-angleView.getTouchY(), angleView.getTouchX());
        if (System.currentTimeMillis() - lastShootTime > SHOOT_DELAY) {
            if (bullets.size() > 5)
                bullets.removeFirst();
            bullets.add(hero.shoot());
            lastShootTime = System.currentTimeMillis();
        }

        for (Monster monster : monsters) {
            monster.calculateStep(hero.getPosition());

            if(monster.isAbleToBeat(hero.getPosition())) {
                hero.damageWithCost(monster.beat());
            }
            ((Actor)monster).draw(canvas);
        }

        if(!hero.isDead()) {
            for (Bullet bullet : bullets) {
                bullet.draw(canvas);
            }

            hero.draw(canvas);
        } else {
            mainThread.setRunning(false);
        }
    }

}
