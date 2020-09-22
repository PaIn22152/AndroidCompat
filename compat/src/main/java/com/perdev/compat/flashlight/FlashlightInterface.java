package com.perdev.compat.flashlight;

import android.content.Context;

/**
 * Project    AndroidCompat
 * Path       com.perdev.compat.flashlight
 * Date       2020/09/22 - 15:49
 * Author     Payne.
 * About      类描述：
 */
interface FlashlightInterface {

    void init(Context context);//初始化

    void on();//打开手电筒

    void off();//关闭手电筒

    boolean getState();//获取手电筒开关状态

    void listenStateChange(OnStateChangeListener listener);//监听开关状态变化

    void listenError(OnErrorListener listener);//监听发生的错误

    void destroy();//回收资源

}
