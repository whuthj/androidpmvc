package com.hujun.mypuremvc.mvc.view;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hujun.mypuremvc.R;
import com.hujun.mypuremvc.mvc.MyAppFacade;
import com.hujun.mypuremvc.mvc.NotificationNames;
import com.hujun.mypuremvc.mvc.controller.GetHttpPicCommand;
import com.hujun.mypuremvc.mvc.model.ShowPicProxy;
import com.hujun.mypuremvc.mvc.model.bean.ShowPicBean;
import com.hujun.mypuremvc.mvc.view.activity.ShowPicActivity;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

public class ShowPicMediator extends Mediator implements View.OnClickListener {
    public static final String NAME = "ShowPicMediator";

    private ShowPicActivity mMainActivity;
    private ImageView mImageView;
    private Button mBtnPrev;
    private Button mBtnNext;
    private int mPicIndex;
    private int mPicCount;

    public ShowPicMediator(ShowPicActivity act) {
        super(NAME, null);
        this.mMainActivity = act;

        Fragment fragment = mMainActivity.getSupportFragmentManager().findFragmentById(R.id.fragment);
        mImageView = (ImageView)fragment.getView().findViewById(R.id.imageView);
        mBtnPrev = (Button)mMainActivity.findViewById(R.id.btnPrev);
        mBtnNext = (Button)mMainActivity.findViewById(R.id.btnNext);

        mBtnPrev.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        ShowPicProxy proxy = (ShowPicProxy) MyAppFacade.getInstance().retrieveProxy(ShowPicProxy.NAME);
        mPicCount = proxy.getArrPicUrl().length;
    }

    public void showDefaultPic() {
        showPic(mPicIndex);
    }

    private void showPic(int picIndex) {
        ShowPicProxy proxy = (ShowPicProxy) MyAppFacade.getInstance().retrieveProxy(ShowPicProxy.NAME);
        String[] picUrls = proxy.getArrPicUrl();
        int nPicLen = picUrls.length;
        if (picIndex < 0 || picIndex > nPicLen - 1) {
            return;
        }

        ShowPicBean bean = new ShowPicBean();
        bean.setStrPicUrl(picUrls[picIndex]);
        bean.setHandler(new Handler());
        sendNotification(GetHttpPicCommand.NAME, bean);

        mBtnPrev.setEnabled(false);
        mBtnNext.setEnabled(false);
    }

    @Override
    public void handleNotification(INotification notification) {
        if (notification.getName().equals(NotificationNames.GET_PIC_COMPLETE)) {
            ShowPicBean bean = (ShowPicBean)notification.getBody();
            mImageView.setImageBitmap(bean.getBitmap());
            handleBtnEnable();
        }
    }

    @Override
    public String[] listNotificationInterests() {
        return new String[] {
                NotificationNames.GET_PIC_COMPLETE,
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPrev:
                if (mPicIndex > 0) {
                    showPic(--mPicIndex);
                }
                break;
            case R.id.btnNext:
                if (mPicIndex < mPicCount) {
                    showPic(++mPicIndex);
                }
                break;
            default:
                break;
        }
    }

    private void handleBtnEnable() {
        mBtnPrev.setEnabled(true);
        mBtnNext.setEnabled(true);

        if (mPicIndex == 0) {
            mBtnPrev.setEnabled(false);
        }
        if (mPicIndex == mPicCount - 1) {
            mBtnNext.setEnabled(false);
        }
    }
}
