package com.kyy.recuperationcourt.common.model.entity.elder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChWang
 * @since 2020-06-17
 */

public class HealthManagement implements Parcelable {

    private static final long serialVersionUID=1L;

    private Integer id;

    /**
     * 长者id
     */
    private Integer elderId;

    /**
     * 长者姓名
     */
    private String elderName;

    /**
     * 创建时间
     */
    private String creatTime;

    private String updateTime;

    /**
     * 所属公司
     */
    private Integer companyId;

    /**
     * 体检描述
     */
    private String description;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 血压
     */
    private String bloodPressure;

    /**
     * 血糖
     */
    private String bloodSugar;

    /**
     * 尿酸
     */
    private String uricAcid;

    /**
     * 总胆固醇
     */
    private String cholesterolTotal;

    /**
     * 脉率
     */
    private String pulseRate;

    /**
     * 心率
     */
    private String heartRate;

    private Integer isDel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getElderId() {
        return elderId;
    }

    public void setElderId(Integer elderId) {
        this.elderId = elderId;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getUricAcid() {
        return uricAcid;
    }

    public void setUricAcid(String uricAcid) {
        this.uricAcid = uricAcid;
    }

    public String getCholesterolTotal() {
        return cholesterolTotal;
    }

    public void setCholesterolTotal(String cholesterolTotal) {
        this.cholesterolTotal = cholesterolTotal;
    }

    public String getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(String pulseRate) {
        this.pulseRate = pulseRate;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "HealthManagement{" +
                "id=" + id +
                ", elderId=" + elderId +
                ", elderName='" + elderName + '\'' +
                ", creatTime='" + creatTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", companyId=" + companyId +
                ", description='" + description + '\'' +
                ", height='" + height + '\'' +
                ", weight=" + weight +
                ", bloodPressure='" + bloodPressure + '\'' +
                ", bloodSugar='" + bloodSugar + '\'' +
                ", uricAcid='" + uricAcid + '\'' +
                ", cholesterolTotal='" + cholesterolTotal + '\'' +
                ", pulseRate='" + pulseRate + '\'' +
                ", heartRate='" + heartRate + '\'' +
                ", isDel=" + isDel +
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
