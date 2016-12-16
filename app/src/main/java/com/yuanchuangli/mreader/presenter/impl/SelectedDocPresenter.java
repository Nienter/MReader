package com.yuanchuangli.mreader.presenter.impl;

import android.content.Context;
import android.os.Handler;

import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.model.bean.user.User;
import com.yuanchuangli.mreader.model.biz.User.IOngetDocListener;
import com.yuanchuangli.mreader.model.biz.User.UserBiz;
import com.yuanchuangli.mreader.presenter.ISelectedDocPresenter;
import com.yuanchuangli.mreader.ui.view.ISelectedDocFragment;
import com.yuanchuangli.mreader.utils.CacheUtil;

import java.util.ArrayList;

/**
 * Created by Blank on 2016/12/16 15:53
 */

public class SelectedDocPresenter implements ISelectedDocPresenter {
    private UserBiz userBiz;
    private ISelectedDocFragment iSelectedDocFragment;
    private Handler mHandler = new Handler();
    private CacheUtil mCacheUtil;

    public SelectedDocPresenter(ISelectedDocFragment iSelectedDocFragment, Context context) {
        if (iSelectedDocFragment == null)
            throw new IllegalArgumentException("iSelectedDocFragement must not be null");
        this.iSelectedDocFragment = iSelectedDocFragment;
        userBiz = new UserBiz();
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getSelectedDoc(int page) {
        userBiz.getSelectedDoc(new User(), new IOngetDocListener() {
            @Override
            public void getDocSuccess(User user, final ArrayList<DocBean> docList) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iSelectedDocFragment.hideProgressDialog();
                        iSelectedDocFragment.updateList(docList);
                    }
                });
            }

            @Override
            public void getDocFailed(int code) {

            }
        });
    }

    @Override
    public void getSelectedDocFromCache(int page) {

    }
}
