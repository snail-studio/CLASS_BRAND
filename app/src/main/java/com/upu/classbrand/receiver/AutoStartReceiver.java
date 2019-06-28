package com.upu.classbrand.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.upu.classbrand.ui.welcome;

public class AutoStartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Intent i =new Intent(context, welcome.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
