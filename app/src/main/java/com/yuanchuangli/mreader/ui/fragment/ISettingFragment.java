package com.yuanchuangli.mreader.ui.fragment;

import com.yuanchuangli.mreader.model.bean.UpdateItem;

/**
 * @author Blank
 * @description ISettingFragment
 * @time 2017/1/4 16:26
 */

public interface ISettingFragment {
    void showError(String error);

    void showUpdateDialog(UpdateItem updateItem);

    void showNoUpdate();
}
