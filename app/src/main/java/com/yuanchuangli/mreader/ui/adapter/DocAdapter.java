package com.yuanchuangli.mreader.ui.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.model.biz.User.DownloadService;
import com.yuanchuangli.mreader.presenter.impl.ClickToPreviewPresenter;
import com.yuanchuangli.mreader.ui.activity.DocInfoActivity;
import com.yuanchuangli.mreader.ui.view.IDocAdapterView;
import com.yuanchuangli.mreader.utils.DateUtils;
import com.yuanchuangli.mreader.utils.LogUtils;
import com.yuanchuangli.mreader.utils.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Blank on 2016/12/13 15:51
 */

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.ViewHolder> implements IDocAdapterView {
    private Context mContext;
    private ClickToPreviewPresenter clickToPreviewPresenter = new ClickToPreviewPresenter(this);

    private List<DocBean> mDocList;
    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv_doc;
        ImageView doc_Image;
        TextView doc_title, doc_updateTime, doc_needCoin, doc_click;
        Button btn_doc;

        public ViewHolder(View itemView) {
            super(itemView);
            doc_Image = (ImageView) itemView.findViewById(R.id.img_doc);
            cv_doc = (CardView) itemView.findViewById(R.id.cv_selecteddoc);
            doc_click = (TextView) itemView.findViewById(R.id.times_download);
            doc_title = (TextView) itemView.findViewById(R.id.tv_title);
            doc_updateTime = (TextView) itemView.findViewById(R.id.tv_time);
            doc_needCoin = (TextView) itemView.findViewById(R.id.id_coin);
            btn_doc = (Button) itemView.findViewById(R.id.btn_doc);
        }
    }

    public DocAdapter() {
    }

    ;

    public DocAdapter(List<DocBean> DocList, Context context) {
        mDocList = DocList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.selecteddoc_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mDocList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DocBean doc = mDocList.get(position);
        holder.doc_title.setText(doc.getTitle());
        holder.doc_click.setText(doc.getClick() + "次下载");
        holder.doc_needCoin.setText(doc.getNeedCoin());
        holder.doc_updateTime.setText(DateUtils.timeStamp2Date(doc.getUpdateTime(), "yyyy.MM.dd"));
        Intent intent = new Intent(mContext, DownloadService.class);
        mContext.startService(intent);
        mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (TextUtils.isEmpty(doc.getLitpic()) || !doc.getLitpic().contains("http")) {
            LogUtils.i("无图", "真的" + TextUtils.isEmpty(doc.getLitpic()));
            Glide.with(mContext).load(R.drawable.ic_nolitpic).into(holder.doc_Image);
        } else {
            Glide.with(mContext).load(doc.getLitpic()).into(holder.doc_Image);
        }
        holder.cv_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.doc_title.setTextColor(Color.GRAY);
                DocAdapter.this.show(doc, holder.doc_title.getText().toString());
            }
        });
        holder.btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.btn_doc);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.pop_fav:
                                if (DataSupport.findBySQL("select * from DocBean where docId = ?", doc.getdocId()).getCount() == 0) {
                                    doc.save();
                                    LogUtils.i("SAVE", "已存储");
                                }
                                break;
                            case R.id.pop_download:
                                //String url = "http://userup0001.book118.com/uploads/userup/130/12Z05NW-4452.doc";
                                ClickToPreviewPresenter.getDownloadLink(doc);
                                //String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }

    /**
     * 跳转到下一个界面并携带数据
     *
     * @param doc
     * @param title
     */
    private void show(DocBean doc, final String title) {
        final String docId = doc.getdocId();

        ClickToPreviewPresenter.ToDocPreview(doc);
    }

    /**
     * 跳转到界面
     */
    @Override
    public void ToDocInfoACtivity(DocBean docBean) {
        mContext.startActivity(new Intent(mContext, DocInfoActivity.class).putExtra("url", docBean.getPreviewPath()).putExtra("title", docBean.getTitle()));
    }

    @Override
    public void showError(int code) {
        switch (code) {
            case 2:
                ToastUtils.showToast(mContext, "您没有该买此文档");
                AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
                builder.setTitle("文档提示");
                builder.setMessage("此文档您还未购买，是否购买？");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", null);
                builder.show();
                break;
            default:
                ToastUtils.showToast(mContext, "对不起，此文档现在不可预览");
        }

    }

    @Override
    public String getDownloadLink(DocBean docBean) {

        return docBean.getDownloadLink();
    }

    @Override
    public void startDownload(String url) {
        downloadBinder.startDownload(url);

    }

    @Override
    public void onDestory() {
        mContext.unbindService(connection);
    }


}

