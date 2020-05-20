package com.lawe.starofadministration.config;

public interface Constants {
    //默认登录
    String LOGIN = "/login/login";

    //登陆协议
    String FINDUSERAGREEMENT = "/sys/config/findUserAgreement";

    //发送验证码
    String GETPASSWORDBYSENDNOTE = "/login/getPasswordBySendNote";

    //验证码验证
    String VALIDCODE = "/login/validCode";

    //根据逐渐查询详情
    String DOCUMENTFICTION = "/sys/documentfiction/info";

    //调用word模板
    String SHOWWORDTEMPLATE = "/sys/poffice/showWordTemplate";

    //调用word接口
    String SHOWWORD = "/sys/poffice/showWord";
}
