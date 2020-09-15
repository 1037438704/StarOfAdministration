package com.lawe.starofadministration.bean;

import java.util.List;

/**
 * author : fuke
 * date : 2020/9/15 10:14
 * description : 个人信息
 */
public class PersonMessBean {


    /**
     * msg : success
     * code : 0
     * map : {"policy_percentage":"0","credit_percentage":"0","unit":"其他机构","d_name":"李四","job_name":"书记","work_percentage":"0","depart":"南开区教育局002","list":[{"id":"1237946831187492865","departmentUserId":"1235099783191703555","achieveTypeId":"39","awardType":1,"createUser":"1","createTime":"2020-03-12 11:41:39","medalPhoto":"http://60.205.202.177:8081/static/medal/medal_small.png","medalName":"优秀党委书记勋章"},{"id":"1237922371823550466","departmentUserId":"1235099783191703555","achieveTypeId":"11","awardType":1,"createUser":"1235754208570691585","createTime":"2020-03-12 10:04:28","medalPhoto":"http://60.205.202.177:8081/static/medal/medal_small.png","medalName":"月度优秀公务员"},{"id":"1237922372041654274","departmentUserId":"1235099783191703555","achieveTypeId":"23","awardType":1,"createUser":"1235754208570691585","createTime":"2020-03-12 10:04:28","medalPhoto":"http://60.205.202.177:8081/static/medal/medal_small.png","medalName":"优秀共产党员"}],"user_photo":"","age":29}
     */

    private String msg;
    private int code;
    private MapBean map;

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

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public static class MapBean {
        /**
         * policy_percentage : 0
         * credit_percentage : 0
         * unit : 其他机构
         * d_name : 李四
         * job_name : 书记
         * work_percentage : 0
         * depart : 南开区教育局002
         * list : [{"id":"1237946831187492865","departmentUserId":"1235099783191703555","achieveTypeId":"39","awardType":1,"createUser":"1","createTime":"2020-03-12 11:41:39","medalPhoto":"http://60.205.202.177:8081/static/medal/medal_small.png","medalName":"优秀党委书记勋章"},{"id":"1237922371823550466","departmentUserId":"1235099783191703555","achieveTypeId":"11","awardType":1,"createUser":"1235754208570691585","createTime":"2020-03-12 10:04:28","medalPhoto":"http://60.205.202.177:8081/static/medal/medal_small.png","medalName":"月度优秀公务员"},{"id":"1237922372041654274","departmentUserId":"1235099783191703555","achieveTypeId":"23","awardType":1,"createUser":"1235754208570691585","createTime":"2020-03-12 10:04:28","medalPhoto":"http://60.205.202.177:8081/static/medal/medal_small.png","medalName":"优秀共产党员"}]
         * user_photo :
         * age : 29
         */

        private int policy_percentage;
        private int credit_percentage;
        private String unit;
        private String d_name;
        private String job_name;
        private int work_percentage;
        private String depart;
        private String user_photo;
        private int age;
        private List<ListBean> list;

        public int getPolicy_percentage() {
            return policy_percentage;
        }

        public void setPolicy_percentage(int policy_percentage) {
            this.policy_percentage = policy_percentage;
        }

        public int getCredit_percentage() {
            return credit_percentage;
        }

        public void setCredit_percentage(int credit_percentage) {
            this.credit_percentage = credit_percentage;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getD_name() {
            return d_name;
        }

        public void setD_name(String d_name) {
            this.d_name = d_name;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public int getWork_percentage() {
            return work_percentage;
        }

        public void setWork_percentage(int work_percentage) {
            this.work_percentage = work_percentage;
        }

        public String getDepart() {
            return depart;
        }

        public void setDepart(String depart) {
            this.depart = depart;
        }

        public String getUser_photo() {
            return user_photo;
        }

        public void setUser_photo(String user_photo) {
            this.user_photo = user_photo;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1237946831187492865
             * departmentUserId : 1235099783191703555
             * achieveTypeId : 39
             * awardType : 1
             * createUser : 1
             * createTime : 2020-03-12 11:41:39
             * medalPhoto : http://60.205.202.177:8081/static/medal/medal_small.png
             * medalName : 优秀党委书记勋章
             */

            private String id;
            private String departmentUserId;
            private String achieveTypeId;
            private int awardType;
            private String createUser;
            private String createTime;
            private String medalPhoto;
            private String medalName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDepartmentUserId() {
                return departmentUserId;
            }

            public void setDepartmentUserId(String departmentUserId) {
                this.departmentUserId = departmentUserId;
            }

            public String getAchieveTypeId() {
                return achieveTypeId;
            }

            public void setAchieveTypeId(String achieveTypeId) {
                this.achieveTypeId = achieveTypeId;
            }

            public int getAwardType() {
                return awardType;
            }

            public void setAwardType(int awardType) {
                this.awardType = awardType;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getMedalPhoto() {
                return medalPhoto;
            }

            public void setMedalPhoto(String medalPhoto) {
                this.medalPhoto = medalPhoto;
            }

            public String getMedalName() {
                return medalName;
            }

            public void setMedalName(String medalName) {
                this.medalName = medalName;
            }
        }
    }
}
