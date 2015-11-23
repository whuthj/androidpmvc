package com.hujun.mypuremvc.mvc;

import com.hujun.mypuremvc.mvc.controller.GetHttpPicCommand;
import com.hujun.mypuremvc.mvc.controller.GotoActCommand;
import com.hujun.mypuremvc.mvc.controller.LoginCommand;
import com.hujun.mypuremvc.mvc.controller.ScreenEventCommand;
import com.hujun.mypuremvc.mvc.controller.StartupCommand;
import com.hujun.mypuremvc.mvc.model.ShowPicProxy;
import com.hujun.mypuremvc.mvc.model.StartupProxy;

import org.puremvc.java.multicore.patterns.facade.Facade;

public class MyAppFacade extends Facade {
    public static final String APP_KEY = "MyPureMvc";
    private static MyAppFacade _sInstance = null;

    public static synchronized MyAppFacade getInstance() {
        if (_sInstance == null) {
            _sInstance = new MyAppFacade();
        }
        return _sInstance;
    }

    private MyAppFacade() {
        init(APP_KEY);
    }

    @Override
    protected void initializeController() {
        super.initializeController();
        registerCommand(StartupCommand.NAME, new StartupCommand());
        registerCommand(LoginCommand.NAME, new LoginCommand());
        registerCommand(GotoActCommand.NAME, new GotoActCommand());
        registerCommand(ScreenEventCommand.NAME, new ScreenEventCommand());
        registerCommand(GetHttpPicCommand.NAME, new GetHttpPicCommand());
    }

    @Override
    protected void initializeModel() {
        super.initializeModel();
        registerProxy(new StartupProxy());
        registerProxy(new ShowPicProxy());
    }

    @Override
    protected void initializeView() {
        super.initializeView();
    }

    public void startup() {
        this.sendNotification(StartupCommand.NAME);
    }
}
