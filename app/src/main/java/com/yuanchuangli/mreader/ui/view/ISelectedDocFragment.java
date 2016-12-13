package com.yuanchuangli.mreader.ui.view;

import com.yuanchuangli.mreader.model.bean.doc.DocBean;

import java.util.ArrayList;

/**
 * @author Blank
 * @description ISelectedDocFragment
 * @time 2016/12/13 18:04
 */

public interface ISelectedDocFragment extends IBaseFragment {
    void updateList(ArrayList<DocBean> docBeans);
}
