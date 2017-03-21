package com.zc.degou.model;

import java.io.Serializable;

/**
 * Created by lucifer on 17/1/7.
 *
 * @deprecated 用户信息数据结构
 */

public class UserInfoBean implements Serializable {

    private int userId = 0; // 用户id

    private String recom = "";  // 渠道号

    private String userName = "";  // 用户名

    private String domainName = "";  // 域名

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setRecom(String recom) {
        this.recom = recom;
    }

    public String getRecom() {
        return recom;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
    }

}
