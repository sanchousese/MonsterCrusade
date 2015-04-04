package com.da.MonsterCrusade;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by sancho on 04.04.15.
 */
public class MainThread extends Thread {

    private static final String TAG = MainThread.class.getSimpleName();

    private static final int MAX_FPS = 20;
    private static final int MAX_FRAMES_SKIPS = 5;
    private static final int FRAME_PERIOD = 1000 / MAX_FPS;

    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;
    private static boolean running;

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        Canvas canvas;
        Log.d(TAG, "Starting game loop");

        long beginTime;
        long timeDiff;
        int sleepTime = 0;
        int frameSkipped;

        while (running) {
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    beginTime = System.currentTimeMillis();
                    frameSkipped = 0;
                    gamePanel.onDraw(canvas);

                    timeDiff = System.currentTimeMillis() - beginTime;
                    sleepTime = (int) (FRAME_PERIOD - timeDiff);

                    if (sleepTime > 0) {
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {}
                    }

                    while (sleepTime < 0 && frameSkipped < MAX_FRAMES_SKIPS) {
                        sleepTime += FRAME_PERIOD;
                        frameSkipped++;
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public static void setRunning(boolean running) {
        MainThread.running = running;
    }
}
