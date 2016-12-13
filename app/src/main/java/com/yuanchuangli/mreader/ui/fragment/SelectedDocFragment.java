package com.yuanchuangli.mreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.ui.DocAdapter;
import com.yuanchuangli.mreader.ui.view.ISelectedDocFragment;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Blank
 * @description SelectedDocFragment
 * @time 2016/12/13 16:30
 */
public class SelectedDocFragment extends BaseFragment implements ISelectedDocFragment, SwipeRefreshLayout.OnRefreshListener {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = false;
    private DocBean[] docbean = {new DocBean("2014.10.13", "300", "理想主义", "http://swf13.book118.com/litpics//20150409/2129002-55252a01b5a27.jpg", "20")};
    private DocAdapter adapter;
    private ArrayList<DocBean> mDocBeenList = new ArrayList<>();
    private int currentPage = 1;
    private int psatVisiblesItems, visibleItemCount, totalItemCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDoc();
        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        adapter = new DocAdapter(mDocBeenList);
        recyclerView.setAdapter(adapter);
    }

    private void initDoc() {
        mDocBeenList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(docbean.length);
            mDocBeenList.add(docbean[index]);
        }
    }

    @Override
    public void updateList(ArrayList<DocBean> docBeans) {
        currentPage++;
        mDocBeenList.addAll(docBeans);
        adapter.notifyDataSetChanged();
    }

    /**
     * 不为空，让其显示出来
     */
    @Override
    public void showProgressDialog() {
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 不为空，让其显示出来
     */
    @Override
    public void hideProgressDialog() {
        if (swipeRefreshLayout != null) {//不加可能会崩溃
            swipeRefreshLayout.setRefreshing(false);
            loading = false;
        }
        if (progressBar != null)
            progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
