package com.yuanchuangli.mreader.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.Recharge;
import com.yuanchuangli.mreader.utils.DateUtils;

import java.util.List;

/**
 * @author Blank
 * @description RechargeRecordsAdapter
 * @time 2017/1/4 16:23
 */

public class RechargeRecordsAdapter extends RecyclerView.Adapter<RechargeRecordsAdapter.ViewHolder> {
    List<Recharge> mRechargeList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_buyId, tv_amount, tv_time, tv_product, tv_status;
        CardView cv_recharge;

        public ViewHolder(View itemView) {
            super(itemView);
            cv_recharge = (CardView) itemView.findViewById(R.id.cv_recharge);
            tv_buyId = (TextView) itemView.findViewById(R.id.id_buyid);
            tv_amount = (TextView) itemView.findViewById(R.id.id_amount);
            tv_product = (TextView) itemView.findViewById(R.id.id_product);
            tv_time = (TextView) itemView.findViewById(R.id.id_rechargetime);
            tv_status = (TextView) itemView.findViewById(R.id.id_rechargestatus);
        }
    }

    public RechargeRecordsAdapter(List<Recharge> rechargeList) {
        mRechargeList = rechargeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rechargerecords_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recharge recharge = mRechargeList.get(position);
        holder.tv_buyId.setText(recharge.getBuyId());
        holder.tv_product.setText(recharge.getRechargeProduct());
        holder.tv_time.setText(DateUtils.timeStamp2Date(recharge.getRechargeTime(), "yyyy.MM.dd"));
        holder.tv_amount.setText(recharge.getRechargeMoney());
        if (recharge.getRechargeSta() == 0) {
            holder.tv_status.setText("成功");
        }
    }

    @Override
    public int getItemCount() {
        return mRechargeList.size();
    }
}
