package com.lawe.starofadministration.bean;

import java.util.List;

/**
 * author : fuke
 * date : 2020/6/22 15:12
 * description : 公文拟制系统列表
 */
public class FictionListBean {


    /**
     * msg : success
     * code : 0
     * page : {"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"id":"1275353169571074050","quasiNumber":"28nkjyj002202006230001","docTitle":"你你你理解理解","changeType":2,"docType":"批复","publicProperty":"1","docTheme":"国土资源、能源","docAddress":"","timingSendTime":"2020-06-24 10:10:35","signUnit":"","signDoc":0,"spentTime":"","docNumber":"","archivedState":0,"relationId":"564d0520-5abd-4fa5-a427-28f835e2d891","timeStamp":"","modelId":"","remark":"明年","sendTime":"","creatorId":"1245548306401255426","creatorCoId":"1235120679131725825","createTime":"2020-06-23 17:01:05","state":0,"filePath":"","forwardingFile":"","taskId":"","taskName":"","processInstanceId":"","dname":"啦啦啦"},{"id":"1275358009177141250","quasiNumber":"28nkjyj002202006230002","docTitle":"急急急急","changeType":2,"docType":"请示","publicProperty":"2","docTheme":"财政、金融、审计","docAddress":null,"timingSendTime":"2020-06-24 10:10:35","signUnit":null,"signDoc":0,"spentTime":null,"docNumber":null,"archivedState":0,"relationId":"7e7cac8b-4398-4185-9c88-674339702776","timeStamp":null,"modelId":null,"remark":"LOMO","sendTime":null,"creatorId":"1245548306401255426","creatorCoId":"1235120679131725825","createTime":"2020-06-23 17:20:18","state":0,"filePath":null,"forwardingFile":null,"taskId":null,"taskName":null,"processInstanceId":null,"dname":"啦啦啦"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
     */

