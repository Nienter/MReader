package com.yuanchuangli.mreader.model.bean;

/**
 * @author Blank
 * @description recharge
 * @time 2016/12/29 17:22
 */

public class Recharge {
    private String buyId;//商品ID
    private String rechargeMoney;//充值金额
    private String rechargeTime;//充值时间
    private String rechargeProduct;//充值渠道
    private int rechargeSta;//充值状态

    public int getRechargeSta() {
        return rechargeSta;
    }

    public void setRechargeSta(int rechargeSta) {
        this.rechargeSta = rechargeSta;
    }


    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public String getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(String rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(String rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public String getRechargeProduct() {
        return rechargeProduct;
    }

    public void setRechargeProduct(String rechargeProduct) {
        this.rechargeProduct = rechargeProduct;
    }
}
