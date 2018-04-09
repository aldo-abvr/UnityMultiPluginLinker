package com.norahai.unitymultipluginlinker;

import com.unity3d.player.UnityPlayerActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.sdkbox.plugin.SDKBox;
import android.content.Intent;
import android.view.WindowManager;

public class CustomMainActivity extends com.google.firebase.MessagingUnityPlayerActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        /*
        if (Build.VERSION.SDK_INT >= 23) {
            Intent intent = new Intent();
            String packageName = getApplicationContext().getPackageName();
            PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
            if (pm.isIgnoringBatteryOptimizations(packageName))
                intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
            else {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
            }
            getApplicationContext().startActivity(intent);
        }
      */

        super.onCreate(savedInstanceState);

        //Awake Lock
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        SDKBox.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
