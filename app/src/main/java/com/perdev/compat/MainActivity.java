package com.perdev.compat;

import android.Manifest.permission;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.perdev.compat.RXUtil.RXCallback;
import com.perdev.compat.flashlight.FlashlightCompat;

public class MainActivity extends AppCompatActivity {

    FlashlightCompat flashlightCompat = new FlashlightCompat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashlightCompat.init(this);

        findViewById(R.id.tv_am_sos).setOnClickListener(v -> {
            RXUtil.rxInterval(1000, false, new RXCallback() {
                @Override
                public void call() {
                    if (flashlightCompat.getState()) {
                        flashlightCompat.off();
                    } else {
                        flashlightCompat.on();
                    }
                }
            });
        });
        findViewById(R.id.tv_am_swi).setOnClickListener(v -> {
            if (flashlightCompat.getState()) {
                flashlightCompat.off();
            } else {
                flashlightCompat.on();
            }
        });

        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            requestPermissions(new String[]{permission.CAMERA}, 1);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flashlightCompat.release();
    }
}
