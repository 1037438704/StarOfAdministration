package com.lawe.starofadministration.bean;

import java.util.List;

/**
 * author : fuke
 * date : 2020/6/18 15:12
 * description : 描述
 */
public class ByTypeBean {

    /**
     * code : 0
     * dataDictList : [{"dataKey":"函","dataType":"doc_type","dataValue":"14","id":"274","remark":"公文类型"},{"dataKey":"纪要","dataType":"doc_type","dataValue":"15","id":"275","remark":"公文类型"},{"dataKey":"便函","dataType":"doc_type","dataValue":"16","id":"276","remark":"公文类型"},{"dataKey":"信息","dataType":"doc_type","dataValue":"17","id":"277","remark":"公文类型"}]
     * msg : success
     */

    private String code;
    private String msg;
    private List<DataDictListBean> dataDictList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataDictListBean> getDataDictList() {
        return dataDictList;
    }

    public void setDataDictList(List<DataDictListBean> dataDictList) {
        this.dataDictList = dataDictList;
    }

    public static class DataDictListBean {
        /**
         * dataKey : 函
         * dataType : doc_type
         * dataValue : 14
         * id : 274
         * remark : 公文类型
         */

        private String dataKey;
        private String dataType;
        private String dataValue;
        private String id;
        private String remark;

        public String getDataKey() {
            return dataKey;
        }

        public void setDataKey(String dataKey) {
            this.dataKey = dataKey;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getDataValue() {
            return dataValue;
        }

        public void setDataValue(String dataValue) {
            this.dataValue = dataValue;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
