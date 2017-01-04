package com.yuanchuangli.mreader.model.bean;

/**
 * @author Blank
 * @description UpdateItem  更新的bean类
 * @time 2016/12/30 14:08
 */

public class UpdateItem {

    private int versionCode;//版本号
    private String versionName;//版本名
    private String downloadUrl;//更新地址
    private String releaseNote;//更新日志

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getReleaseNote() {
        return releaseNote;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }


}
