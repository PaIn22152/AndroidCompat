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

    private IFlashlight mFlashlightCompat;

    public FlashlightCompat() {
        //目前适配了api22及其以下版本 和 api23及其以上版本 两种机型
        //当有新的需要适配的机型时，新建实现类实现IFlashlight接口，并在此判断机型，使用新的实现类
        if (VERSION.SDK_INT >= 23) {
            mFlashlightCompat = new FlashlightImpl_23_up();
        } else {
            mFlashlightCompat = new FlashlightImpl_22_low();
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

    public void release() {
        mFlashlightCompat.release();
    }
}
