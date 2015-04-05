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

import java.util.ArrayList;
import java.util.Iterator;
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
    private ArrayList<Bullet> bullets;
    private ArrayList<Monster> monsters;
    private static final long SHOOT_DELAY = 300L;
    private static final long MONSTERS_APPEARING_DELAY = 3000L;
    private long lastShootTime;
    private long monstersAppearingTime;

    public MainGamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);
        Point startHeroPosition = new Point(DisplayInfo.getSize(getContext()).x / 2, DisplayInfo.getSize(getContext()).y / 2);
        hero = new Hero(startHeroPosition, 0, getContext(), new HeroWeapon());
        screenSize = DisplayInfo.getSize(getContext());
        setFocusable(true);
        bullets = new ArrayList<Bullet>();
        monsters = new ArrayList<Monster>();

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
            if (bullets.size() > 15)
                bullets.remove(0);
            bullets.add(hero.shoot());
            lastShootTime = System.currentTimeMillis();
        }

        if (System.currentTimeMillis() - monstersAppearingTime > MONSTERS_APPEARING_DELAY && monsters.size() <= 5) {
            monsters.add(Zombie.generate(getContext()));
            monstersAppearingTime = System.currentTimeMillis();
        }

        Iterator<Monster> monsterIterator = monsters.iterator();
        while (monsterIterator.hasNext()) {
            Monster monster = monsterIterator.next();
            monster.calculateStep(hero.getPosition());

            if(monster.isAbleToBeat(hero.getPosition())) {
                hero.damageWithCost(monster.beat());
            }

            drawActor(monster, canvas);
            if(monster.isDead()){
                monsterIterator.remove();
            }
        }

//        for (Monster monster : monsters) {
//            monster.calculateStep(hero.getPosition());
//
//            drawActor(monster, canvas);
//        }

        if(hero.isDead()) {
            mainThread.setRunning(false);
            killThread();
        } else {
            hero.draw(canvas);

            for (Bullet bullet : bullets) {
                bullet.draw(canvas);
            }
        }
    }

    private void drawActor(Actor actor, Canvas canvas) {
        Iterator it = bullets.iterator();
        while (it.hasNext()){
            Bullet bullet = (Bullet) it.next();
            if (bullet.hasIntersection(actor)) {
                actor.damageWithCost(bullet.getDamage());
                it.remove();
                if (actor.isDead()) {
                    return;
                }
            }
        }

        actor.draw(canvas);
    }

}
