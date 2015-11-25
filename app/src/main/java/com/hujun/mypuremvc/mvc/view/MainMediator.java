package com.hujun.mypuremvc.mvc.view;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.hujun.mypuremvc.R;
import com.hujun.mypuremvc.mvc.MyAppFacade;
import com.hujun.mypuremvc.mvc.NotificationNames;
import com.hujun.mypuremvc.mvc.controller.GetHttpPicCommand;
import com.hujun.mypuremvc.mvc.controller.GotoActCommand;
import com.hujun.mypuremvc.mvc.model.ShowPicProxy;
import com.hujun.mypuremvc.mvc.model.bean.GotoActBean;
import com.hujun.mypuremvc.mvc.model.bean.ShowPicBean;
import com.hujun.mypuremvc.mvc.model.bean.StartupBean;
import com.hujun.mypuremvc.mvc.view.activity.LoginActivity;
import com.hujun.mypuremvc.MainActivity;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

public class MainMediator extends Mediator {
    public static final String NAME = "MainMediator";

    private MainActivity mMainActivity;
    private TextView mTxtHello;
    private ImageView mImageView;

    public MainMediator(MainActivity act) {
        super(NAME, null);
        this.mMainActivity = act;

        mImageView = (ImageView)mMainActivity.findViewById(R.id.imageView);
    }

    @Override
    public String[] listNotificationInterests() {
        return new String[]
        {
                NotificationNames.START_UP,
                NotificationNames.SCREEN_EVENT,
                NotificationNames.GET_PIC_COMPLETE
        };
    }

    @Override
    public void handleNotification( INotification note ) {
        if (note.getName().equals(NotificationNames.START_UP)) {
            handleStartup(note);
        } else if (note.getName().equals(NotificationNames.GET_PIC_COMPLETE)) {
            ShowPicBean bean = (ShowPicBean)note.getBody();
            mImageView.setImageBitmap(bean.getBitmap());
        }
    }

    private void handleStartup(INotification note) {
        mTxtHello = (TextView)mMainActivity.findViewById(R.id.txtHello);
        StartupBean bean = (StartupBean)note.getBody();
        mTxtHello.setText(bean.getStartupCmdLine());

        ShowPicProxy proxy = (ShowPicProxy)MyAppFacade.getInstance().retrieveProxy(ShowPicProxy.NAME);
        proxy.setArrPicUrl(new String[]{
                "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1303839292,2141065752&fm=80",
                "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1612847655,2289508866&fm=58",
                "http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=4bace83ab3de9c82b268f1dd0de8eb6f/4bed2e738bd4b31c5ef598b887d6277f9f2ff8ec.jpg",
                "http://b.hiphotos.baidu.com/baike/w%3D268/sign=db126e6e918fa0ec7fc7630b1e96594a/d62a6059252dd42a91f6adf6033b5bb5c8eab8f4.jpg",
                "http://d.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=588dec643912b31bd361c57be7715d1f/ac345982b2b7d0a227f474becbef76094b369ad7.jpg",
                "http://www.ibm.com/developerworks/cn/java/j-lo-puremvc/origin_image001.jpg"
        });

        ShowPicBean tmp = new ShowPicBean();
        tmp.setStrPicUrl(proxy.getArrPicUrl()[5]);
        tmp.setHandler(new Handler());
        sendNotification(GetHttpPicCommand.NAME, tmp);
    }

    public void gotoLoginAct() {
        GotoActBean bean = new GotoActBean();
        bean.setFromAct(mMainActivity);
        bean.setToActCls(LoginActivity.class);

        sendNotification(GotoActCommand.NAME, bean);
    }
}
