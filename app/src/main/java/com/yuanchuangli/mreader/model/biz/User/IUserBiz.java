package com.yuanchuangli.mreader.model.biz.User;

import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.model.bean.user.User;

/**
 * @author Blank
 * @description IUserBiz 登录操作的接口
 * @time 2016/11/24 18:24
 */

public interface IUserBiz {
    void login(User user, IOnloginListener onloginListener);


    void getSelectedDoc(User user, int page, IOngetDocListener iOngetDocListener);

    void clickItemDoc(DocBean docBean, IClickListener iClickListener);
}
