package com.yuanchuangli.mreader.presenter.impl;

import android.os.Handler;

import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.model.biz.User.IClickListener;
import com.yuanchuangli.mreader.model.biz.User.UserBiz;
import com.yuanchuangli.mreader.ui.view.IDocAdapterView;

/**
 * Created by Blank on 2016/12/23 15:00
 */

public class ClickToPreviewPresenter {
    private static IDocAdapterView iDocAdapterView;
    private static UserBiz userBiz;
    private static Handler mHandler = new Handler();

    public ClickToPreviewPresenter(IDocAdapterView iDocAdapterView) {
        this.iDocAdapterView = iDocAdapterView;
        userBiz = new UserBiz();
    }

    public static void ToDocPreview(final DocBean docBean) {
        userBiz.clickItemDoc(docBean, new IClickListener() {
            @Override
            public void Success(final DocBean docBean) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iDocAdapterView.ToDocInfoACtivity(docBean);
                    }
                });
            }

            @Override
            public void Fail() {

            }
        });
    }


}
