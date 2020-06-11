package com.lawe.starofadministration.bean;

import java.util.List;

/**
 * author : fuke
 * date : 2020/5/15 9:23
 * description : 默认登录方式
 */
public class LoginDefaltBean {

    /**
     * msg : success
     * code : 0
     * entityBO : {"id":"1253514067132448770","regionCode":"","dName":"周冬雨","dAccount":"15033044576","dNum":"15033044576","sex":2,"birthDate":"2020-04-24 00:00:00","organizationCode":1,"rankingIf":1,"startStatus":1,"accountSafeLevel":1,"job_name":"","job_level":"","labelType":"测试标签1,测试标签2,测试标签3,测试标签4,测试标签5,测试签名6,测试签名7,dfs","password":"4a4ce9875fa476213a86b8c5bced12362f1617398fc32652e37c5eca585408a9","remark":"","qrCodeString":"","userPhoto":"","workPercentage":"","creditPercentage":"","seniorSalfSetif":"","mailPhoneShowif":"","leapfrogSendif":"","rankingStatisticsif":"","salt":"DiODCkn7ZnxAT37u9iFZ","delIf":0,"joinUser":"","tiePhone":"","lookHand":"","authorization":"","privateConnect":"","messagePush":"","warning":"","scheduleReminder":"","handSign":"","fontSize":"","deparentCode":"","listDepartment":[{"id":"1235120679131725825","parentId":"0","provinceCode":"2","cityCode":"3","countyCode":"8","departFullName":"南开区教育局002","departLevel":1,"simpleName":"南开教育局002","creditCode":"002","archives":"002","oneOfficialNum":"一级公文号","twoOfficialNum":"二级公文号","encodingRidge":"nkjyj002","officePhone":"022-23456789","mailListShowIf":1,"sortNum":"0006","rankingIf":1,"forwardId":"nkjyj002","functionInfo":"职能信息","createTime":"2020-03-04 16:31:32","inspectorEncoding":"","departType":""}]}
     * expire : 7200
     * token : e73e91c2fc8564db0058607e72123217
     */

    private String msg;
    private String code;
    private EntityBOBean entityBO;
    private String expire;
    private String token;

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

    public EntityBOBean getEntityBO() {
        return entityBO;
    }

