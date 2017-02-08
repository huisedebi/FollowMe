package com.Mystar.www.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/23.
 */
/*返回固定参数
        status      1跳注册成功页面   0提示info内容
        info        为返回的提示信息

        返回的数据
        uid   用户id
        headImg   用户头像
        userName  用户名
        userType  用户类型  0为普通用户　1为商户*/
public class UserInfoBean implements Serializable {
    private int status;
    private String info;

    private String uid;
    private String headImg;
    private String userName;
    private String userType;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
