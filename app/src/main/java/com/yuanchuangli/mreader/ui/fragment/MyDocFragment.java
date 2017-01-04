package com.yuanchuangli.mreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.ui.fragment.mydoc.DocRecords;
import com.yuanchuangli.mreader.ui.fragment.mydoc.DownloadRecords;
import com.yuanchuangli.mreader.ui.fragment.mydoc.LastBrowse;
import com.yuanchuangli.mreader.ui.myview.OnSegmentClickListener;
import com.yuanchuangli.mreader.ui.myview.SegmentView;

/**
 * @author Blabk
 * @description MyDocFragment 我的文档，其中又嵌套了3个界面
 * @time 2016/12/26 17:27
 */
public class MyDocFragment extends Fragment {
    private LastBrowse lastBrowse;
    private DownloadRecords downloadRecords;
    private DocRecords docRecords;
    private SegmentView segmentView;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mydoc, container, false);
        segmentView = (SegmentView) view.findViewById(R.id.segment);
        fragmentManager = getChildFragmentManager();
        setTabSelection(0);
        segmentView.setOnsegmentlistenerclicker(new OnSegmentClickListener() {
            @Override
            public void setOnsegment(View v, int position) {
                setTabSelection(position);
            }
        });
        return view;
    }

    private void setTabSelection(int position) {
        FragmentTransaction trans = fragmentManager.beginTransaction();
        hideFragment(trans);
        switch (position) {
            case 0:
//                if (lastBrowse == null) {
//                    lastBrowse = new LastBrowse();
//                    trans.replace(R.id.contentsegment, new LastBrowse());
//                } else {
//                    trans.show(lastBrowse);
//                }
//                trans.commit();
                trans.replace(R.id.contentsegment, new LastBrowse()).commit();
                break;
            case 1:
//                if (downloadRecords == null) {
//                    downloadRecords = new DownloadRecords();
//                    //trans.add(R.id.contentsegment, new DownloadRecords());
//                    trans.replace(R.id.contentsegment, new DownloadRecords());
//                } else {
//                    trans.show(downloadRecords);
//                }
//                trans.commit();
                trans.replace(R.id.contentsegment, new DownloadRecords()).commit();
                break;
            case 2:
//                if (docRecords == null) {
//                    docRecords = new DocRecords();
//                    trans.replace(R.id.contentsegment, new DocRecords());
//                } else {
//                    trans.show(docRecords);
//                }
//                trans.commit();
                trans.replace(R.id.contentsegment, new DocRecords()).commit();
                break;
        }
    }

    private void hideFragment(FragmentTransaction trans) {
        if (lastBrowse != null) {
            trans.hide(lastBrowse);
        }
        if (lastBrowse != null) {
            trans.hide(lastBrowse);
        }
        if (lastBrowse != null) {
            trans.hide(lastBrowse);
        }
    }
}



