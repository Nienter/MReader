package com.yuanchuangli.mreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.utils.BackHandledFragment;

/**
 * @author Blank
 * @description MyMessageFragment 我的消息的Fragment
 * @time 2017/1/4 16:26
 */
public class MyMessageFragment extends BackHandledFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, null);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public String getTagText() {
        return null;
    }

    @Override
    public boolean onBackPressed() {


        return false;
    }
}
