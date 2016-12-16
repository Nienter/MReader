package com.yuanchuangli.mreader.model.biz.User;

import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.model.bean.user.User;

import java.util.ArrayList;

/**
 * Created by Blank on 2016/12/16 16:08
 */

public interface IOngetDocListener {
    void getDocSuccess(User user, ArrayList<DocBean> docList);

    void getDocFailed(int code);
}
