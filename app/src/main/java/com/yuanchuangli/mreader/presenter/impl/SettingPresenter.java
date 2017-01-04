package com.yuanchuangli.mreader.presenter.impl;

import com.yuanchuangli.mreader.presenter.ISettingPresenter;
import com.yuanchuangli.mreader.ui.fragment.ISettingFragment;

/**
 * @author Blank
 * @description SettingPresenter
 * @time 2017/1/4 16:10
 */

public class SettingPresenter implements ISettingPresenter {
    private ISettingFragment mSettingFragment;

    public SettingPresenter(ISettingFragment iSettingFragment) {
        this.mSettingFragment = iSettingFragment;

    }

    @Override
    public void checkUpdate() {

    }
}
