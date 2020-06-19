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

    //公文拟制新建保存
    String DOCUMENT_SAVE = "/sys/documentfiction/save";

    //根据部门id拟编号
    String GETQUASINUMBER = "/sys/documentfiction/getQuasiNumber/";

    //查询公文拟制公文字号数值
    String GETNUMBER = "/sys/documentfiction/getNumber";

    //根据逐渐查询详情
    String DOCUMENTFICTION = "/sys/documentfiction/info";

    //查询字典表
    String BYTYPE = "/sys/dataDict/byType";

    //查询公文主题
    String LISTFINDALL = "/sys/documenttheme/listFindAll";

    //附件目录
    String UPLOADQUERYPAGE = "/sys/upload/queryPage";

    //上传附件
    String UPLOADFILE = "/sys/upload/uploadFile";

    //修改附件
    String UPDATEFILE = "/sys/upload/update";

    //删除附件
    String DELETEFUJIAN = "/sys/upload/delete/";

    //调用word模板
    String SHOWWORDTEMPLATE = "/sys/poffice/showWordTemplate";

    //调用word接口
    String SHOWWORD = "/sys/poffice/test";
}
