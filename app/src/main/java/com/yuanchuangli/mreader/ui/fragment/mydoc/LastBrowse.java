package com.yuanchuangli.mreader.ui.fragment.mydoc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.ui.adapter.DocStoreAdapter;
import com.yuanchuangli.mreader.ui.myview.DividerItemDecoration;
import com.yuanchuangli.mreader.utils.LogUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Blank
 * @description LastBrowse 浏览记录或者说收藏记录
 * @time 2016/12/28 15:27
 */

public class LastBrowse extends Fragment {
    private List<DocBean> docStoreList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recentbrowse, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intView(view);
    }

    private void intView(View view) {
        initData();
        RecyclerView recycleView = (RecyclerView) view.findViewById(R.id.rv_docstore);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        DocStoreAdapter docStoreAdapter = new DocStoreAdapter(docStoreList);
        recycleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recycleView.setAdapter(docStoreAdapter);
    }

    private void initData() {
        List<DocBean> docSave = DataSupport.findAll(DocBean.class);
        for (DocBean doc : docSave
                ) {
            LogUtils.i("LAST", doc.getTitle());
            docStoreList.add(doc);
        }
    }
}