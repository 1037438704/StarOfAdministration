package com.lawe.starofadministration.bean;

/**
 * author : fuke
 * date : 2020/5/15 10:47
 * description : 登录协议
 */
public class LoginWebBean {

    /**
     * msg : success
     * code : 0
     * userAgreementUrl : http://47.94.15.30:8080/zw/html/xy/useragreement.html
     */

    private String msg;
    private int code;
    private String userAgreementUrl;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUserAgreementUrl() {
        return userAgreementUrl;
    }

    public void setUserAgreementUrl(String userAgreementUrl) {
        this.userAgreementUrl = userAgreementUrl;
    }
}
