package com.kyy.recuperationcourt.common.model.entity.elder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <p>
 *
 * </p>
 *
 * @author DefterosChen
 * @since 2020-06-08
 */

public class UserElder implements Parcelable {

    private static final long serialVersionUID=1L;

    public UserElder() {
    }
    private Integer id;

    private String name;

    private String password;

    private String address;

    private Integer sex;

    private String registerTime;

    private String mobile;

    private String birth;

    private String updateTime;

    private String identityCard;

    private Integer isDelete;

    private String residenceAddress;

    private Integer companyId;

    private String remarks;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "UserElder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", sex=" + sex +
                ", registerTime='" + registerTime + '\'' +
                ", mobile='" + mobile + '\'' +
                ", birth='" + birth + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", isDelete=" + isDelete +
                ", residenceAddress='" + residenceAddress + '\'' +
                ", companyId=" + companyId +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
