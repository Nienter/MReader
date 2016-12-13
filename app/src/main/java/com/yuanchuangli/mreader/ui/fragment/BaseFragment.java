package com.yuanchuangli.mreader.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * @author Blank
 * @description BaseFragment
 * @time 2016/12/13 14:00
 */

public class BaseFragment extends Fragment {
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void setSwipeRefreshLayoutColor(SwipeRefreshLayout swipeRefreshLayout) {
        //swipeRefreshLayout.setColorSchemeColors(getActivity().getSharedPreferences(SharePreferenceUtil.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).getInt(SharePreferenceUtil.VIBRANT, ContextCompat.getColor(getActivity(), R.color.colorAccent)));
        swipeRefreshLayout.setColorSchemeColors(android.support.design.R.color.material_grey_900);
    }
}
