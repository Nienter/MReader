package com.yuanchuangli.mreader.model.bean.user;

import java.io.Serializable;

/**
 * User 实体类
 * Created by Blank on 2016/11/21 15:36
 */

public class User implements BaseUser, Serializable {
    private String username;
    private String password;
    private String coin;
    private String imagePath;

    public User() {
    }


    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getCoin() {
        return coin;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "coin='" + coin + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
