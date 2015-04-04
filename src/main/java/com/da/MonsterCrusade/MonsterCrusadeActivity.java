package com.da.MonsterCrusade;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MonsterCrusadeActivity extends Activity {

    private static final String TAG = MonsterCrusadeActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        MainGamePanel.joystickView = (JoystickView) findViewById(R.id.joystickView);
        Log.d(TAG, "View added");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Destroy...");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Stopping...");
        super.onStop();
    }
}
