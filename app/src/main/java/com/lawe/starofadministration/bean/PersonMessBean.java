package com.lawe.starofadministration.bean;

/**
 * author : fuke
 * date : 2020/9/15 10:14
 * description : 个人信息
 */
public class PersonMessBean {

    /**
     * msg : success
     * code : 0
     * map : {"policy_percentage":"0","credit_percentage":"0","unit":"其他机构","d_name":"啦啦啦","job_name":null,"work_percentage":"0","depart":"南开区教育局002","user_photo":null,"age":7}
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
         * d_name : 啦啦啦
         * job_name : null
         * work_percentage : 0
         * depart : 南开区教育局002
         * user_photo : null
         * age : 7
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
    }
}
