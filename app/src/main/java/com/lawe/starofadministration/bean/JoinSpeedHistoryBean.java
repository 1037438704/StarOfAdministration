package com.lawe.starofadministration.bean;

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
     * historyTaskMap : {"qicao":[{"id":"450035","type":"起草文件","userId":"李四","time":"2020-06-24 14:43:41","taskId":"450033","processInstanceId":"450026","action":"AddComment","message":"摸摸你自己的","fullMessage":"摸摸你自己的","persistentState":"org.activiti.engine.impl.persistence.entity.CommentEntityImpl","fullMessageBytes":"5pG45pG45L2g6Ieq5bex55qE","messageParts":["摸摸你自己的"],"inserted":false,"deleted":false,"updated":false}]}
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

        private List<QicaoBean> qicao;

        public List<QicaoBean> getQicao() {
            return qicao;
        }

        public void setQicao(List<QicaoBean> qicao) {
            this.qicao = qicao;
        }

        public static class QicaoBean {
            /**
             * id : 450035
             * type : 起草文件
             * userId : 李四
             * time : 2020-06-24 14:43:41
             * taskId : 450033
             * processInstanceId : 450026
             * action : AddComment
             * message : 摸摸你自己的
             * fullMessage : 摸摸你自己的
             * persistentState : org.activiti.engine.impl.persistence.entity.CommentEntityImpl
             * fullMessageBytes : 5pG45pG45L2g6Ieq5bex55qE
             * messageParts : ["摸摸你自己的"]
             * inserted : false
             * deleted : false
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
            private String persistentState;
            private String fullMessageBytes;
            private boolean inserted;
            private boolean deleted;
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

            public String getPersistentState() {
                return persistentState;
            }

            public void setPersistentState(String persistentState) {
                this.persistentState = persistentState;
            }

            public String getFullMessageBytes() {
                return fullMessageBytes;
            }

            public void setFullMessageBytes(String fullMessageBytes) {
                this.fullMessageBytes = fullMessageBytes;
            }

            public boolean isInserted() {
                return inserted;
            }

            public void setInserted(boolean inserted) {
                this.inserted = inserted;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
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
}
