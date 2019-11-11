package com.bkw.wangyiproject.entity;

public class GirlBean {

    private String style;
    private String like;
    private int icon;

    public GirlBean() {
    }

    public GirlBean(String style, String like, int icon) {
        this.style = style;
        this.like = like;
        this.icon = icon;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
