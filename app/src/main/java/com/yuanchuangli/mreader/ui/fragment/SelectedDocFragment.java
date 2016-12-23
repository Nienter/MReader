package com.yuanchuangli.mreader.ui.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.api.HttpUtil;
import com.yuanchuangli.mreader.api.ServerInterface_GET;
import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.parse.JSONParse_PHP;
import com.yuanchuangli.mreader.presenter.impl.SelectedDocPresenter;
import com.yuanchuangli.mreader.ui.adapter.DocAdapter;
import com.yuanchuangli.mreader.ui.view.ISelectedDocFragment;
import com.yuanchuangli.mreader.utils.LogUtils;
import com.yuanchuangli.mreader.utils.SharedPreferenceUtil;
import com.yuanchuangli.mreader.utils.ToastUtils;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Context mContext;
    private Handler mHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, null);
        setHasOptionsMenu(true);//显示menu必须加
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

    /**
     * 更新列表
     *
     * @param docBeans
     */
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
            Snackbar.make(swipeTarget, "网络通信错误，请您检查网络，错误码" + error, Snackbar.LENGTH_SHORT).setAction("重试", new View.OnClickListener() {
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                ToastUtils.showToast(getContext(), "我是搜索");
                new Thread() {
                    @Override
                    public void run() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("title", query);
                        map.put("token", SharedPreferenceUtil.getUser(BaseApplication.getContext()).getString("token", null));
                        try {
                            URL url = new URL(ServerInterface_GET.REQUET_PATH_DOC_SEARCH);
                            final ArrayList<DocBean> docBeen = JSONParse_PHP.getDocList(HttpUtil.sendGet(url, map));
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    LogUtils.i("docBean", docBeen.toString());
                                    mDocBeanList.clear();
                                    updateList(docBeen);
                                }
                            });

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });//为搜索框设置监听事件

        searchView.setQueryHint("请输入文档名称");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                LogUtils.i("return", "到了de2");
                break;
            case R.id.action_settings:
                LogUtils.i("return", "到了de");
                break;
            case android.R.id.home:
                selectedDocPresenter.getSelectedDocFromCache(1);
                LogUtils.i("return", "到了");
                break;
        }

        return super.onOptionsItemSelected(item);

    }

}
