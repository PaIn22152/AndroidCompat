package com.perdev.compat.flashlight;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Project    AndroidCompat
 * Path       com.perdev.compat.flashlight
 * Date       2020/09/23 - 15:54
 * Author     Payne.
 * About      类描述：
 */
class ThreadPool {

    private static ExecutorService cachedThreadPool = Executors.newSingleThreadExecutor();

    static void run(Runnable run) {
        cachedThreadPool.execute(run);
    }


}
