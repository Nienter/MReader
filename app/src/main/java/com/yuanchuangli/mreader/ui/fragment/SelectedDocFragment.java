package com.yuanchuangli.mreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.presenter.impl.SelectedDocPresenter;
import com.yuanchuangli.mreader.ui.adapter.DocAdapter;
import com.yuanchuangli.mreader.ui.view.ISelectedDocFragment;

import java.util.ArrayList;

/**
 * @author Blank
 * @description SelectedDocFragment
 * @time 2016/12/13 16:30
 */
public class SelectedDocFragment extends BaseFragment implements ISelectedDocFragment, SwipeRefreshLayout.OnRefreshListener {
    private ProgressBar progressBar;
    private RecyclerView swipeTarget;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean loading = false;
    private DocAdapter docAdapter;
    private ArrayList<DocBean> mDocBeanList = new ArrayList<>();
    private SelectedDocPresenter selectedDocPresenter;
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
        initView(view);
    }

    /**
     * 初始化精选文档界面
     */
    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToLoadLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeTarget = (RecyclerView) view.findViewById(R.id.swipe_target);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        progressBar = (ProgressBar) view.findViewById(R.id.id_fragment_progressBar);
        swipeTarget.setLayoutManager(mLinearLayoutManager);
        swipeTarget.setHasFixedSize(true);
        swipeTarget.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //向下滚动
                {
                    visibleItemCount = mLinearLayoutManager.getChildCount();
                    totalItemCount = mLinearLayoutManager.getItemCount();
                    psatVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();

                    if (!loading && (visibleItemCount + psatVisiblesItems) >= totalItemCount) {
                        loading = true;
                        onLoadMore();
                    }
                }
            }
        });
        docAdapter = new DocAdapter(mDocBeanList, getActivity());
        swipeTarget.setAdapter(docAdapter);
        selectedDocPresenter.getSelectedDocFromCache(1);
        onRefresh();
    }

    /**
     * 加载更多
     */
    public void onLoadMore() {
        selectedDocPresenter.getSelectedDoc(currentPage);
    }

    private void initDoc() {
        selectedDocPresenter = new SelectedDocPresenter(this, getActivity());
    }

    @Override
    public void updateList(ArrayList<DocBean> docBeans) {
        currentPage++;
        mDocBeanList.addAll(docBeans);
        docAdapter.notifyDataSetChanged();
    }

    /**
     * 不为空，让其显示出来
     */
    @Override
    public void showProgressDialog() {
        if (progressBar == null)
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
        if (swipeTarget != null) {
            selectedDocPresenter.getSelectedDocFromCache(currentPage);
            Snackbar.make(swipeTarget, "网络通信错误，请您检查网络，您也可以拨打电话" + error, Snackbar.LENGTH_SHORT).setAction("重试", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedDocPresenter.getSelectedDoc(currentPage);
                }
            }).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        mDocBeanList.clear();
        docAdapter.notifyDataSetChanged();
        selectedDocPresenter.getSelectedDoc(currentPage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
