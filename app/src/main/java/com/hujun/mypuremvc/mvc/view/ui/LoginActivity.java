package com.hujun.mypuremvc.mvc.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hujun.mypuremvc.R;
import com.hujun.mypuremvc.mvc.MyAppFacade;
import com.hujun.mypuremvc.mvc.view.LoginMediator;
import com.hujun.mypuremvc.mvc.view.ShowPicMediator;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        registerMediator();
    }

    @Override
    protected void registerMediator() {
        if (MyAppFacade.getInstance().hasMediator(LoginMediator.NAME)) {
            return;
        }
        MyAppFacade.getInstance().registerMediator(new LoginMediator(this));
    }

    @Override
    protected void unregisterMediator() {
        MyAppFacade.getInstance().removeMediator(LoginMediator.NAME);
    }

    @Override
    public void onClick(View view) {
        LoginMediator mediator = (LoginMediator)MyAppFacade.getInstance().retrieveMediator(LoginMediator.NAME);
        switch (view.getId()) {
            case R.id.btnLogin:
                mediator.gotoShowPicAct();
                break;
            default:
                break;
        }
    }
}
