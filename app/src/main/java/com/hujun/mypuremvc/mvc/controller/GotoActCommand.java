package com.hujun.mypuremvc.mvc.controller;

import android.content.Intent;

import com.hujun.mypuremvc.mvc.model.bean.GotoActBean;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class GotoActCommand extends SimpleCommand {
    public static final String NAME = "GotoActCommand";

    @Override
    public void execute(INotification notification) {
        super.execute(notification);

        GotoActBean bean = (GotoActBean)notification.getBody();
        if (bean == null) { return; }
        if (bean.getFromAct() == null) { return; }
        if (bean.getToActCls() == null) { return; }

        bean.getFromAct().startActivity(new Intent(bean.getFromAct(), bean.getToActCls()));
        if (bean.isFinishFrom()) { bean.getFromAct().finish(); }
    }
}
