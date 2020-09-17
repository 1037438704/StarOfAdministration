package com.lawe.starofadministration.bean;

/**
 * author : fuke
 * date : 2020/9/17 14:47
 * description : 描述
 */
public class EventFactionBean {


    //第一个页面的
    private String qNumber;  //字号数值
    private String quasiNumber;  //拟编号
    private String docTitle;  //公文标题
    private String docContext;  //公文主体


    //第二个页面
    private String publicProperty; //公开
    private String docTheme; //公文主题
    private String docType;  //公文类

    //判断是哪个页面
    public int type;


    public String getqNumber() {
        return qNumber;
    }

    public void setqNumber(String qNumber) {
        this.qNumber = qNumber;
    }

    public String getQuasiNumber() {
        return quasiNumber;
    }

    public void setQuasiNumber(String quasiNumber) {
        this.quasiNumber = quasiNumber;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocContext() {
        return docContext;
    }

    public void setDocContext(String docContext) {
        this.docContext = docContext;
    }

    public String getPublicProperty() {
        return publicProperty;
    }

    public void setPublicProperty(String publicProperty) {
        this.publicProperty = publicProperty;
    }

    public String getDocTheme() {
        return docTheme;
    }

    public void setDocTheme(String docTheme) {
        this.docTheme = docTheme;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }
    //打印一个toString

    @Override
    public String toString() {
        return "EventFactionBean{" +
                "qNumber='" + qNumber + '\'' +
                ", quasiNumber='" + quasiNumber + '\'' +
                ", docTitle='" + docTitle + '\'' +
                ", docContext='" + docContext + '\'' +
                ", publicProperty='" + publicProperty + '\'' +
                ", docTheme='" + docTheme + '\'' +
                ", docType='" + docType + '\'' +
                ", type=" + type +
                '}';
    }
}
