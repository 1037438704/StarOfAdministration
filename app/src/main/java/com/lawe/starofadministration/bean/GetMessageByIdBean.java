package com.lawe.starofadministration.bean;

import java.util.List;

/**
 * author : fuke
 * date : 2020/9/18 13:59
 * description : 描述
 */
public class GetMessageByIdBean {

    /**
     * msg : success
     * code : 0
     * documentFictionBO : {"id":"1275714346394279937","quasiNumber":"28nkjyj002202006240002","qNumber":"1","docTitle":"公文拟制","changeType":"","docType":"","publicProperty":"","docTheme":"","docAddress":"","timingSendTime":"2020-06-24 10:10:35","signUnit":"","signDoc":0,"spentTime":"","docNumber":"","number":"","archivedState":0,"relationId":"75e306e6-6b3c-4911-b105-111e1ebccfcc","timeStamp":"","modelId":"","remark":"","sendTime":"2020-06-24 16:58:08","creatorId":"1245548306401255426","creatorCoId":"1235120679131725825","createTime":"2020-06-24 16:56:16","modifierId":"","modifierCoId":"","modifyTime":"","state":1,"filePath":"","fileName":"","forwardingFile":"","registerId":"","archivePeopleId":"","archivePeopleCoId":"","dName":"","archivedTime":"","signUnitList":"","assigneeList":[{"id":"","departFullName":"","depUserId":"1245548306401255426","dName":"啦啦啦","userPhoto":""}]}
     */

    private String msg;
    private int code;
    private DocumentFictionBOBean documentFictionBO;

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

    public DocumentFictionBOBean getDocumentFictionBO() {
        return documentFictionBO;
    }

    public void setDocumentFictionBO(DocumentFictionBOBean documentFictionBO) {
        this.documentFictionBO = documentFictionBO;
    }

    public static class DocumentFictionBOBean {
        /**
         * id : 1275714346394279937
         * quasiNumber : 28nkjyj002202006240002
         * qNumber : 1
         * docTitle : 公文拟制
         * changeType :
         * docType :
         * publicProperty :
         * docTheme :
         * docAddress :
         * timingSendTime : 2020-06-24 10:10:35
         * signUnit :
         * signDoc : 0
         * spentTime :
         * docNumber :
         * number :
         * archivedState : 0
         * relationId : 75e306e6-6b3c-4911-b105-111e1ebccfcc
         * timeStamp :
         * modelId :
         * remark :
         * sendTime : 2020-06-24 16:58:08
         * creatorId : 1245548306401255426
         * creatorCoId : 1235120679131725825
         * createTime : 2020-06-24 16:56:16
         * modifierId :
         * modifierCoId :
         * modifyTime :
         * state : 1
         * filePath :
         * fileName :
         * forwardingFile :
         * registerId :
         * archivePeopleId :
         * archivePeopleCoId :
         * dName :
         * archivedTime :
         * signUnitList :
         * assigneeList : [{"id":"","departFullName":"","depUserId":"1245548306401255426","dName":"啦啦啦","userPhoto":""}]
         */

        private String id;
        private String quasiNumber;
        private String qNumber;
        private String docTitle;
        private String changeType;
        private String docType;
        private String publicProperty;
        private String docTheme;
        private String docAddress;
        private String timingSendTime;
        private String signUnit;
        private int signDoc;
        private String spentTime;
        private String docNumber;
        private String number;
        private int archivedState;
        private String relationId;
        private String timeStamp;
        private String modelId;
        private String remark;
        private String sendTime;
        private String creatorId;
        private String creatorCoId;
        private String createTime;
        private String modifierId;
        private String modifierCoId;
        private String modifyTime;
        private int state;
        private String filePath;
        private String fileName;
        private String forwardingFile;
        private String registerId;
        private String archivePeopleId;
        private String archivePeopleCoId;
        private String dName;
        private String archivedTime;
        private String signUnitList;
        private List<AssigneeListBean> assigneeList;

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

        public String getQNumber() {
            return qNumber;
        }

        public void setQNumber(String qNumber) {
            this.qNumber = qNumber;
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

        public int getSignDoc() {
            return signDoc;
        }

        public void setSignDoc(int signDoc) {
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getArchivedState() {
            return archivedState;
        }

        public void setArchivedState(int archivedState) {
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

        public String getModifierId() {
            return modifierId;
        }

        public void setModifierId(String modifierId) {
            this.modifierId = modifierId;
        }

        public String getModifierCoId() {
            return modifierCoId;
        }

        public void setModifierCoId(String modifierCoId) {
            this.modifierCoId = modifierCoId;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getForwardingFile() {
            return forwardingFile;
        }

        public void setForwardingFile(String forwardingFile) {
            this.forwardingFile = forwardingFile;
        }

        public String getRegisterId() {
            return registerId;
        }

        public void setRegisterId(String registerId) {
            this.registerId = registerId;
        }

        public String getArchivePeopleId() {
            return archivePeopleId;
        }

        public void setArchivePeopleId(String archivePeopleId) {
            this.archivePeopleId = archivePeopleId;
        }

        public String getArchivePeopleCoId() {
            return archivePeopleCoId;
        }

        public void setArchivePeopleCoId(String archivePeopleCoId) {
            this.archivePeopleCoId = archivePeopleCoId;
        }

        public String getDName() {
            return dName;
        }

        public void setDName(String dName) {
            this.dName = dName;
        }

        public String getArchivedTime() {
            return archivedTime;
        }

        public void setArchivedTime(String archivedTime) {
            this.archivedTime = archivedTime;
        }

        public String getSignUnitList() {
            return signUnitList;
        }

        public void setSignUnitList(String signUnitList) {
            this.signUnitList = signUnitList;
        }

        public List<AssigneeListBean> getAssigneeList() {
            return assigneeList;
        }

        public void setAssigneeList(List<AssigneeListBean> assigneeList) {
            this.assigneeList = assigneeList;
        }

        public static class AssigneeListBean {
            /**
             * id :
             * departFullName :
             * depUserId : 1245548306401255426
             * dName : 啦啦啦
             * userPhoto :
             */

            private String id;
            private String departFullName;
            private String depUserId;
            private String dName;
            private String userPhoto;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDepartFullName() {
                return departFullName;
            }

            public void setDepartFullName(String departFullName) {
                this.departFullName = departFullName;
            }

            public String getDepUserId() {
                return depUserId;
            }

            public void setDepUserId(String depUserId) {
                this.depUserId = depUserId;
            }

            public String getDName() {
                return dName;
            }

            public void setDName(String dName) {
                this.dName = dName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }
        }
    }
}
