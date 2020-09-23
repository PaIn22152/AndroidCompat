package com.perdev.compat.flashlight;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;

/**
 * Project    AndroidCompat
 * Path       com.perdev.compat.flashlight
 * Date       2020/09/22 - 16:12
 * Author     Payne.
 * About      类描述：
 * 手电筒实现类，[,api-22]
 */
class FlashlightImpl_22_low implements IFlashlight {

    private Camera                mCamera;
    private boolean               mState = false;
    private OnStateChangeListener mStateChangeListener;
    private OnErrorListener       mErrorListener;

    @Override
    public void init(Context context) {

    }

    @Override
    public void on() {

        ThreadPool.run(() -> {
            try {
                L.d("on start");
                if (mCamera == null) {
                    mCamera = Camera.open();
                }
                Camera.Parameters parameter = mCamera.getParameters();
                parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                mCamera.setParameters(parameter);
                mCamera.setPreviewTexture(new SurfaceTexture(0));
                mCamera.startPreview();
                mState = true;
                L.d("on end");
            } catch (Exception e) {
                L.d("on e=" + e);
                if (mErrorListener != null) {
                    mErrorListener.onError("on() e=" + e);
                }
            }
        });

    }

    @Override
    public void off() {
        ThreadPool.run(() -> {
            L.d("off start");
            if (mCamera == null) {
                mCamera = Camera.open();
            }
            Camera.Parameters parameter = mCamera.getParameters();
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(parameter);
            mCamera.stopPreview();
            mState = false;
            L.d("off end");
        });

    }

    @Override
    public boolean getState() {
        return mState;
    }

    @Override
    public void listenStateChange(OnStateChangeListener listener) {
        mStateChangeListener = listener;
    }

    @Override
    public void listenError(OnErrorListener listener) {
        mErrorListener = listener;
    }

    @Override
    public void release() {
        if (mCamera != null) {
            mCamera.release();
        }
        mCamera = null;
    }
}
