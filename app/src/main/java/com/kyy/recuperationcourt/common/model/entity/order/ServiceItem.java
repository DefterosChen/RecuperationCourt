package com.kyy.recuperationcourt.common.model.entity.order;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * <p>
 *
 * </p>
 *
 * @author ChWang
 * @since 2020-06-08
 */
@SuppressLint("ParcelCreator")
public class ServiceItem implements Parcelable {

    private static final long serialVersionUID = 1L;

    private Integer serviceId;

    private String serviceName;

    /**
     * 服务项描述
     */
    private String serviceDes;

    private String creatTime;

    private Integer isDelete;

    private Integer companyId;

    private Integer price;

    private Integer cycle;

    private String updateTime;

    private Integer type;

    private Integer serviceCategory;


    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDes() {
        return serviceDes;
    }

    public void setServiceDes(String serviceDes) {
        this.serviceDes = serviceDes;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(Integer serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    @Override
    public String toString() {
        return "ServiceItem{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", serviceDes='" + serviceDes + '\'' +
                ", creatTime='" + creatTime + '\'' +
                ", isDelete=" + isDelete +
                ", companyId=" + companyId +
                ", price=" + price +
                ", cycle=" + cycle +
                ", updateTime='" + updateTime + '\'' +
                ", type=" + type +
                ", serviceCategory=" + serviceCategory +
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
