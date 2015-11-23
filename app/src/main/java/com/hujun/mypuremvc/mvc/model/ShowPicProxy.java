package com.hujun.mypuremvc.mvc.model;

import org.puremvc.java.multicore.patterns.proxy.Proxy;

public class ShowPicProxy extends Proxy {
    public static final String NAME = "ShowPicProxy";

    public String[] getArrPicUrl() {
        return arrPicUrl;
    }

    public void setArrPicUrl(String[] arrPicUrl) {
        this.arrPicUrl = arrPicUrl;
    }

    private String[] arrPicUrl;

    public ShowPicProxy() {
        super(NAME);
    }
}
