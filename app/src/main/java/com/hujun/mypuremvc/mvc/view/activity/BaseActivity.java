package com.hujun.mypuremvc.mvc.view.activity;

import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    protected void registerMediator() {

    }

    protected void unregisterMediator() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerMediator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterMediator();
    }
}
