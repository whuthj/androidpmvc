package com.hujun.mypuremvc.mvc.view.activity;

import android.support.v4.app.FragmentActivity;

public class BaseFragmentActivity extends FragmentActivity {
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
