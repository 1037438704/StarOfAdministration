package com.lawe.starofadministration.bean;

import java.util.List;

/**
 * author :
 * date : 2020/4/13 16:17
 * description :
 */
public class MessageBean {

    /**
     * msg : success
     * code : 0
     * page : {"pageNum":0,"pageSize":10,"size":5,"startRow":1,"endRow":5,"total":5,"pages":1,"list":[{"id":120,"pitId":"345001","taskId":"345034","taskName":"起草文件","createTime":"2020-05-06 10:54:51","startTime":"2020-05-06 10:54:51","endTime":"2020-05-06 10:54:51","category":"1","state":-1,"docTitle":"公文拟制","deadlineTime":"2020-05-06 10:54:51","warningState":"10","isEmergency":"0"},{"id":123,"pitId":"360001","taskId":"360035","taskName":"文件录入/签收","createTime":"2020-05-06 15:30:37","startTime":null,"endTime":null,"category":"3","state":-1,"docTitle":"我要收文了","deadlineTime":"2020-04-25 19:46:24","warningState":null,"isEmergency":1},{"id":1,"pitId":null,"taskId":null,"taskName":null,"createTime":"2020-04-13 19:46:24","startTime":null,"endTime":null,"category":"6","state":0,"docTitle":"市政公文","deadlineTime":null,"warningState":null,"isEmergency":null},{"id":1,"pitId":null,"taskId":null,"taskName":null,"createTime":null,"startTime":null,"endTime":null,"category":"5","state":0,"docTitle":"工作计划","deadlineTime":"2020-04-25 19:46:24","warningState":null,"isEmergency":null},{"id":1,"pitId":null,"taskId":null,"taskName":null,"createTime":null,"startTime":null,"endTime":null,"category":"7","state":null,"docTitle":"文件报发","deadlineTime":"2020-04-25 19:46:24","warningState":null,"isEmergency":null}],"prePage":0,"nextPage":1,"isFirstPage":false,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
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
         * pageNum : 0
         * pageSize : 10
         * size : 5
         * startRow : 1
         * endRow : 5
         * total : 5
         * pages : 1
         * list : [{"id":120,"pitId":"345001","taskId":"345034","taskName":"起草文件","createTime":"2020-05-06 10:54:51","startTime":"2020-05-06 10:54:51","endTime":"2020-05-06 10:54:51","category":"1","state":-1,"docTitle":"公文拟制","deadlineTime":"2020-05-06 10:54:51","warningState":"10","isEmergency":"0"},{"id":123,"pitId":"360001","taskId":"360035","taskName":"文件录入/签收","createTime":"2020-05-06 15:30:37","startTime":null,"endTime":null,"category":"3","state":-1,"docTitle":"我要收文了","deadlineTime":"2020-04-25 19:46:24","warningState":null,"isEmergency":1},{"id":1,"pitId":null,"taskId":null,"taskName":null,"createTime":"2020-04-13 19:46:24","startTime":null,"endTime":null,"category":"6","state":0,"docTitle":"市政公文","deadlineTime":null,"warningState":null,"isEmergency":null},{"id":1,"pitId":null,"taskId":null,"taskName":null,"createTime":null,"startTime":null,"endTime":null,"category":"5","state":0,"docTitle":"工作计划","deadlineTime":"2020-04-25 19:46:24","warningState":null,"isEmergency":null},{"id":1,"pitId":null,"taskId":null,"taskName":null,"createTime":null,"startTime":null,"endTime":null,"category":"7","state":null,"docTitle":"文件报发","deadlineTime":"2020-04-25 19:46:24","warningState":null,"isEmergency":null}]
         * prePage : 0
         * nextPage : 1
         * isFirstPage : false
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * lastPage : 1
         * firstPage : 1
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
        private String lastPage;
        private String firstPage;
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

        public String getLastPage() {
            return lastPage;
        }

        public void setLastPage(String lastPage) {
            this.lastPage = lastPage;
        }

        public String getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(String firstPage) {
            this.firstPage = firstPage;
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
             * id : 120
             * pitId : 345001
             * taskId : 345034
             * taskName : 起草文件
             * createTime : 2020-05-06 10:54:51
             * startTime : 2020-05-06 10:54:51
             * endTime : 2020-05-06 10:54:51
             * category : 1
             * state : -1
             * docTitle : 公文拟制
             * deadlineTime : 2020-05-06 10:54:51
             * warningState : 10
             * isEmergency : 0
             */

            private String id;
            private String pitId;
            private String taskId;
            private String taskName;
            private String createTime;
            private String startTime;
            private String endTime;
            private String category;
            private String state;
            private String docTitle;
            private String deadlineTime;
            private String warningState;
            private String isEmergency;
            private String total;
            private String quasiNumber;
            private String documentReportUnit;

            public String getDocumentReportUnit() {
                return documentReportUnit;
            }

            public void setDocumentReportUnit(String documentReportUnit) {
                this.documentReportUnit = documentReportUnit;
            }

            public String getQuasiNumber() {
                return quasiNumber;
            }

            public void setQuasiNumber(String quasiNumber) {
                this.quasiNumber = quasiNumber;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPitId() {
                return pitId;
            }

            public void setPitId(String pitId) {
                this.pitId = pitId;
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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getDocTitle() {
                return docTitle;
            }

            public void setDocTitle(String docTitle) {
                this.docTitle = docTitle;
            }

            public String getDeadlineTime() {
                return deadlineTime;
            }

            public void setDeadlineTime(String deadlineTime) {
                this.deadlineTime = deadlineTime;
            }

            public String getWarningState() {
                return warningState;
            }

            public void setWarningState(String warningState) {
                this.warningState = warningState;
            }

            public String getIsEmergency() {
                return isEmergency;
            }

            public void setIsEmergency(String isEmergency) {
                this.isEmergency = isEmergency;
            }
        }
    }
}