    public void setEntityBO(EntityBOBean entityBO) {
        this.entityBO = entityBO;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class EntityBOBean {
        /**
         * id : 1253514067132448770
         * regionCode :
         * dName : 周冬雨
         * dAccount : 15033044576
         * dNum : 15033044576
         * sex : 2
         * birthDate : 2020-04-24 00:00:00
         * organizationCode : 1
         * rankingIf : 1
         * startStatus : 1
         * accountSafeLevel : 1
         * job_name :
         * job_level :
         * labelType : 测试标签1,测试标签2,测试标签3,测试标签4,测试标签5,测试签名6,测试签名7,dfs
         * password : 4a4ce9875fa476213a86b8c5bced12362f1617398fc32652e37c5eca585408a9
         * remark :
         * qrCodeString :
         * userPhoto :
         * workPercentage :
         * creditPercentage :
         * seniorSalfSetif :
         * mailPhoneShowif :
         * leapfrogSendif :
         * rankingStatisticsif :
         * salt : DiODCkn7ZnxAT37u9iFZ
         * delIf : 0
         * joinUser :
         * tiePhone :
         * lookHand :
         * authorization :
         * privateConnect :
         * messagePush :
         * warning :
         * scheduleReminder :
         * handSign :
         * fontSize :
         * deparentCode :
         * listDepartment : [{"id":"1235120679131725825","parentId":"0","provinceCode":"2","cityCode":"3","countyCode":"8","departFullName":"南开区教育局002","departLevel":1,"simpleName":"南开教育局002","creditCode":"002","archives":"002","oneOfficialNum":"一级公文号","twoOfficialNum":"二级公文号","encodingRidge":"nkjyj002","officePhone":"022-23456789","mailListShowIf":1,"sortNum":"0006","rankingIf":1,"forwardId":"nkjyj002","functionInfo":"职能信息","createTime":"2020-03-04 16:31:32","inspectorEncoding":"","departType":""}]
         */

        private String id;
        private String regionCode;
        private String dName;
        private String dAccount;
        private String dNum;
        private String sex;
        private String birthDate;
        private String organizationCode;
        private String rankingIf;
        private String startStatus;
        private String accountSafeLevel;
        private String job_name;
        private String job_level;
        private String labelType;
        private String password;
        private String remark;
        private String qrCodeString;
        private String userPhoto;
        private String workPercentage;
        private String creditPercentage;
        private String seniorSalfSetif;
        private String mailPhoneShowif;
        private String leapfrogSendif;
        private String rankingStatisticsif;
        private String salt;
        private String delIf;
        private String joinUser;
        private String tiePhone;
        private String lookHand;
        private String authorization;
        private String privateConnect;
        private String messagePush;
        private String warning;
        private String scheduleReminder;
        private String handSign;
        private String fontSize;
        private String deparentCode;
        private List<ListDepartmentBean> listDepartment;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(String regionCode) {
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getOrganizationCode() {
            return organizationCode;
        }

        public void setOrganizationCode(String organizationCode) {
            this.organizationCode = organizationCode;
        }

        public String getRankingIf() {
            return rankingIf;
        }

        public void setRankingIf(String rankingIf) {
            this.rankingIf = rankingIf;
        }

        public String getStartStatus() {
            return startStatus;
        }

        public void setStartStatus(String startStatus) {
            this.startStatus = startStatus;
        }

        public String getAccountSafeLevel() {
            return accountSafeLevel;
        }

        public void setAccountSafeLevel(String accountSafeLevel) {
            this.accountSafeLevel = accountSafeLevel;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getJob_level() {
            return job_level;
        }

        public void setJob_level(String job_level) {
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getQrCodeString() {
            return qrCodeString;
        }

        public void setQrCodeString(String qrCodeString) {
            this.qrCodeString = qrCodeString;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getWorkPercentage() {
            return workPercentage;
        }

        public void setWorkPercentage(String workPercentage) {
            this.workPercentage = workPercentage;
        }

        public String getCreditPercentage() {
            return creditPercentage;
        }

        public void setCreditPercentage(String creditPercentage) {
            this.creditPercentage = creditPercentage;
        }

        public String getSeniorSalfSetif() {
            return seniorSalfSetif;
        }

        public void setSeniorSalfSetif(String seniorSalfSetif) {
            this.seniorSalfSetif = seniorSalfSetif;
        }

        public String getMailPhoneShowif() {
            return mailPhoneShowif;
        }

        public void setMailPhoneShowif(String mailPhoneShowif) {
            this.mailPhoneShowif = mailPhoneShowif;
        }

        public String getLeapfrogSendif() {
            return leapfrogSendif;
        }

        public void setLeapfrogSendif(String leapfrogSendif) {
            this.leapfrogSendif = leapfrogSendif;
        }

        public String getRankingStatisticsif() {
            return rankingStatisticsif;
        }

        public void setRankingStatisticsif(String rankingStatisticsif) {
            this.rankingStatisticsif = rankingStatisticsif;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getDelIf() {
            return delIf;
        }

        public void setDelIf(String delIf) {
            this.delIf = delIf;
        }

        public String getJoinUser() {
            return joinUser;
        }

        public void setJoinUser(String joinUser) {
            this.joinUser = joinUser;
        }

        public String getTiePhone() {
            return tiePhone;
        }

        public void setTiePhone(String tiePhone) {
            this.tiePhone = tiePhone;
        }

        public String getLookHand() {
            return lookHand;
        }

        public void setLookHand(String lookHand) {
            this.lookHand = lookHand;
        }

        public String getAuthorization() {
            return authorization;
        }

        public void setAuthorization(String authorization) {
            this.authorization = authorization;
        }

        public String getPrivateConnect() {
            return privateConnect;
        }

        public void setPrivateConnect(String privateConnect) {
            this.privateConnect = privateConnect;
        }

        public String getMessagePush() {
            return messagePush;
        }

        public void setMessagePush(String messagePush) {
            this.messagePush = messagePush;
        }

        public String getWarning() {
            return warning;
        }

        public void setWarning(String warning) {
            this.warning = warning;
        }

        public String getScheduleReminder() {
            return scheduleReminder;
        }

        public void setScheduleReminder(String scheduleReminder) {
            this.scheduleReminder = scheduleReminder;
        }

        public String getHandSign() {
            return handSign;
        }

        public void setHandSign(String handSign) {
            this.handSign = handSign;
        }

        public String getFontSize() {
            return fontSize;
        }

        public void setFontSize(String fontSize) {
            this.fontSize = fontSize;
        }

        public String getDeparentCode() {
            return deparentCode;
        }

        public void setDeparentCode(String deparentCode) {
            this.deparentCode = deparentCode;
        }

        public List<ListDepartmentBean> getListDepartment() {
            return listDepartment;
        }

        public void setListDepartment(List<ListDepartmentBean> listDepartment) {
            this.listDepartment = listDepartment;
        }

        public static class ListDepartmentBean {
            /**
             * id : 1235120679131725825
             * parentId : 0
             * provinceCode : 2
             * cityCode : 3
             * countyCode : 8
             * departFullName : 南开区教育局002
             * departLevel : 1
             * simpleName : 南开教育局002
             * creditCode : 002
             * archives : 002
             * oneOfficialNum : 一级公文号
             * twoOfficialNum : 二级公文号
             * encodingRidge : nkjyj002
             * officePhone : 022-23456789
             * mailListShowIf : 1
             * sortNum : 0006
             * rankingIf : 1
             * forwardId : nkjyj002
             * functionInfo : 职能信息
             * createTime : 2020-03-04 16:31:32
             * inspectorEncoding :
             * departType :
             */

            private String id;
            private String parentId;
            private String provinceCode;
            private String cityCode;
            private String countyCode;
            private String departFullName;
            private String departLevel;
            private String simpleName;
            private String creditCode;
            private String archives;
            private String oneOfficialNum;
            private String twoOfficialNum;
            private String encodingRidge;
            private String officePhone;
            private String mailListShowIf;
            private String sortNum;
            private String rankingIf;
            private String forwardId;
            private String functionInfo;
            private String createTime;
            private String inspectorEncoding;
            private String departType;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            public String getCityCode() {
                return cityCode;
            }

            public void setCityCode(String cityCode) {
                this.cityCode = cityCode;
            }

            public String getCountyCode() {
                return countyCode;
            }

            public void setCountyCode(String countyCode) {
                this.countyCode = countyCode;
            }

            public String getDepartFullName() {
                return departFullName;
            }

            public void setDepartFullName(String departFullName) {
                this.departFullName = departFullName;
            }

            public String getDepartLevel() {
                return departLevel;
            }

            public void setDepartLevel(String departLevel) {
                this.departLevel = departLevel;
            }

            public String getSimpleName() {
                return simpleName;
            }

            public void setSimpleName(String simpleName) {
                this.simpleName = simpleName;
            }

            public String getCreditCode() {
                return creditCode;
            }

            public void setCreditCode(String creditCode) {
                this.creditCode = creditCode;
            }

            public String getArchives() {
                return archives;
            }

            public void setArchives(String archives) {
                this.archives = archives;
            }

            public String getOneOfficialNum() {
                return oneOfficialNum;
            }

            public void setOneOfficialNum(String oneOfficialNum) {
                this.oneOfficialNum = oneOfficialNum;
            }

            public String getTwoOfficialNum() {
                return twoOfficialNum;
            }

            public void setTwoOfficialNum(String twoOfficialNum) {
                this.twoOfficialNum = twoOfficialNum;
            }

            public String getEncodingRidge() {
                return encodingRidge;
            }

            public void setEncodingRidge(String encodingRidge) {
                this.encodingRidge = encodingRidge;
            }

            public String getOfficePhone() {
                return officePhone;
            }

            public void setOfficePhone(String officePhone) {
                this.officePhone = officePhone;
            }

            public String getMailListShowIf() {
                return mailListShowIf;
            }

            public void setMailListShowIf(String mailListShowIf) {
                this.mailListShowIf = mailListShowIf;
            }

            public String getSortNum() {
                return sortNum;
            }

            public void setSortNum(String sortNum) {
                this.sortNum = sortNum;
            }

            public String getRankingIf() {
                return rankingIf;
            }

            public void setRankingIf(String rankingIf) {
                this.rankingIf = rankingIf;
            }

            public String getForwardId() {
                return forwardId;
            }

            public void setForwardId(String forwardId) {
                this.forwardId = forwardId;
            }

            public String getFunctionInfo() {
                return functionInfo;
            }

            public void setFunctionInfo(String functionInfo) {
                this.functionInfo = functionInfo;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getInspectorEncoding() {
                return inspectorEncoding;
            }

            public void setInspectorEncoding(String inspectorEncoding) {
                this.inspectorEncoding = inspectorEncoding;
            }

            public String getDepartType() {
                return departType;
            }

            public void setDepartType(String departType) {
                this.departType = departType;
            }
        }
    }
}
