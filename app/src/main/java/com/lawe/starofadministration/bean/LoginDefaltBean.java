package com.lawe.starofadministration.bean;

/**
 * author : fuke
 * date : 2020/5/15 9:23
 * description : 默认登录方式
 */
public class LoginDefaltBean {

    /**
     * msg : success
     * code : 0
     * expire : 7200
     * entity : {"id":"1253514067132448770","regionCode":null,"dName":"周冬雨","dAccount":"15033044576","dNum":"15033044576","sex":2,"birthDate":"2020-04-24 00:00:00","organizationCode":1,"rankingIf":1,"startStatus":1,"accountSafeLevel":1,"job_name":null,"job_level":null,"labelType":"测试标签1,测试标签2,测试标签3,测试标签4,测试标签5,测试签名6,测试签名7,dfs","password":"4a4ce9875fa476213a86b8c5bced12362f1617398fc32652e37c5eca585408a9","salt":"DiODCkn7ZnxAT37u9iFZ","createUser":null,"createTime":"2020-05-06 10:40:17","remark":null,"qrCodeString":null,"userPhoto":null,"workPercentage":null,"creditPercentage":null,"seniorSalfSetif":null,"mailPhoneShowif":null,"leapfrogSendif":null,"rankingStatisticsif":null,"delIf":0,"joinUser":null}
     * token : fd9ceeb919554595692129e1d348da97
     */

    private String msg;
    private int code;
    private int expire;
    private EntityBean entity;
    private String token;

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

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public EntityBean getEntity() {
        return entity;
    }

    public void setEntity(EntityBean entity) {
        this.entity = entity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class EntityBean {
        /**
         * id : 1253514067132448770
         * regionCode : null
         * dName : 周冬雨
         * dAccount : 15033044576
         * dNum : 15033044576
         * sex : 2
         * birthDate : 2020-04-24 00:00:00
         * organizationCode : 1
         * rankingIf : 1
         * startStatus : 1
         * accountSafeLevel : 1
         * job_name : null
         * job_level : null
         * labelType : 测试标签1,测试标签2,测试标签3,测试标签4,测试标签5,测试签名6,测试签名7,dfs
         * password : 4a4ce9875fa476213a86b8c5bced12362f1617398fc32652e37c5eca585408a9
         * salt : DiODCkn7ZnxAT37u9iFZ
         * createUser : null
         * createTime : 2020-05-06 10:40:17
         * remark : null
         * qrCodeString : null
         * userPhoto : null
         * workPercentage : null
         * creditPercentage : null
         * seniorSalfSetif : null
         * mailPhoneShowif : null
         * leapfrogSendif : null
         * rankingStatisticsif : null
         * delIf : 0
         * joinUser : null
         */

        private String id;
        private Object regionCode;
        private String dName;
        private String dAccount;
        private String dNum;
        private int sex;
        private String birthDate;
        private int organizationCode;
        private int rankingIf;
        private int startStatus;
        private int accountSafeLevel;
        private Object job_name;
        private Object job_level;
        private String labelType;
        private String password;
        private String salt;
        private Object createUser;
        private String createTime;
        private Object remark;
        private Object qrCodeString;
        private Object userPhoto;
        private Object workPercentage;
        private Object creditPercentage;
        private Object seniorSalfSetif;
        private Object mailPhoneShowif;
        private Object leapfrogSendif;
        private Object rankingStatisticsif;
        private int delIf;
        private Object joinUser;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(Object regionCode) {
            this.regionCode = regionCode;
        }

        public String getDName() {
            return dName;
        }

        public void setDName(String dName) {
            this.dName = dName;
        }

        public String getDAccount() {
            return dAccount;
        }

        public void setDAccount(String dAccount) {
            this.dAccount = dAccount;
        }

        public String getDNum() {
            return dNum;
        }

        public void setDNum(String dNum) {
            this.dNum = dNum;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public int getOrganizationCode() {
            return organizationCode;
        }

        public void setOrganizationCode(int organizationCode) {
            this.organizationCode = organizationCode;
        }

        public int getRankingIf() {
            return rankingIf;
        }

        public void setRankingIf(int rankingIf) {
            this.rankingIf = rankingIf;
        }

        public int getStartStatus() {
            return startStatus;
        }

        public void setStartStatus(int startStatus) {
            this.startStatus = startStatus;
        }

        public int getAccountSafeLevel() {
            return accountSafeLevel;
        }

        public void setAccountSafeLevel(int accountSafeLevel) {
            this.accountSafeLevel = accountSafeLevel;
        }

        public Object getJob_name() {
            return job_name;
        }

        public void setJob_name(Object job_name) {
            this.job_name = job_name;
        }

        public Object getJob_level() {
            return job_level;
        }

        public void setJob_level(Object job_level) {
            this.job_level = job_level;
        }

        public String getLabelType() {
            return labelType;
        }

        public void setLabelType(String labelType) {
            this.labelType = labelType;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public Object getCreateUser() {
            return createUser;
        }

        public void setCreateUser(Object createUser) {
            this.createUser = createUser;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getQrCodeString() {
            return qrCodeString;
        }

        public void setQrCodeString(Object qrCodeString) {
            this.qrCodeString = qrCodeString;
        }

        public Object getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(Object userPhoto) {
            this.userPhoto = userPhoto;
        }

        public Object getWorkPercentage() {
            return workPercentage;
        }

        public void setWorkPercentage(Object workPercentage) {
            this.workPercentage = workPercentage;
        }

        public Object getCreditPercentage() {
            return creditPercentage;
        }

        public void setCreditPercentage(Object creditPercentage) {
            this.creditPercentage = creditPercentage;
        }

        public Object getSeniorSalfSetif() {
            return seniorSalfSetif;
        }

        public void setSeniorSalfSetif(Object seniorSalfSetif) {
            this.seniorSalfSetif = seniorSalfSetif;
        }

        public Object getMailPhoneShowif() {
            return mailPhoneShowif;
        }

        public void setMailPhoneShowif(Object mailPhoneShowif) {
            this.mailPhoneShowif = mailPhoneShowif;
        }

        public Object getLeapfrogSendif() {
            return leapfrogSendif;
        }

        public void setLeapfrogSendif(Object leapfrogSendif) {
            this.leapfrogSendif = leapfrogSendif;
        }

        public Object getRankingStatisticsif() {
            return rankingStatisticsif;
        }

        public void setRankingStatisticsif(Object rankingStatisticsif) {
            this.rankingStatisticsif = rankingStatisticsif;
        }

        public int getDelIf() {
            return delIf;
        }

        public void setDelIf(int delIf) {
            this.delIf = delIf;
        }

        public Object getJoinUser() {
            return joinUser;
        }

        public void setJoinUser(Object joinUser) {
            this.joinUser = joinUser;
        }
    }
}
