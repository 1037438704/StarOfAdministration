package com.lawe.starofadministration.bean;

import java.util.List;

/**
 * author : fuke
 * date : 2020/6/18 16:55
 * description : 描述
 */
public class ZhutiFindAllBean {

    /**
     * msg : success
     * code : 0
     * list : [{"id":1,"themeName":"国务院组织机构","parentId":"123"},{"id":100,"themeName":"医药管理","parentId":null},{"id":101,"themeName":"体育","parentId":null},{"id":102,"themeName":"其他","parentId":null},{"id":108,"themeName":"人口与计划生育、妇女儿童工作","parentId":null},{"id":11,"themeName":"综合政务","parentId":null},{"id":113,"themeName":"劳动、人事、监察","parentId":null},{"id":121,"themeName":"公安、安全、司法","parentId":null},{"id":126,"themeName":"民政、扶贫、救灾","parentId":null},{"id":134,"themeName":"民族、宗教","parentId":null},{"id":137,"themeName":"对外事务","parentId":null},{"id":141,"themeName":"港澳台侨工作","parentId":null},{"id":150,"themeName":"国防","parentId":null},{"id":21,"themeName":"国民经济管理国有资产监管","parentId":null},{"id":29,"themeName":"财政、金融、审计","parentId":null},{"id":39,"themeName":"国土资源、能源","parentId":null},{"id":48,"themeName":"农业、林业、水利","parentId":null},{"id":53,"themeName":"工业、交通","parentId":null},{"id":66,"themeName":"商贸、海关、旅游","parentId":null},{"id":73,"themeName":"市场监管、安全生产监管","parentId":null},{"id":80,"themeName":"城乡建设、环境保护","parentId":null},{"id":87,"themeName":"科技、教育","parentId":null},{"id":92,"themeName":"文化、广电、新闻出版","parentId":null},{"id":98,"themeName":"卫生、体育","parentId":null}]
     */

    private String msg;
    private String code;
    private List<ListBean> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * themeName : 国务院组织机构
         * parentId : 123
         */

        private String id;
        private String themeName;
        private String parentId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getThemeName() {
            return themeName;
        }

        public void setThemeName(String themeName) {
            this.themeName = themeName;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }
}
