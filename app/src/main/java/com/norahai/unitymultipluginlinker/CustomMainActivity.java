package com.norahai.unitymultipluginlinker;

import com.google.firebase.messaging.MessageForwardingService;
import com.unity3d.player.UnityPlayerActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.sdkbox.plugin.SDKBox;
import android.content.Intent;

public class CustomMainActivity extends UnityPlayerActivity {

    /**
     * Dispose of the mUnityPlayer when restarting the app.
     *
     * This ensures that when the app starts up again it does not start with stale data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mUnityPlayer != null) {
            mUnityPlayer.quit();
            mUnityPlayer = null;
        }
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_custom_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        SDKBox.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Workaround for when a message is sent containing both a Data and Notification payload.
     * <p>
     * When the app is in the background, if a message with both a data and notification payload is
     * receieved the data payload is stored on the Intent passed to onNewIntent. By default, that
     * intent does not get set as the Intent that started the app, so when the app comes back online
     * it doesn't see a new FCM message to respond to. As a workaround, we override onNewIntent so
     * that it sends the intent to the MessageForwardingService which forwards the message to the
     * FirebaseMessagingService which in turn sends the message to the application.
     */
    @Override
    protected void onNewIntent(Intent intent) {
        Intent message = new Intent(this, MessageForwardingService.class);
        message.setAction(MessageForwardingService.ACTION_REMOTE_INTENT);
        message.putExtras(intent);
        message.setData(intent.getData());
        startService(message);
    }
}
