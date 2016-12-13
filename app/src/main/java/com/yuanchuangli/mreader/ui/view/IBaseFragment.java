package com.yuanchuangli.mreader.ui.view;

/**
 * @author Blank
 * @description IBaseFragment
 * @time 2016/12/13 13:55
 */

public interface IBaseFragment {
    void showProgressDialog();

    void hideProgressDialog();

    void showError(String error);
}
