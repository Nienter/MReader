package com.yuanchuangli.mreader.model.biz.User;

import com.yuanchuangli.mreader.api.ServerInterface_GET;
import com.yuanchuangli.mreader.api.ServerInterface_POST;
import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.model.bean.user.User;
import com.yuanchuangli.mreader.parse.JSONParse_PHP;
import com.yuanchuangli.mreader.utils.SharedPreferenceUtil;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

import java.util.ArrayList;

/**
 * @author Blank
 * @description 处理具体的逻辑   问题：里面的逻辑都是新开启了一个线程，有内存泄漏的危险，后期要用线程池来统一管理
 * @time 2016/11/23 11:26
 */

public class UserBiz implements IUserBiz {
    /**
     * 登录的具体逻辑
     *
     * @param user
     * @param loginListener
     */
    @Override
    public void login(final User user, final IOnloginListener loginListener) {
        new Thread() {
            @Override
            public void run() {
                String json = ServerInterface_POST.checkPass(user);
                int code = JSONParse_PHP.getStatus(json);
                String token = JSONParse_PHP.getToken(json);
                User user = JSONParse_PHP.getUserBaseInfo(json);
                if (loginListener.isCancleLogin()) return;
                switch (code) {
                    case JSONParse_PHP.STATUS_SUCCESS:
                        SharedPreferenceUtil.saveUser(BaseApplication.getContext(), null, null, token);
                        loginListener.loginSuccess(user);
                        break;
                    default:
                        loginListener.loginFailed(code);
                        break;
                }
            }
        }.start();

    }

    /**
     * 获取文档列表的逻辑
     *
     * @param user
     * @param iOngetDocListener
     */
    @Override
    public void getSelectedDoc(final User user, final IOngetDocListener iOngetDocListener) {
        new Thread() {
            @Override
            public void run() {
                String json = ServerInterface_GET.getDocListFromServer();
                int code = JSONParse_PHP.getStatus(json);
                ArrayList<DocBean> docList = JSONParse_PHP.getDocList(json);
                switch (code) {
                    case JSONParse_PHP.STATUS_SUCCESS:
                        iOngetDocListener.getDocSuccess(user, docList);
                        break;
                    case JSONParse_PHP.STATUS_TOKEN_ERROR:
                        iOngetDocListener.getDocFailed(code);
                        break;
                    case JSONParse_PHP.STATUS_SIGN_ERROR:
                        iOngetDocListener.getDocFailed(code);
                        break;
                    default:
                        iOngetDocListener.getDocFailed(code);
                        break;
                }
            }
        }.start();
    }

    /**
     * 用户点击一份文档
     *
     * @param docBean        单个文档
     * @param iClickListener
     */
    @Override
    public void clickItemDoc(final DocBean docBean, final IClickListener iClickListener) {
        new Thread() {
            @Override
            public void run() {
                String json = ServerInterface_GET.getDocInfromServer(docBean);
                int code = JSONParse_PHP.getStatus(json);
                String docPreviewPath = JSONParse_PHP.getDocInfo(json);
                docBean.setPreviewPath(docPreviewPath);
                switch (code) {
                    case JSONParse_PHP.STATUS_SUCCESS:
                        iClickListener.Success(docBean);
                        break;
                    case JSONParse_PHP.SERVER_CONNECTION_ERROR:
                        break;
                    case JSONParse_PHP.STATUS_SIGN_ERROR:
                        break;
                }

            }
        }.start();
    }


}
