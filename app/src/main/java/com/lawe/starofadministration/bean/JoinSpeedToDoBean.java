package com.lawe.starofadministration.bean;

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
    }
}
