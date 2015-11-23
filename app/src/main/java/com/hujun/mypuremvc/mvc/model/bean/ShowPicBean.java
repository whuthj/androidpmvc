package com.hujun.mypuremvc.mvc.model.bean;

import android.graphics.Bitmap;
import android.os.Handler;

public class ShowPicBean {
    public String getStrPicUrl() {
        return strPicUrl;
    }

    public void setStrPicUrl(String strPicUrl) {
        this.strPicUrl = strPicUrl;
    }

    private String strPicUrl;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private Bitmap bitmap;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    private Handler handler;
}
