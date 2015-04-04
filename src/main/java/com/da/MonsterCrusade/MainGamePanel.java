package com.da.MonsterCrusade;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.da.MonsterCrusade.controls.JoystickView;
import com.da.MonsterCrusade.entities.Hero;

/**
 * Created by sancho on 04.04.15.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();
    private Hero hero;

    private MainThread mainThread;
    public static JoystickView joystickView;

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
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        hero.goWithCost(joystickView.getTouchX(), joystickView.getTouchY());
        hero.draw(canvas);
    }
}
