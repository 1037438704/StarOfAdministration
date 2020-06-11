package com.lawe.starofadministration.config;

public interface Constants {

    //默认登录
    String LOGIN = "/sys/login";

    //登陆协议
    String FINDUSERAGREEMENT = "/sys/config/findUserAgreement";

    //发送验证码
    String GETPASSWORDBYSENDNOTE = "/login/getPasswordBySendNote";

    //验证码验证
    String VALIDCODE = "/login/validCode";

    //首页待办信息
    String LISTFINDALLBYCURRENTUSER = "/sys/homepage/listFindAllByCurrentUser";

    //首页个人信息
    String QUERYPAGE = "/sys/center/queryPage";

    //公文拟制查询所有
    String DOCUMENT_QUERYPAGE = "/sys/documentfiction/queryPage";

    //根据逐渐查询详情
    String DOCUMENTFICTION = "/sys/documentfiction/info";

    //调用word模板
    String SHOWWORDTEMPLATE = "/sys/poffice/showWordTemplate";

    //调用word接口
    String SHOWWORD = "/sys/poffice/test";
}
