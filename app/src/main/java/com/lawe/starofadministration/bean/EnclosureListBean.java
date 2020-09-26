package com.lawe.starofadministration.bean;

import java.util.List;

/**
 * author : fuke
 * date : 2020/9/25 10:53
 * description : 描述
 */
public class EnclosureListBean {

    /**
     * msg : success
     * code : 0
     * page : {"pageNum":1,"pageSize":10,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"id":"1309322506479915009","relationId":"5408ffa1-4794-4f97-abc4-a0dd941d0ec9","path":"http://192.168.0.179:null/files/upload_file//5408ffa1-4794-4f97-abc4-a0dd941d0ec92020/09/25/1601001785820.jpg","updateTime":"2020-09-25 10:43:06","byteSize":556234,"suffix":"jpg","uploadifyTitle":"map.jpg","uploadUserId":"1307948026315431938","uploadUserName":"测试01","uploadUserUnit":"1263770327617937410","uploadUserUnitName":"1测试A","state":1}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
     */

    private String msg;
    private int code;
    private PageBean page;

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
         * size : 1
         * startRow : 1
         * endRow : 1
         * total : 1
         * pages : 1
         * list : [{"id":"1309322506479915009","relationId":"5408ffa1-4794-4f97-abc4-a0dd941d0ec9","path":"http://192.168.0.179:null/files/upload_file//5408ffa1-4794-4f97-abc4-a0dd941d0ec92020/09/25/1601001785820.jpg","updateTime":"2020-09-25 10:43:06","byteSize":556234,"suffix":"jpg","uploadifyTitle":"map.jpg","uploadUserId":"1307948026315431938","uploadUserName":"测试01","uploadUserUnit":"1263770327617937410","uploadUserUnitName":"1测试A","state":1}]
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

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
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

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
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
             * id : 1309322506479915009
             * relationId : 5408ffa1-4794-4f97-abc4-a0dd941d0ec9
             * path : http://192.168.0.179:null/files/upload_file//5408ffa1-4794-4f97-abc4-a0dd941d0ec92020/09/25/1601001785820.jpg
             * updateTime : 2020-09-25 10:43:06
             * byteSize : 556234
             * suffix : jpg
             * uploadifyTitle : map.jpg
             * uploadUserId : 1307948026315431938
             * uploadUserName : 测试01
             * uploadUserUnit : 1263770327617937410
             * uploadUserUnitName : 1测试A
             * state : 1
             */

            private String id;
            private String relationId;
            private String path;
            private String updateTime;
            private int byteSize;
            private String suffix;
            private String uploadifyTitle;
            private String uploadUserId;
            private String uploadUserName;
            private String uploadUserUnit;
            private String uploadUserUnitName;
            private int state;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRelationId() {
                return relationId;
            }

            public void setRelationId(String relationId) {
                this.relationId = relationId;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getByteSize() {
                return byteSize;
            }

            public void setByteSize(int byteSize) {
                this.byteSize = byteSize;
            }

            public String getSuffix() {
                return suffix;
            }

            public void setSuffix(String suffix) {
                this.suffix = suffix;
            }

            public String getUploadifyTitle() {
                return uploadifyTitle;
            }

            public void setUploadifyTitle(String uploadifyTitle) {
                this.uploadifyTitle = uploadifyTitle;
            }

            public String getUploadUserId() {
                return uploadUserId;
            }

            public void setUploadUserId(String uploadUserId) {
                this.uploadUserId = uploadUserId;
            }

            public String getUploadUserName() {
                return uploadUserName;
            }

            public void setUploadUserName(String uploadUserName) {
                this.uploadUserName = uploadUserName;
            }

            public String getUploadUserUnit() {
                return uploadUserUnit;
            }

            public void setUploadUserUnit(String uploadUserUnit) {
                this.uploadUserUnit = uploadUserUnit;
            }

            public String getUploadUserUnitName() {
                return uploadUserUnitName;
            }

            public void setUploadUserUnitName(String uploadUserUnitName) {
                this.uploadUserUnitName = uploadUserUnitName;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
