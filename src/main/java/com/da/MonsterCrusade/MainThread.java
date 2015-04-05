package com.da.MonsterCrusade;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

class MainThread extends Thread{
    private boolean runFlag = false;
    private SurfaceHolder surfaceHolder;
    private MainGamePanel mainPanel;


    private static final int MAX_FPS = 20;
    private static final int MAX_FRAMES_SKIPS = 5;
    private static final int FRAME_PERIOD = 1000 / MAX_FPS;

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel mainPanel){
        this.surfaceHolder = surfaceHolder;
        this.mainPanel = mainPanel;
    }

    public void setRunning(boolean run) {
        runFlag = run;
    }

    public void run() {
        Canvas canvas;

        long beginTime;
        long timeDiff;
        int sleepTime = 0;
        int frameSkipped;

        while (runFlag) {
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    beginTime = System.currentTimeMillis();
                    frameSkipped = 0;

                    mainPanel.onDraw(canvas);

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
            }
            finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}