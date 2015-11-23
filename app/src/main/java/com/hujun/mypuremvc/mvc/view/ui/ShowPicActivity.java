package com.hujun.mypuremvc.mvc.view.ui;

import android.os.Bundle;
import android.widget.Button;

import com.hujun.mypuremvc.R;
import com.hujun.mypuremvc.mvc.MyAppFacade;
import com.hujun.mypuremvc.mvc.view.ShowPicMediator;

public class ShowPicActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pic);

        registerMediator();
        ShowPicMediator mediator = (ShowPicMediator)MyAppFacade.getInstance().retrieveMediator(ShowPicMediator.NAME);
        mediator.showDefaultPic();
    }

    @Override
    protected void registerMediator() {
        super.registerMediator();
        if (MyAppFacade.getInstance().hasMediator(ShowPicMediator.NAME)) {
            return;
        }

        MyAppFacade.getInstance().registerMediator(new ShowPicMediator(this));
    }

    @Override
    protected void unregisterMediator() {
        super.unregisterMediator();
        MyAppFacade.getInstance().removeMediator(ShowPicMediator.NAME);
    }
}
