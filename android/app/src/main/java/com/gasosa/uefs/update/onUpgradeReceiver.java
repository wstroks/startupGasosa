package com.gasosa.uefs.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.database.core.Tag;


public class onUpgradeReceiver extends BroadcastReceiver {
String tag="upgradeReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(tag, "App got updated!");
    }
}
