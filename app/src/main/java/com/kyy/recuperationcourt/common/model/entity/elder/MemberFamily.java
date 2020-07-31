package com.kyy.recuperationcourt.common.model.entity.elder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChWang
 * @since 2020-07-02
 */

public class MemberFamily implements Parcelable {


    /**
     * id
     */
    private Integer id;

    /**
     * 与老人关系
     */
    private String relationship;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 现居住地址
     */
    private String address;

    /**
     * 老人id
     */
    private Integer userElderId;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserElderId() {
        return userElderId;
    }

    public void setUserElderId(Integer userElderId) {
        this.userElderId = userElderId;
    }

    @Override
    public String toString() {
        return "MemberFamily{" +
                "id=" + id +
                ", relationship='" + relationship + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", userElderId=" + userElderId +
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
