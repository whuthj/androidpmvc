package com.hujun.mypuremvc.mvc.view.activity;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hujun.mypuremvc.R;
import com.hujun.mypuremvc.mvc.MyAppFacade;
import com.hujun.mypuremvc.mvc.view.LoginMediator;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    ViewPager pager = null;
    PagerTabStrip tabStrip = null;
    ArrayList<View> viewContainter = new ArrayList<View>();
    ArrayList<String> titleContainer = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        registerMediator();

        pager = (ViewPager) this.findViewById(R.id.viewpager);
        pager.setBackgroundColor(Color.parseColor("#663300"));
        pager.setOffscreenPageLimit(3);
        tabStrip = (PagerTabStrip) this.findViewById(R.id.tabstrip);
        tabStrip.setBackgroundColor(Color.parseColor("#993300"));
        tabStrip.setTabIndicatorColor(Color.parseColor("#FF0000"));
        tabStrip.setTextColor(Color.parseColor("#FFFFFF"));
        tabStrip.setDrawFullUnderline(false);
        //tabStrip.setTextSpacing(200);

        View view1 = LayoutInflater.from(this).inflate(R.layout.activity_show_pic, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.activity_login, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.fragment_show_pic, null);
        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);
        viewContainter.add(view4);
        titleContainer.add("Tab1");
        titleContainer.add("Tab2");
        titleContainer.add("Tab3");
        titleContainer.add("Tab4");

        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewContainter.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                ((ViewPager) container).removeView(viewContainter.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainter.get(position));
                return viewContainter.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleContainer.get(position);
            }
        });
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
