package com.perdev.compat.flashlight;

import android.content.Context;
import android.os.Build.VERSION;

/**
 * Project    AndroidCompat
 * Path       com.perdev.compat.flashlight
 * Date       2020/09/22 - 16:08
 * Author     Payne.
 * About      类描述：
 * flashlight兼容类，使用组合+工厂实现，该类对外提供接口
 */
public class FlashlightCompat {

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

    public void init(Context context) {
        mFlashlightCompat.init(context);
    }

    public void on() {
        mFlashlightCompat.on();
    }

    public void off() {
        mFlashlightCompat.off();
    }

    public boolean getState() {
        return mFlashlightCompat.getState();
    }

    public void listenStateChange(OnStateChangeListener listener) {
        mFlashlightCompat.listenStateChange(listener);
    }

    public void listenError(OnErrorListener listener) {
        mFlashlightCompat.listenError(listener);
    }

    public void destroy() {
        mFlashlightCompat.destroy();
    }
}
