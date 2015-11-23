package com.hujun.mypuremvc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hujun.mypuremvc.mvc.MyAppFacade;
import com.hujun.mypuremvc.mvc.view.MainMediator;
import com.hujun.mypuremvc.mvc.view.ui.BaseActivity;
import com.hujun.mypuremvc.receiver.ScreenBroadcastReceiver;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnLogin = (Button)findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(this);

        registerMediator();
        ScreenBroadcastReceiver.registerReceiver(this);
        MyAppFacade.getInstance().startup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenBroadcastReceiver.unregisterReceiver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Test", "MainAct onPause");
    }

    @Override
    protected void onStop() {
        super.onPause();
        Log.e("Test", "MainAct onStop");
    }

    @Override
    protected void registerMediator() {
        if (MyAppFacade.getInstance().hasMediator(MainMediator.NAME)) {
            return;
        }
        MyAppFacade.getInstance().registerMediator(new MainMediator(this));
    }

    @Override
    protected void unregisterMediator() {
        MyAppFacade.getInstance().removeMediator(MainMediator.NAME);
    }

    @Override
    public void onClick(View view) {
        MainMediator mediator = (MainMediator)MyAppFacade.getInstance().retrieveMediator(MainMediator.NAME);

        switch (view.getId()) {
            case R.id.btnLogin:
                mediator.gotoLoginAct();
                break;
            default:
                break;
        }
    }
}
