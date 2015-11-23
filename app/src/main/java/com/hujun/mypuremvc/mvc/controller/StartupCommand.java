package com.hujun.mypuremvc.mvc.controller;

import com.hujun.mypuremvc.mvc.NotificationNames;
import com.hujun.mypuremvc.mvc.model.bean.StartupBean;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class StartupCommand extends SimpleCommand {
    public static final String NAME = "StartupCommand";

    @Override
    public void execute(INotification notification) {
        super.execute(notification);

        StartupBean bean = new StartupBean();
        bean.setStartupCmdLine("PureMvc就是屌");

        this.sendNotification(NotificationNames.START_UP, bean);
    }
}
