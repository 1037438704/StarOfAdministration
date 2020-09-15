package com.lawe.starofadministration.bean;

/**
 * author : fuke
 * date : 2020/5/15 10:47
 * description : 登录协议
 */
public class LoginWebBean {


    /**
     * code : 0
     * msg : success
     * xy : {"private_agreement_url":"http://60.205.202.177:8080/zw/xy/ys.html","service_agreement_url":"http://60.205.202.177:8080/zw/xy/fw.html","user_agreement_url":"http://60.205.202.177:8080/zw/xy/yh.html"}
     */

    private int code;
    private String msg;
    private XyBean xy;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public XyBean getXy() {
        return xy;
    }

    public void setXy(XyBean xy) {
        this.xy = xy;
    }

    public static class XyBean {
        /**
         * private_agreement_url : http://60.205.202.177:8080/zw/xy/ys.html
         * service_agreement_url : http://60.205.202.177:8080/zw/xy/fw.html
         * user_agreement_url : http://60.205.202.177:8080/zw/xy/yh.html
         */

        private String private_agreement_url;
        private String service_agreement_url;
        private String user_agreement_url;

        public String getPrivate_agreement_url() {
            return private_agreement_url;
        }

        public void setPrivate_agreement_url(String private_agreement_url) {
            this.private_agreement_url = private_agreement_url;
        }

        public String getService_agreement_url() {
            return service_agreement_url;
        }

        public void setService_agreement_url(String service_agreement_url) {
            this.service_agreement_url = service_agreement_url;
        }

        public String getUser_agreement_url() {
            return user_agreement_url;
        }

        public void setUser_agreement_url(String user_agreement_url) {
            this.user_agreement_url = user_agreement_url;
        }
    }
}
