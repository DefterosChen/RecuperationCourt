package com.kyy.recuperationcourt.common.model.entity.order;

import java.util.Date;

public class WorkOrder {


    private Integer id;

    /**
     * 工单号
     */
    private String workOrderId;

    /**
     * 所属公司id
     */
    private Integer companyId;

    /**
     * 工单类型
     */
    private Integer type;

    /**
     * 工单状态
     */
    private Integer state;

    /**
     * 服务项目
     */
    private Integer serviceItemId;

    /**
     * 服务项目
     */
    private String serviceItemName;


    /**
     * 工单内容
     */
    private String orderDes;

    /**
     * 创建人员
     */
    private Integer creatUserId;

    /**
     * 服务人员
     */
    private Integer serviceUserId;

    /**
     * 被服务长者
     */
    private Integer elderUserId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 到期时间
     */
    private String dueTime;

    private String creatUserName;

    private String serviceUserName;

    private String elderUserName;

    private String province;

    private String city;

    private String area;

    private String address;

    private Integer servicePrice;

    private Integer isVisited;

    private Long priceTotal;

    private Integer timeConsuming;

    private Integer serviceItemType;

    private Integer serviceItemCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(Integer serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public String getServiceItemName() {
        return serviceItemName;
    }

    public void setServiceItemName(String serviceItemName) {
        this.serviceItemName = serviceItemName;
    }

    public String getOrderDes() {
        return orderDes;
    }

    public void setOrderDes(String orderDes) {
        this.orderDes = orderDes;
    }

    public Integer getCreatUserId() {
        return creatUserId;
    }

    public void setCreatUserId(Integer creatUserId) {
        this.creatUserId = creatUserId;
    }

    public Integer getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(Integer serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    public Integer getElderUserId() {
        return elderUserId;
    }

    public void setElderUserId(Integer elderUserId) {
        this.elderUserId = elderUserId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getCreatUserName() {
        return creatUserName;
    }

    public void setCreatUserName(String creatUserName) {
        this.creatUserName = creatUserName;
    }

    public String getServiceUserName() {
        return serviceUserName;
    }

    public void setServiceUserName(String serviceUserName) {
        this.serviceUserName = serviceUserName;
    }

    public String getElderUserName() {
        return elderUserName;
    }

    public void setElderUserName(String elderUserName) {
        this.elderUserName = elderUserName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Integer servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Integer getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(Integer isVisited) {
        this.isVisited = isVisited;
    }

    public Long getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Long priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Integer getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(Integer timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    public Integer getServiceItemType() {
        return serviceItemType;
    }

    public void setServiceItemType(Integer serviceItemType) {
        this.serviceItemType = serviceItemType;
    }

    public Integer getServiceItemCategory() {
        return serviceItemCategory;
    }

    public void setServiceItemCategory(Integer serviceItemCategory) {
        this.serviceItemCategory = serviceItemCategory;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "id=" + id +
                ", workOrderId='" + workOrderId + '\'' +
                ", companyId=" + companyId +
                ", type=" + type +
                ", state=" + state +
                ", serviceItemId=" + serviceItemId +
                ", serviceItemName='" + serviceItemName + '\'' +
                ", orderDes='" + orderDes + '\'' +
                ", creatUserId=" + creatUserId +
                ", serviceUserId=" + serviceUserId +
                ", elderUserId=" + elderUserId +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", dueTime='" + dueTime + '\'' +
                ", creatUserName='" + creatUserName + '\'' +
                ", serviceUserName='" + serviceUserName + '\'' +
                ", elderUserName='" + elderUserName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", servicePrice=" + servicePrice +
                ", isVisited=" + isVisited +
                ", priceTotal=" + priceTotal +
                ", timeConsuming=" + timeConsuming +
                ", serviceItemType=" + serviceItemType +
                ", serviceItemCategory=" + serviceItemCategory +
                '}';
    }
}
