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
import com.yuanchuangli.mreader.utils.LogUtils;

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

    /**
     * 获取精选文档的逻辑
     *
     * @param page
     */
    @Override
    public void getSelectedDoc(final int page) {
        iSelectedDocFragment.showProgressDialog();
        userBiz.getSelectedDoc(new User(), new IOngetDocListener() {
            @Override
            public void getDocSuccess(User user, final ArrayList<DocBean> docList) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iSelectedDocFragment.hideProgressDialog();
                        iSelectedDocFragment.updateList(docList);
                        mCacheUtil.put("doc" + page, docList);
                    }
                });
            }

            @Override
            public void getDocFailed(final int code) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iSelectedDocFragment.hideProgressDialog();
                        iSelectedDocFragment.showError("错误码" + code);
                    }
                });

            }
        });
    }

    /**
     * 从缓存中拿到数据
     *
     * @param page
     */
    @Override
    public void getSelectedDocFromCache(int page) {
        if (mCacheUtil.getAsObject("doc" + page) != null && mCacheUtil.getAsObject("doc" + page).toString().length() != 0) {
            LogUtils.i("缓存", mCacheUtil.getAsObject("doc" + page).toString());
            ArrayList<DocBean> docBeanList = (ArrayList<DocBean>) mCacheUtil.getAsObject("doc" + page);
            iSelectedDocFragment.updateList(docBeanList);
        }
    }
}
