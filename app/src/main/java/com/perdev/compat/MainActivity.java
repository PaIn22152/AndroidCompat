package com.perdev.compat;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.perdev.compat.flashlight.FlashlightCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlashlightCompat flashlightCompat = new FlashlightCompat();
        flashlightCompat.init(this);
        flashlightCompat.on();

    }
}
