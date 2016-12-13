package com.yuanchuangli.mreader.model.BroadcastReceivers;

/**
 * @author Blank
 * @description 网络变化的接口
 * @time 2016/12/12 18:10
 */

public interface INetworkChange {
    void networkConn();

    void networkError();
}
