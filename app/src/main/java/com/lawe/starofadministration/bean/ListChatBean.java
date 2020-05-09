package com.lawe.starofadministration.bean;

/**
 * author : fuke
 * date : 2020/5/9 13:59
 * description : 描述
 */
public class ListChatBean {

    boolean display;
    String title;

    public ListChatBean(boolean display, String title) {
        this.display = display;
        this.title = title;
    }

    public boolean getDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
