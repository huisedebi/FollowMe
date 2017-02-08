package com.Mystar.www.url;

import com.Mystar.www.util.AppUtil;

/**
 * Created by Administrator on 2016/11/23.
 */

public class MyUrl {
    //http://192.168.0.125/Api/login/register.html

    // public final static String URL ="http://192.168.1.125/";
    public final static String URL = "http://110.87.111.31:8080/index.php?key="+ AppUtil.getMD5Time();
    public final static String NURL = "http://106.14.26.15/index.php?key="+ AppUtil.getMD5Time();
    //注册验证码
    public final static String REGISTERSMS = NURL + "&g=App&m=Login&a=regSms";
    //注册
    public final static String REGISTER = NURL + "&g=App&m=Login&a=register";
    //登录
    public final static String LOGIN = NURL + "&g=App&m=Login&a=index";
    //主页
    public final static String INDEX = NURL + "&g=App&m=Index&a=index";


}
