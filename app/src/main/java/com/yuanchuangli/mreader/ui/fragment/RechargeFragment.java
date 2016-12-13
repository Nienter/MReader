package com.yuanchuangli.mreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuanchuangli.mreader.R;

/**
 * Created by Chenyc on 15/6/27.
 */
public class RechargeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private String[] myDataset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, null);
        myDataset = new String[]{"RecycleView",
                "TextInputLayout", "CardView", "AppBar & TabLayout", "Bottom Tab"
        };

        return view;
    }


}
