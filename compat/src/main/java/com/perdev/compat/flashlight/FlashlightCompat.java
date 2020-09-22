package com.perdev.compat.flashlight;

import android.content.Context;
import android.os.Build.VERSION;

/**
 * Project    AndroidCompat
 * Path       com.perdev.compat.flashlight
 * Date       2020/09/22 - 16:08
 * Author     Payne.
 * About      类描述：
 */
public class FlashlightCompat implements FlashlightInterface {


    private FlashlightInterface mFlashlightCompat;

    public FlashlightCompat() {
        if (VERSION.SDK_INT >= 23) {
            mFlashlightCompat = new FlashlightImpl_23();
        } else if (VERSION.SDK_INT >= 21) {
            mFlashlightCompat = new FlashlightImpl_21_22();
        } else {
            mFlashlightCompat = new FlashlightImpl_16_20();
        }
    }

    @Override
    public void init(Context context) {
        if (mFlashlightCompat != null) {
            mFlashlightCompat.init(context);
        }

    }

    @Override
    public void on() {
        if (mFlashlightCompat != null) {
            mFlashlightCompat.on();
        }
    }

    @Override
    public void off() {
        if (mFlashlightCompat != null) {
            mFlashlightCompat.off();
        }
    }

    @Override
    public boolean getState() {
        if (mFlashlightCompat != null) {
            return mFlashlightCompat.getState();
        }
        return false;
    }

    @Override
    public void listenStateChange(OnStateChangeListener listener) {
        if (mFlashlightCompat != null) {
            mFlashlightCompat.listenStateChange(listener);
        }
    }

    @Override
    public void listenError(OnErrorListener listener) {
        if (mFlashlightCompat != null) {
            mFlashlightCompat.listenError(listener);
        }
    }

    @Override
    public void destroy() {
        if (mFlashlightCompat != null) {
            mFlashlightCompat.destroy();
        }
    }
}
