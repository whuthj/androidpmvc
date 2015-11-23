package com.hujun.mypuremvc.mvc.view;

import com.hujun.mypuremvc.mvc.controller.GotoActCommand;
import com.hujun.mypuremvc.mvc.model.bean.GotoActBean;
import com.hujun.mypuremvc.mvc.view.ui.LoginActivity;
import com.hujun.mypuremvc.mvc.view.ui.ShowPicActivity;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

public class LoginMediator extends Mediator {
    public static final String NAME = "LoginMediator";

    private LoginActivity mLoginAct;

    public LoginMediator(LoginActivity act) {
        super(NAME, null);
        mLoginAct = act;
    }

    @Override
    public void handleNotification(INotification notification) {

    }

    @Override
    public String[] listNotificationInterests() {
        return new String[]{

        };
    }

    public void gotoShowPicAct() {
        GotoActBean bean = new GotoActBean();
        bean.setFromAct(mLoginAct);
        bean.setToActCls(ShowPicActivity.class);

        sendNotification(GotoActCommand.NAME, bean);
    }
}
