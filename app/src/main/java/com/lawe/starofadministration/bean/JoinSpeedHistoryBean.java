package com.lawe.starofadministration.bean;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author : fuke
 * date : 2020/6/24 16:28
 * description : 描述
 */
public class JoinSpeedHistoryBean {

    /**
     * msg : success
     * code : 0
     * historyTaskMap : {"createFileRetract":[{"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"fullMessageBytes":"5pKk5Zue","inserted":false,"updated":false,"deleted":false}],"createFile":[{"id":"452510","type":"起草文件","userId":"啦啦啦","time":"2020-06-24 16:56:17","taskId":"452508","processInstanceId":"452501","action":"AddComment","message":"","fullMessage":"","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":[""],"fullMessageBytes":"","inserted":false,"updated":false,"deleted":false}]}
     */

    private String msg;
    private int code;
    private HistoryTaskMapBean historyTaskMap;

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

    public HistoryTaskMapBean getHistoryTaskMap() {
        return historyTaskMap;
    }

    public void setHistoryTaskMap(HistoryTaskMapBean historyTaskMap) {
        this.historyTaskMap = historyTaskMap;
    }

    public static class HistoryTaskMapBean {
        private List<CreateFileRetractBean> createFileRetract;
        private List<VerifyFileRetractBean> verifyFileRetract;
        private List<ReviewFileRetractBean> reviewFileRetract;
        private List<ProofreadingFileRetractBean> proofreadingFileRetract;
        private List<SignFileRetractBean> signFileRetract;
        private List<IssueFileRetractBean> issueFileRetract;
        private List<CountersignedFileRetractBean> countersignedFileRetract;
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
                            createFile.get(i).getUserPhoto(),
                            createFile.get(i).departmentName,
                            createFile.get(i).getJobName(),
                            createFile.get(i).getDname(),
                            createFile.get(i).endTime,
                            createFile.get(i).getComment().message));
                }
            }

            if (verifyFile != null) {
                for (int i = 0; i < verifyFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "审核文件",
                            verifyFile.get(i).getUserPhoto(),
                            verifyFile.get(i).departmentName,
                            verifyFile.get(i).getJobName(),
                            verifyFile.get(i).getDname(),
                            verifyFile.get(i).endTime,
                            verifyFile.get(i).getComment().message));
                }
            }

            if (reviewFile != null) {
                for (int i = 0; i < reviewFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "审阅文件",
                            reviewFile.get(i).getUserPhoto(),
                            reviewFile.get(i).departmentName,
                            reviewFile.get(i).getJobName(),
                            reviewFile.get(i).getDname(),
                            reviewFile.get(i).endTime,
                            reviewFile.get(i).getComment().message));
                }
            }

            if (proofreadingFile != null) {
                for (int i = 0; i < proofreadingFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "校对文件",
                            proofreadingFile.get(i).getUserPhoto(),
                            proofreadingFile.get(i).departmentName,
                            proofreadingFile.get(i).getJobName(),
                            proofreadingFile.get(i).getDname(),
                            proofreadingFile.get(i).endTime,
                            proofreadingFile.get(i).getComment().message));
                }
            }

            if (signFile != null) {
                for (int i = 0; i < signFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "签发文件",
                            signFile.get(i).getUserPhoto(),
                            signFile.get(i).departmentName,
                            signFile.get(i).getJobName(),
                            signFile.get(i).getDname(),
                            signFile.get(i).endTime,
                            signFile.get(i).getComment().message));
                }
            }

            if (issueFile != null) {
                for (int i = 0; i < issueFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "核发文件",
                            issueFile.get(i).getUserPhoto(),
                            issueFile.get(i).departmentName,
                            issueFile.get(i).getJobName(),
                            issueFile.get(i).getDname(),
                            issueFile.get(i).endTime,
                            issueFile.get(i).getComment().message));
                }
            }

            if (countersignedFile != null) {
                for (int i = 0; i < countersignedFile.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "会签文件",
                            countersignedFile.get(i).getUserPhoto(),
                            countersignedFile.get(i).departmentName,
                            countersignedFile.get(i).getJobName(),
                            countersignedFile.get(i).getDname(),
                            countersignedFile.get(i).endTime,
                            countersignedFile.get(i).getComment().message));
                }
            }

            if (createFileRetract != null) {
                for (int i = 0; i < createFileRetract.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "公文拟制撤回",
                            createFileRetract.get(i).getUserPhoto(),
                            createFileRetract.get(i).departmentName,
                            createFileRetract.get(i).getJobName(),
                            createFileRetract.get(i).getDname(),
                            createFileRetract.get(i).endTime,
                            createFileRetract.get(i).getComment().message));
                }
            }

            if (verifyFileRetract != null) {
                for (int i = 0; i < verifyFileRetract.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "审核文件撤回",
                            verifyFileRetract.get(i).getUserPhoto(),
                            verifyFileRetract.get(i).departmentName,
                            verifyFileRetract.get(i).getJobName(),
                            verifyFileRetract.get(i).getDname(),
                            verifyFileRetract.get(i).endTime,
                            verifyFileRetract.get(i).getComment().message));
                }
            }

            if (reviewFileRetract != null) {
                for (int i = 0; i < reviewFileRetract.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "审阅文件撤回",
                            reviewFileRetract.get(i).getUserPhoto(),
                            reviewFileRetract.get(i).departmentName,
                            reviewFileRetract.get(i).getJobName(),
                            reviewFileRetract.get(i).getDname(),
                            reviewFileRetract.get(i).endTime,
                            reviewFileRetract.get(i).getComment().message));
                }
            }

            if (proofreadingFileRetract != null) {
                for (int i = 0; i < proofreadingFileRetract.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "校对文件撤回",
                            proofreadingFileRetract.get(i).getUserPhoto(),
                            proofreadingFileRetract.get(i).departmentName,
                            proofreadingFileRetract.get(i).getJobName(),
                            proofreadingFileRetract.get(i).getDname(),
                            proofreadingFileRetract.get(i).endTime,
                            proofreadingFileRetract.get(i).getComment().message));
                }
            }

            if (signFileRetract != null) {
                for (int i = 0; i < signFileRetract.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "签发文件撤回",
                            signFileRetract.get(i).getUserPhoto(),
                            signFileRetract.get(i).departmentName,
                            signFileRetract.get(i).getJobName(),
                            signFileRetract.get(i).getDname(),
                            signFileRetract.get(i).endTime,
                            signFileRetract.get(i).getComment().message));
                }
            }

            if (issueFileRetract != null) {
                for (int i = 0; i < issueFileRetract.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "核发文件撤回",
                            issueFileRetract.get(i).getUserPhoto(),
                            issueFileRetract.get(i).departmentName,
                            issueFileRetract.get(i).getJobName(),
                            issueFileRetract.get(i).getDname(),
                            issueFileRetract.get(i).endTime,
                            issueFileRetract.get(i).getComment().message));
                }
            }

            if (countersignedFileRetract != null) {
                for (int i = 0; i < countersignedFileRetract.size(); i++) {
                    ListSumBean.add(new SumBean(
                            i,
                            "会签文件撤回",
                            countersignedFileRetract.get(i).getUserPhoto(),
                            countersignedFileRetract.get(i).departmentName,
                            countersignedFileRetract.get(i).getJobName(),
                            countersignedFileRetract.get(i).getDname(),
                            countersignedFileRetract.get(i).endTime,
                            countersignedFileRetract.get(i).getComment().message));
                }
            }

            //列表反转
           // Collections.reverse(ListSumBean);
            Log.v("TAGB",new Gson().toJson(ListSumBean));
            return ListSumBean;
        }

        public List<CreateFileRetractBean> getCreateFileRetract() {
            return createFileRetract;
        }

        public void setCreateFileRetract(List<CreateFileRetractBean> createFileRetract) {
            this.createFileRetract = createFileRetract;
        }

        public List<CreateFileBean> getCreateFile() {
            return createFile;
        }

        public void setCreateFile(List<CreateFileBean> createFile) {
            this.createFile = createFile;
        }

        public List<VerifyFileRetractBean> getVerifyFileRetract() {
            return verifyFileRetract;
        }

        public void setVerifyFileRetract(List<VerifyFileRetractBean> verifyFileRetract) {
            this.verifyFileRetract = verifyFileRetract;
        }

        public List<ReviewFileRetractBean> getReviewFileRetract() {
            return reviewFileRetract;
        }

        public void setReviewFileRetract(List<ReviewFileRetractBean> reviewFileRetract) {
            this.reviewFileRetract = reviewFileRetract;
        }

        public List<ProofreadingFileRetractBean> getProofreadingFileRetract() {
            return proofreadingFileRetract;
        }

        public void setProofreadingFileRetract(List<ProofreadingFileRetractBean> proofreadingFileRetract) {
            this.proofreadingFileRetract = proofreadingFileRetract;
        }

        public List<SignFileRetractBean> getSignFileRetract() {
            return signFileRetract;
        }

        public void setSignFileRetract(List<SignFileRetractBean> signFileRetract) {
            this.signFileRetract = signFileRetract;
        }

        public List<IssueFileRetractBean> getIssueFileRetract() {
            return issueFileRetract;
        }

        public void setIssueFileRetract(List<IssueFileRetractBean> issueFileRetract) {
            this.issueFileRetract = issueFileRetract;
        }

        public List<CountersignedFileRetractBean> getCountersignedFileRetract() {
            return countersignedFileRetract;
        }

        public void setCountersignedFileRetract(List<CountersignedFileRetractBean> countersignedFileRetract) {
            this.countersignedFileRetract = countersignedFileRetract;
        }

        public List<VerifyFileBean> getVerifyFile() {
            return verifyFile;
        }

        public void setVerifyFile(List<VerifyFileBean> verifyFile) {
            this.verifyFile = verifyFile;
        }

        public List<ReviewFileBean> getReviewFile() {
            return reviewFile;
        }

        public void setReviewFile(List<ReviewFileBean> reviewFile) {
            this.reviewFile = reviewFile;
        }

        public List<ProofreadingFileBean> getProofreadingFile() {
            return proofreadingFile;
        }

        public void setProofreadingFile(List<ProofreadingFileBean> proofreadingFile) {
            this.proofreadingFile = proofreadingFile;
        }

        public List<SignFileBean> getSignFile() {
            return signFile;
        }

        public void setSignFile(List<SignFileBean> signFile) {
            this.signFile = signFile;
        }

        public List<IssueFileBean> getIssueFile() {
            return issueFile;
        }

        public void setIssueFile(List<IssueFileBean> issueFile) {
            this.issueFile = issueFile;
        }

        public List<CountersignedFileBean> getCountersignedFile() {
            return countersignedFile;
        }

        public void setCountersignedFile(List<CountersignedFileBean> countersignedFile) {
            this.countersignedFile = countersignedFile;
        }

        public static class CreateFileRetractBean {

            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CommentBean getComment() {
                return comment;
            }

            public void setComment(CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class VerifyFileRetractBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class ReviewFileRetractBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class ProofreadingFileRetractBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class SignFileRetractBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class IssueFileRetractBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class CountersignedFileRetractBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class CreateFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class VerifyFileBean {

            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class ReviewFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class ProofreadingFileBean {

            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class SignFileBean {

            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class IssueFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class CountersignedFileBean {
            /**
             * departmentName : 南开区教育局002
             * jobName :
             * userPhoto :
             * endTime : 2020-09-15 15:36:05
             * comment : {"id":"457501","type":"createFileRetract","userId":"啦啦啦","time":"2020-09-15 15:35:40","taskId":"452524","processInstanceId":"452501","action":"AddComment","message":"撤回","fullMessage":"撤回","fullMessageBytes":"5pKk5Zue","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","messageParts":["撤回"],"deleted":false,"inserted":false,"updated":false}
             * dname : 啦啦啦
             */

            private String departmentName;
            private String jobName;
            private String userPhoto;
            private String endTime;
            private CreateFileRetractBean.CommentBean comment;
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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public CreateFileRetractBean.CommentBean getComment() {
                return comment;
            }

            public void setComment(CreateFileRetractBean.CommentBean comment) {
                this.comment = comment;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public static class CommentBean {
                /**
                 * id : 457501
                 * type : createFileRetract
                 * userId : 啦啦啦
                 * time : 2020-09-15 15:35:40
                 * taskId : 452524
                 * processInstanceId : 452501
                 * action : AddComment
                 * message : 撤回
                 * fullMessage : 撤回
                 * fullMessageBytes : 5pKk5Zue
                 * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
                 * messageParts : ["撤回"]
                 * deleted : false
                 * inserted : false
                 * updated : false
                 */

                private String id;
                private String type;
                private String userId;
                private String time;
                private String taskId;
                private String processInstanceId;
                private String action;
                private String message;
                private String fullMessage;
                private String fullMessageBytes;
                private String persistentState;
                private boolean deleted;
                private boolean inserted;
                private boolean updated;
                private List<String> messageParts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTaskId() {
                    return taskId;
                }

                public void setTaskId(String taskId) {
                    this.taskId = taskId;
                }

                public String getProcessInstanceId() {
                    return processInstanceId;
                }

                public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getFullMessage() {
                    return fullMessage;
                }

                public void setFullMessage(String fullMessage) {
                    this.fullMessage = fullMessage;
                }

                public String getFullMessageBytes() {
                    return fullMessageBytes;
                }

                public void setFullMessageBytes(String fullMessageBytes) {
                    this.fullMessageBytes = fullMessageBytes;
                }

                public String getPersistentState() {
                    return persistentState;
                }

                public void setPersistentState(String persistentState) {
                    this.persistentState = persistentState;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public boolean isInserted() {
                    return inserted;
                }

                public void setInserted(boolean inserted) {
                    this.inserted = inserted;
                }

                public boolean isUpdated() {
                    return updated;
                }

                public void setUpdated(boolean updated) {
                    this.updated = updated;
                }

                public List<String> getMessageParts() {
                    return messageParts;
                }

                public void setMessageParts(List<String> messageParts) {
                    this.messageParts = messageParts;
                }
            }
        }

        public static class SumBean {
            public int getIndex;
            public String typeTitle;
            public String imgHead;
            public String departName;
            public String jobName;
            public String name;
            public String time;
            public String content;

            public SumBean(int getIndex, String typeTitle, String imgHead, String departName, String jobName, String name, String time, String content) {
                this.getIndex = getIndex;
                this.typeTitle = typeTitle;
                this.imgHead = imgHead;
                this.departName = departName;
                this.jobName = jobName;
                this.name = name;
                this.time = time;
                this.content = content;
            }
        }
    }
}
