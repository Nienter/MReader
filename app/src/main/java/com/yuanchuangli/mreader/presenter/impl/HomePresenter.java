package com.yuanchuangli.mreader.presenter.impl;

import com.yuanchuangli.mreader.model.biz.User.UserBiz;
import com.yuanchuangli.mreader.presenter.IHomePresenter;
import com.yuanchuangli.mreader.ui.view.IHomeView;

/**
 * Created by Blank on 2016/12/15 14:57
 */

public class HomePresenter implements IHomePresenter {
    private IHomeView iHomeView;
    private UserBiz userBiz;

    public HomePresenter(IHomeView iHomeView) {
        if (iHomeView == null) {
            throw new IllegalArgumentException("main must not be null");

        }
        this.iHomeView = iHomeView;
        userBiz = new UserBiz();
    }

    /**
     * 检查更新
     */
    @Override
    public void checkUpdate() {

    }

    /**
     * 用于初始化界面
     */
    @Override
    public void refreshView() {

    }
}
