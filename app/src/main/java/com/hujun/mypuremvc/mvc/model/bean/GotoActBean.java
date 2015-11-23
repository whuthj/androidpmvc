package com.hujun.mypuremvc.mvc.model.bean;

import android.app.Activity;

public class GotoActBean {
    public Activity getFromAct() {
        return fromAct;
    }

    public void setFromAct(Activity fromAct) {
        this.fromAct = fromAct;
    }

    public Class<?> getToActCls() {
        return toActCls;
    }

    public void setToActCls(Class<?> toActCls) {
        this.toActCls = toActCls;
    }

    public boolean isFinishFrom() {
        return finishFrom;
    }

    public void setFinishFrom(boolean finishFrom) {
        this.finishFrom = finishFrom;
    }

    private Activity fromAct;
    private Class<?> toActCls;
    private boolean finishFrom;
}
