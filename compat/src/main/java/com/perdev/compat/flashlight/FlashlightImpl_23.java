package com.perdev.compat.flashlight;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build.VERSION_CODES;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/**
 * Project    AndroidCompat
 * Path       com.perdev.compat.flashlight
 * Date       2020/09/22 - 15:52
 * Author     Payne.
 * About      类描述：
 * 手电筒实现类，[api-23,
 */
@RequiresApi(api = VERSION_CODES.M)
class FlashlightImpl_23 implements FlashlightInterface {

    private boolean                     mState = false;
    private Context                     mContext;
    private CameraManager               mCameraManager;
    private CameraManager.TorchCallback mTorchCallback;
    private OnStateChangeListener       mStateChangeListener;
    private OnErrorListener             mErrorListener;

    @Override
    public void init(Context context) {
        mContext = context.getApplicationContext();

        mCameraManager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);

        mTorchCallback = new CameraManager.TorchCallback() {
            @Override
            public void onTorchModeChanged(@NonNull String cameraId, boolean enabled) {
                super.onTorchModeChanged(cameraId, enabled);
                mState = enabled;
                if (mStateChangeListener != null) {
                    mStateChangeListener.onChanged(mState);
                }
            }
        };
    }

    private void setTorchMode(boolean state) {
        try {
            //获取当前手机所有摄像头设备ID
            String[] ids = mCameraManager.getCameraIdList();
            for (String id : ids) {
                CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
                //查询该摄像头组件是否包含闪光灯
                Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                if (flashAvailable != null && flashAvailable
                        && lensFacing != null
                        && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                    //打开或关闭手电筒
                    mCameraManager.setTorchMode(id, state);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (mErrorListener != null) {
                mErrorListener.onError("setTorchMode Exception e = " + e);
            }
        }
    }

    @Override
    public void on() {
        setTorchMode(true);
    }

    @Override
    public void off() {
        setTorchMode(false);
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

    }

    @Override
    public void destroy() {
        if (mCameraManager != null && mTorchCallback != null) {
            mCameraManager.unregisterTorchCallback(mTorchCallback);
        }
    }
}
