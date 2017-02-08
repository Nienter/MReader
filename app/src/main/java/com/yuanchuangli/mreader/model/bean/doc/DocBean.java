package com.yuanchuangli.mreader.model.bean.doc;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 文档的实体类
 * Created by Blank on 2016/11/22 17:47
 */

public class DocBean extends DataSupport implements Serializable {
    private String updateTime;//更新日期

    private String docId;//文档id

    private String needCoin;//需要金币

    private String title;//文档标题

    private String litpic;//文档缩略图

    private String pageNumber;//文档页数

    private String click;//点击率或下载率

    private String previewPath;//预览路径

    private String downloadLink;//下载链接

    public DocBean() {
    }


    public DocBean(String updateTime, String needCoin, String title, String litpic, String click, String previewPath, String downloadLink) {
        this.updateTime = updateTime;
        this.needCoin = needCoin;
        this.title = title;
        this.litpic = litpic;
        this.click = click;
        this.previewPath = previewPath;
        this.downloadLink = downloadLink;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getNeedCoin() {
        return needCoin;
    }

    public void setNeedCoin(String needMoney) {
        this.needCoin = needMoney;
    }

    public String getdocId() {
        return docId;
    }

    public void setdocId(String docId) {
        this.docId = docId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getPreviewPath() {
        return previewPath;
    }

    public void setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    @Override
    public String toString() {
        return "DocBean{" +
                "updateTime='" + updateTime + '\'' +
                ", docId='" + docId + '\'' +
                ", needCoin='" + needCoin + '\'' +
                ", title='" + title + '\'' +
                ", litpic='" + litpic + '\'' +
                ", pageNumber='" + pageNumber + '\'' +
                ", click='" + click + '\'' +
                ", previewPath='" + previewPath + '\'' +
                ", downloadLink='" + downloadLink + '\'' +
                '}';
    }
}
