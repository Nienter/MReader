package com.yuanchuangli.mreader.model.bean.doc;

/**
 * 文档的实体类
 * Created by Blank on 2016/11/22 17:47
 */

public class DocBean {
    private String updateTime;//更新日期

    private String id;//文档id

    private String needCoin;//需要金币

    private String title;//文档标题

    private String litpic;//文档缩略图

    private String pageNumber;//文档页数

    private String click;//点击率或下载率

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "chosendoc{" +
                "updateTime='" + updateTime + '\'' +
                ", id='" + id + '\'' +
                ", needCoin='" + needCoin + '\'' +
                ", title='" + title + '\'' +
                ", litpic='" + litpic + '\'' +
                ", pageNumber='" + pageNumber + '\'' +
                ", click='" + click + '\'' +
                '}';
    }
}
