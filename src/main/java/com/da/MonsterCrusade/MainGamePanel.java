package com.da.MonsterCrusade;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.da.MonsterCrusade.entities.Hero;
import com.da.MonsterCrusade.entities.components.Speed;

/**
 * Created by sancho on 04.04.15.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();
    private Hero hero;

    private MainThread mainThread;

    public MainGamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);
        hero = new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1), 50, 50);
        mainThread = new MainThread(getHolder(), this);

        setFocusable(true);
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
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            hero.handleActionDown((int) event.getX(), (int) event.getY());

            if(event.getAction() == MotionEvent.ACTION_MOVE){
                if(hero.isTouched()){
                    hero.setX((int) event.getX());
                    hero.setY((int) event.getY());
                }
            } if(event.getAction() == MotionEvent.ACTION_UP) {
                if (hero.isTouched()) {
                    hero.setTouched(false);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        hero.draw(canvas);
    }

    public void update() {
        // check collision with right wall if heading right
        if (hero.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
                && hero.getX() + hero.getBitmap().getWidth() / 2 >= getWidth()) {
            hero.getSpeed().toggleXDirection();
        }
        // check collision with left wall if heading left
        if (hero.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
                && hero.getX() - hero.getBitmap().getWidth() / 2 <= 0) {
            hero.getSpeed().toggleXDirection();
        }
        // check collision with bottom wall if heading down
        if (hero.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
                && hero.getY() + hero.getBitmap().getHeight() / 2 >= getHeight()) {
            hero.getSpeed().toggleYDirection();
        }
        // check collision with top wall if heading up
        if (hero.getSpeed().getyDirection() == Speed.DIRECTION_UP
                && hero.getY() - hero.getBitmap().getHeight() / 2 <= 0) {
            hero.getSpeed().toggleYDirection();
        }
        // Update the lone droid
        hero.update();

    }
}
