package com.kyy.recuperationcourt.common.model.dto;

import com.kyy.recuperationcourt.common.model.entity.user.SysUser;

public class SysUserTokenDto {

    private SysUser sysUser;

    private String token;

    private String type;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SysUserTokenDto{" +
                "sysUser=" + sysUser +
                ", token='" + token + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
