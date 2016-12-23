package com.yuanchuangli.mreader.model.biz.User;

import com.yuanchuangli.mreader.model.bean.doc.DocBean;

/**
 * Created by Blank on 2016/12/23 14:41
 */

public interface IClickListener {
    void Success(DocBean docBean);

    void Fail();
}