    private String msg;
    private String code;
    private PageBean page;

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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 2
         * startRow : 1
         * endRow : 2
         * total : 2
         * pages : 1
         * list : [{"id":"1275353169571074050","quasiNumber":"28nkjyj002202006230001","docTitle":"你你你理解理解","changeType":2,"docType":"批复","publicProperty":"1","docTheme":"国土资源、能源","docAddress":"","timingSendTime":"2020-06-24 10:10:35","signUnit":"","signDoc":0,"spentTime":"","docNumber":"","archivedState":0,"relationId":"564d0520-5abd-4fa5-a427-28f835e2d891","timeStamp":"","modelId":"","remark":"明年","sendTime":"","creatorId":"1245548306401255426","creatorCoId":"1235120679131725825","createTime":"2020-06-23 17:01:05","state":0,"filePath":"","forwardingFile":"","taskId":"","taskName":"","processInstanceId":"","dname":"啦啦啦"},{"id":"1275358009177141250","quasiNumber":"28nkjyj002202006230002","docTitle":"急急急急","changeType":2,"docType":"请示","publicProperty":"2","docTheme":"财政、金融、审计","docAddress":null,"timingSendTime":"2020-06-24 10:10:35","signUnit":null,"signDoc":0,"spentTime":null,"docNumber":null,"archivedState":0,"relationId":"7e7cac8b-4398-4185-9c88-674339702776","timeStamp":null,"modelId":null,"remark":"LOMO","sendTime":null,"creatorId":"1245548306401255426","creatorCoId":"1235120679131725825","createTime":"2020-06-23 17:20:18","state":0,"filePath":null,"forwardingFile":null,"taskId":null,"taskName":null,"processInstanceId":null,"dname":"啦啦啦"}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        private String pageNum;
        private String pageSize;
        private String size;
        private String startRow;
        private String endRow;
        private String total;
        private String pages;
        private String prePage;
        private String nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private String navigatePages;
        private String navigateFirstPage;
        private String navigateLastPage;
        private String firstPage;
        private String lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public String getPageNum() {
            return pageNum;
        }

        public void setPageNum(String pageNum) {
            this.pageNum = pageNum;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getStartRow() {
            return startRow;
        }

        public void setStartRow(String startRow) {
            this.startRow = startRow;
        }

        public String getEndRow() {
            return endRow;
        }

        public void setEndRow(String endRow) {
            this.endRow = endRow;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public String getPrePage() {
            return prePage;
        }

        public void setPrePage(String prePage) {
            this.prePage = prePage;
        }

        public String getNextPage() {
            return nextPage;
        }

        public void setNextPage(String nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public String getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(String navigatePages) {
            this.navigatePages = navigatePages;
        }

        public String getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(String navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public String getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(String navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public String getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(String firstPage) {
            this.firstPage = firstPage;
        }

        public String getLastPage() {
            return lastPage;
        }

        public void setLastPage(String lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 1275353169571074050
             * quasiNumber : 28nkjyj002202006230001
             * docTitle : 你你你理解理解
             * changeType : 2
             * docType : 批复
             * publicProperty : 1
             * docTheme : 国土资源、能源
             * docAddress :
             * timingSendTime : 2020-06-24 10:10:35
             * signUnit :
             * signDoc : 0
             * spentTime :
             * docNumber :
             * archivedState : 0
             * relationId : 564d0520-5abd-4fa5-a427-28f835e2d891
             * timeStamp :
             * modelId :
             * remark : 明年
             * sendTime :
             * creatorId : 1245548306401255426
             * creatorCoId : 1235120679131725825
             * createTime : 2020-06-23 17:01:05
             * state : 0
             * filePath :
             * forwardingFile :
             * taskId :
             * taskName :
             * processInstanceId :
             * dname : 啦啦啦
             */

            private String id;
            private String quasiNumber;
            private String docTitle;
            private String changeType;
            private String docType;
            private String publicProperty;
            private String docTheme;
            private String docAddress;
            private String timingSendTime;
            private String signUnit;
            private String signDoc;
            private String spentTime;
            private String docNumber;
            private String archivedState;
            private String relationId;
            private String timeStamp;
            private String modelId;
            private String remark;
            private String sendTime;
            private String creatorId;
            private String creatorCoId;
            private String createTime;
            private String state;
            private String filePath;
            private String forwardingFile;
            private String taskId;
            private String taskName;
            private String processInstanceId;
            private String dname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getChangeType() {
                return changeType;
            }

            public void setChangeType(String changeType) {
                this.changeType = changeType;
            }

            public String getDocType() {
                return docType;
            }

            public void setDocType(String docType) {
                this.docType = docType;
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

            public String getDocAddress() {
                return docAddress;
            }

            public void setDocAddress(String docAddress) {
                this.docAddress = docAddress;
            }

            public String getTimingSendTime() {
                return timingSendTime;
            }

            public void setTimingSendTime(String timingSendTime) {
                this.timingSendTime = timingSendTime;
            }

            public String getSignUnit() {
                return signUnit;
            }

            public void setSignUnit(String signUnit) {
                this.signUnit = signUnit;
            }

            public String getSignDoc() {
                return signDoc;
            }

            public void setSignDoc(String signDoc) {
                this.signDoc = signDoc;
            }

            public String getSpentTime() {
                return spentTime;
            }

            public void setSpentTime(String spentTime) {
                this.spentTime = spentTime;
            }

            public String getDocNumber() {
                return docNumber;
            }

            public void setDocNumber(String docNumber) {
                this.docNumber = docNumber;
            }

            public String getArchivedState() {
                return archivedState;
            }

            public void setArchivedState(String archivedState) {
                this.archivedState = archivedState;
            }

            public String getRelationId() {
                return relationId;
            }

            public void setRelationId(String relationId) {
                this.relationId = relationId;
            }

            public String getTimeStamp() {
                return timeStamp;
            }

            public void setTimeStamp(String timeStamp) {
                this.timeStamp = timeStamp;
            }

            public String getModelId() {
                return modelId;
            }

            public void setModelId(String modelId) {
                this.modelId = modelId;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getSendTime() {
                return sendTime;
            }

            public void setSendTime(String sendTime) {
                this.sendTime = sendTime;
            }

            public String getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(String creatorId) {
                this.creatorId = creatorId;
            }

            public String getCreatorCoId() {
                return creatorCoId;
            }

            public void setCreatorCoId(String creatorCoId) {
                this.creatorCoId = creatorCoId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            public String getForwardingFile() {
                return forwardingFile;
            }

            public void setForwardingFile(String forwardingFile) {
                this.forwardingFile = forwardingFile;
            }

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public String getProcessInstanceId() {
                return processInstanceId;
            }

            public void setProcessInstanceId(String processInstanceId) {
                this.processInstanceId = processInstanceId;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }
        }
    }
}
