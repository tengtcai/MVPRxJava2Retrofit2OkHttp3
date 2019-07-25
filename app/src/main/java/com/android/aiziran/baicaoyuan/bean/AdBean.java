package com.android.aiziran.baicaoyuan.bean;

public class AdBean {
    private String id;
    /**
     * 指定广告的类型
     * 0表示不跳转
     * 1表示app内部打开url
     * 2表示app外部打开url
     * 3表示打开商品详情页
     */
    private int type;
    private String img;//广告图片
    private String url;  //三方链接 或者其他的商品id
    /**
     * wid 显示的位置
     * 1表示启动页广告
     * 2表示首页banner
     * 3表示首页广告
     * 4表示抢购首页的广告
     */
    private int wid;
    private int isDelete;//是否删除广告 1表示删除（不显示），2未删除（显示）
    private String color; //开屏广告的背景颜色

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
