package com.hujun.mypuremvc.mvc.model;

import com.hujun.mypuremvc.mvc.model.bean.StartupBean;

import org.puremvc.java.multicore.patterns.proxy.Proxy;

public class StartupProxy extends Proxy {
    public static final String NAME = "StartupProxy";

    public StartupBean getBean() {
        return bean;
    }

    public void setBean(StartupBean bean) {
        this.bean = bean;
    }

    private StartupBean bean;

    public StartupProxy() {
        super(NAME);
    }
}
