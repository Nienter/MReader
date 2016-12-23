package com.yuanchuangli.mreader.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
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
import com.yuanchuangli.mreader.presenter.impl.ClickToPreviewPresenter;
import com.yuanchuangli.mreader.ui.activity.DocInfoActivity;
import com.yuanchuangli.mreader.ui.view.IDocAdapterView;
import com.yuanchuangli.mreader.utils.DateUtils;

import java.util.List;

/**
 * Created by Blank on 2016/12/13 15:51
 */

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.ViewHolder> implements IDocAdapterView {
    private Context mContext;
    private ClickToPreviewPresenter clickToPreviewPresenter = new ClickToPreviewPresenter(this);
    private List<DocBean> mDocList;


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
        Glide.with(mContext).load(doc.getLitpic()).into(holder.doc_Image);
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
                                break;
                            case R.id.pop_download:
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
        final String docId = doc.getId();
//        new Thread() {
//            @Override
//            public void run() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("docid", docId);
//                map.put("token", SharedPreferenceUtil.getUser(BaseApplication.getContext()).getString("token", null));
//                try {
//                    URL url = new URL(ServerInterface_GET.REQUREST_PATH_DOC_PROVIEW);
//                    String content = JSONParse_PHP.getDocInfo(HttpUtil.sendGet(url, map));
//                    mContext.startActivity(new Intent(mContext, DocInfoActivity.class).putExtra("url", content).putExtra("title", title));
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
        clickToPreviewPresenter.ToDocPreview(doc);
    }

    /**
     * 跳转到界面
     */
    @Override
    public void ToDocInfoACtivity(DocBean docBean) {
        mContext.startActivity(new Intent(mContext, DocInfoActivity.class).putExtra("url", docBean.getPreviewPath()).putExtra("title", docBean.getTitle()));
    }
}
