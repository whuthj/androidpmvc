package com.hujun.mypuremvc.mvc.controller;

import com.hujun.mypuremvc.mvc.NotificationNames;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class ScreenEventCommand extends SimpleCommand{
    public static final String NAME = "ScreenEventCommand";

    @Override
    public void execute(INotification notification) {
        super.execute(notification);

        sendNotification(NotificationNames.SCREEN_EVENT, notification.getBody());
    }
}
