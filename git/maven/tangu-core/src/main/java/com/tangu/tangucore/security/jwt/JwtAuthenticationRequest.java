package com.tangu.tangucore.security.jwt;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author fenglei on 8/29/17.
 */
@SuppressWarnings("ALL")
public class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -8445943548965154778L;

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "账套")
    private String tenant;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    //有几个老系统还是以userCode/passwordden登录的
    public String getUserCode() {
        return username;
    }

    public void setUserCode(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
