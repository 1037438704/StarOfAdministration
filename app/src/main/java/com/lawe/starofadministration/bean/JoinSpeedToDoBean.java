package com.lawe.starofadministration.bean;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/9/22 11:11
 * description : 描述
 */
public class JoinSpeedToDoBean {

    /**
     * msg : success
     * code : 0
     * taskMap : {"createFile":[{"departmentName":"南开区教育局002","jobName":"","userPhoto":"","createTime":"2020-09-15 15:36:05","message":"尚未处理","dname":"啦啦啦"}]}
     */

    private String msg;
    private int code;
    private TaskMapBean taskMap;

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

    public TaskMapBean getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(TaskMapBean taskMap) {
        this.taskMap = taskMap;
    }

    public static class TaskMapBean {
        private List<CreateFileBean> createFile;
        private List<VerifyFileBean> verifyFile;
        private List<ReviewFileBean> reviewFile;
        private List<ProofreadingFileBean> proofreadingFile;
        private List<SignFileBean> signFile;
        private List<IssueFileBean> issueFile;
        private List<CountersignedFileBean> countersignedFile;

        public List<SumBean> getListSumBean() {
            List<SumBean> ListSumBean = new ArrayList<>();
            if (createFile != null) {
                for (int i = 0; i < createFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "起草文件",
                            createFile.get(i).userPhoto,
                            createFile.get(i).departmentName,
                            createFile.get(i).jobName,
                            createFile.get(i).dname,
                            createFile.get(i).message));
                }
            }

            if (verifyFile != null) {
                for (int i = 0; i < verifyFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "审核文件",
                            verifyFile.get(i).userPhoto,
                            verifyFile.get(i).departmentName,
                            verifyFile.get(i).jobName,
                            verifyFile.get(i).dname,
                            verifyFile.get(i).message));
                }
            }

            if (reviewFile != null) {
                for (int i = 0; i < reviewFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "审阅文件",
                            reviewFile.get(i).userPhoto,
                            reviewFile.get(i).departmentName,
                            reviewFile.get(i).jobName,
                            reviewFile.get(i).dname,
                            reviewFile.get(i).message));
                }
            }

            if (proofreadingFile != null) {
                for (int i = 0; i < proofreadingFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "校对文件",
                            proofreadingFile.get(i).userPhoto,
                            proofreadingFile.get(i).departmentName,
                            proofreadingFile.get(i).jobName,
                            proofreadingFile.get(i).dname,
                            proofreadingFile.get(i).message));
                }
            }

            if (signFile != null) {
                for (int i = 0; i < signFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "签发文件",
                            signFile.get(i).userPhoto,
                            signFile.get(i).departmentName,
                            signFile.get(i).jobName,
                            signFile.get(i).dname,
                            signFile.get(i).message));
                }
            }

            if (issueFile != null) {
                for (int i = 0; i < issueFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "核发文件",
                            issueFile.get(i).userPhoto,
                            issueFile.get(i).departmentName,
                            issueFile.get(i).jobName,
                            issueFile.get(i).dname,
                            issueFile.get(i).message));
                }
            }

            if (countersignedFile != null) {
                for (int i = 0; i < countersignedFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "会签文件",
                            countersignedFile.get(i).userPhoto,
                            countersignedFile.get(i).departmentName,
                            countersignedFile.get(i).jobName,
                            countersignedFile.get(i).dname,
                            countersignedFile.get(i).message));
                }
            }

            //列表反转
            // Collections.reverse(ListSumBean);
            Log.v("TAGB",new Gson().toJson(ListSumBean));
            return ListSumBean;
        }

        public List<CreateFileBean> getCreateFile() {
            return createFile;
        }

        public void setCreateFile(List<CreateFileBean> createFile) {
            this.createFile = createFile;
        }

        public static class CreateFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * createTime : 2020-09-15 15:36:05
             * message : 尚未处理
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String createTime;
            private String message;
            private String dname;

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getJobName() {
                return jobName;
            }

            public void setJobName(String jobName) {
                this.jobName = jobName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }
        }
        public static class VerifyFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * createTime : 2020-09-15 15:36:05
             * message : 尚未处理
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String createTime;
            private String message;
            private String dname;

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getJobName() {
                return jobName;
            }

            public void setJobName(String jobName) {
                this.jobName = jobName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }
        }
        public static class ReviewFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * createTime : 2020-09-15 15:36:05
             * message : 尚未处理
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String createTime;
            private String message;
            private String dname;

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getJobName() {
                return jobName;
            }

            public void setJobName(String jobName) {
                this.jobName = jobName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }
        }
        public static class ProofreadingFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * createTime : 2020-09-15 15:36:05
             * message : 尚未处理
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String createTime;
            private String message;
            private String dname;

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getJobName() {
                return jobName;
            }

            public void setJobName(String jobName) {
                this.jobName = jobName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }
        }
        public static class SignFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * createTime : 2020-09-15 15:36:05
             * message : 尚未处理
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String createTime;
            private String message;
            private String dname;

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getJobName() {
                return jobName;
            }

            public void setJobName(String jobName) {
                this.jobName = jobName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }
        }
        public static class IssueFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * createTime : 2020-09-15 15:36:05
             * message : 尚未处理
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String createTime;
            private String message;
            private String dname;

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getJobName() {
                return jobName;
            }

            public void setJobName(String jobName) {
                this.jobName = jobName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }
        }
        public static class CountersignedFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * createTime : 2020-09-15 15:36:05
             * message : 尚未处理
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String createTime;
            private String message;
            private String dname;

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getJobName() {
                return jobName;
            }

            public void setJobName(String jobName) {
                this.jobName = jobName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }
        }


        public static class SumBean {
            public int getIndex;
            public String typeTitle;
            public String imgHead;
            public String departName;
            public String jobName;
            public String name;
            public String content;

            public SumBean(int getIndex, String typeTitle, String imgHead, String departName, String jobName, String name, String content) {
                this.getIndex = getIndex;
                this.typeTitle = typeTitle;
                this.imgHead = imgHead;
                this.departName = departName;
                this.jobName = jobName;
                this.name = name;
                this.content = content;
            }
        }
    }
}
