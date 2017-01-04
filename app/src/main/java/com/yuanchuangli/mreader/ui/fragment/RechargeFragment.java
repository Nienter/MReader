package com.yuanchuangli.mreader.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.ui.activity.RechargeRecordsActivity;

/**
 * @author Blank
 * @description RechargeFragment
 * @time 2017/1/4 16:27
 */
public class RechargeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private String[] myDataset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, container, false);
        setHasOptionsMenu(true);//显示menu必须加
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_recharge, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_rechargerecords:
                startActivity(new Intent(getActivity(), RechargeRecordsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
