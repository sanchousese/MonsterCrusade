package com.da.MonsterCrusade;

/**
 * Created by sancho on 04.04.15.
 */
public interface JoystickMovedListener {

    void onMoved(int pan, int tilt);

    void onReleased();

}
