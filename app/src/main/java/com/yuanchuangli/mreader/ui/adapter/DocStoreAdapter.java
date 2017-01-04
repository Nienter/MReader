package com.yuanchuangli.mreader.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.doc.DocBean;
import com.yuanchuangli.mreader.ui.myview.ItemSlideHelper;
import com.yuanchuangli.mreader.utils.ToastUtils;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

import java.util.List;

/**
 * @author Blank
 * @description DocStoreAdapter文档收藏的适配器
 * @time 2016/12/28 19:14
 */

public class DocStoreAdapter extends RecyclerView.Adapter<DocStoreAdapter.ViewHolder> implements ItemSlideHelper.Callback {


    private List<DocBean> mDocSaveList;
    private RecyclerView mRecyclerView;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView docImage;
        TextView docTitle, docDelete, docBuy;

        public ViewHolder(View view) {
            super(view);
            docImage = (ImageView) view.findViewById(R.id.docstore_image);
            docTitle = (TextView) view.findViewById(R.id.docstore_title);
            docDelete = (TextView) view.findViewById(R.id.tv_delete);
            docBuy = (TextView) view.findViewById(R.id.tv_buy);
        }
    }

    public DocStoreAdapter(List<DocBean> docSaveList) {
        mDocSaveList = docSaveList;
    }

    @Override
    public DocStoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.docstore_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DocStoreAdapter.ViewHolder holder, final int position) {
        final DocBean doc = mDocSaveList.get(position);
        holder.docTitle.setText(doc.getTitle());
        if (doc.getTitle().contains("ppt")) {
            holder.docImage.setImageResource(R.drawable.ic_vector_ppt_48dp);
        } else if (doc.getTitle().contains("doc")) {
            holder.docImage.setImageResource(R.drawable.ic_vector_word_48dp);
        } else {
            holder.docImage.setImageResource(R.drawable.ic_vector_pdf_48dp);
        }
        holder.docDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(BaseApplication.getContext(), "删除");
                //DataSupport.deleteAll("docbean", "docid=?", doc.getdocId());//这边的语句有些问题，应该可以delete一条数据就可以，不用deleteAll，但是没找到
                doc.delete();
                mDocSaveList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDocSaveList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));

    }

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
        if (holder.itemView instanceof LinearLayout) {
            ViewGroup viewGroup = (ViewGroup) holder.itemView;
            if (viewGroup.getChildCount() == 2) {
                return viewGroup.getChildAt(1).getLayoutParams().width;
            }
        }

        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return mRecyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return mRecyclerView.findChildViewUnder(x, y);
    }
}
