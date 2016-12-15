package com.yuanchuangli.mreader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.doc.DocBean;

import java.util.List;

/**
 * Created by Blank on 2016/12/13 15:51
 */

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.ViewHolder> {
    private Context mContext;

    private List<DocBean> mDocList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv_doc;
        ImageView doc_Image;
        TextView doc_title, doc_updateTime, doc_needCoin, doc_click;
        Button btn_More;

        public ViewHolder(View itemView) {
            super(itemView);
            doc_Image = (ImageView) itemView.findViewById(R.id.img_doc);
            cv_doc = (CardView) itemView.findViewById(R.id.cv_selecteddoc);
            doc_click = (TextView) itemView.findViewById(R.id.times_download);
            doc_title = (TextView) itemView.findViewById(R.id.tv_title);
            doc_updateTime = (TextView) itemView.findViewById(R.id.tv_time);
            doc_needCoin = (TextView) itemView.findViewById(R.id.id_coin);
            btn_More = (Button) itemView.findViewById(R.id.btn_doc);
        }
    }

    public DocAdapter(List<DocBean> DocList) {
        mDocList = DocList;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        DocBean doc = mDocList.get(position);
        holder.doc_title.setText(doc.getTitle());
        Glide.with(mContext).load(doc.getLitpic()).into(holder.doc_Image);
    }

    @Override
    public int getItemCount() {
        return mDocList.size();
    }


}
