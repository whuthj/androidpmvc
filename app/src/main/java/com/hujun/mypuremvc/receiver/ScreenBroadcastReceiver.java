package com.hujun.mypuremvc.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.hujun.mypuremvc.mvc.MyAppFacade;
import com.hujun.mypuremvc.mvc.controller.ScreenEventCommand;
import com.hujun.mypuremvc.mvc.model.bean.ScreenEventType;

public class ScreenBroadcastReceiver extends BroadcastReceiver {
    private static ScreenBroadcastReceiver instance = null;

    public static synchronized void registerReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        context.registerReceiver(getInstance(), filter);
    }

    public static synchronized void unregisterReceiver(Context context) {
        context.unregisterReceiver(getInstance());
    }

    private static synchronized ScreenBroadcastReceiver getInstance() {
        if (null == instance) {
            instance = new ScreenBroadcastReceiver();
        }

        return instance;
    }

    public ScreenBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        ScreenEventType emType = ScreenEventType.Screen_NotSet;

        if (Intent.ACTION_SCREEN_ON.equals(action)) {
            emType = ScreenEventType.Screen_On;
        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            emType = ScreenEventType.Screen_Off;
        } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
            emType = ScreenEventType.Screen_Unlock;
        }

        MyAppFacade.getInstance().sendNotification(ScreenEventCommand.NAME, emType);
    }
}
