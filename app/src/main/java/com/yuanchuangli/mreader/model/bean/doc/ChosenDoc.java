package com.yuanchuangli.mreader.model.bean.doc;

import java.util.List;

/**
 * 精选文档列表的实体类
 * Created by Blank on 2016/11/22 17:56
 */

public class ChosenDoc {

    private List<DocBean> docList;//精选文档列表

    public List<DocBean> getDocList() {
        return docList;
    }

    public void setDocList(List<DocBean> docList) {
        this.docList = docList;
    }
}
