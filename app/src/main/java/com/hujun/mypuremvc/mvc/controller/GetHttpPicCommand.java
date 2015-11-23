package com.hujun.mypuremvc.mvc.controller;

import android.os.Handler;
import android.util.Log;

import com.hujun.mypuremvc.mvc.NotificationNames;
import com.hujun.mypuremvc.mvc.model.bean.ShowPicBean;
import com.hujun.mypuremvc.utils.BitmapUtils;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetHttpPicCommand extends SimpleCommand{
    public static final String NAME = "GetHttpPicCommand";
    private static final ExecutorService sSingleThreadPool = Executors.newSingleThreadExecutor();

    @Override
    public void execute(INotification notification) {
        super.execute(notification);

        final ShowPicBean bean = (ShowPicBean)notification.getBody();
        final String strUrl = bean.getStrPicUrl();

        //读取图片做缓存机制都可以在逻辑层做
        sSingleThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                bean.setBitmap(BitmapUtils.getHttpBitmap(strUrl));

                bean.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        sendNotification(NotificationNames.GET_PIC_COMPLETE, bean);
                    }
                });
            }
        });
    }
}
