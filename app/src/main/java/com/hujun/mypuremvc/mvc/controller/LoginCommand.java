package com.hujun.mypuremvc.mvc.controller;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class LoginCommand extends SimpleCommand {
    public static final String NAME = "LoginCommand";

    @Override
    public void execute(INotification notification) {
        super.execute(notification);
    }
}
