package com.da.MonsterCrusade;

import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by sancho on 04.04.15.
 */
public class MainThread extends Thread {

    private static final String TAG = MainThread.class.getSimpleName();

    private SurfaceHolder surfaceHolder;
    private MainGamePanel mainGamePanel;
    private static boolean running;

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel mainGamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.mainGamePanel = mainGamePanel;
    }

    @Override
    public void run() {
        long tickCount = 0L;
        Log.d(TAG, "Starting game loop");
        while (running){
            tickCount++;
        }
        Log.d(TAG, "Game loop executed " + tickCount + " times");
    }

    public static void setRunning(boolean running) {
        MainThread.running = running;
    }
}
