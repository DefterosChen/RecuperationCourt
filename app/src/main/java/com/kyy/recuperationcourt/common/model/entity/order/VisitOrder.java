package com.kyy.recuperationcourt.common.model.entity.order;

public class VisitOrder {

    private static final long serialVersionUID=1L;

    private Integer id;

    /**
     * 工单号
     */
    private String orderId;

    /**
     * 回访内容
     */
    private String content;

    /**
     * 创建时间
     */
    private String creatTime;

    /**
     * 服务项
     */
    private Integer serviceId;

    /**
     * 服务项
     */
    private String serviceName;

    /**
     * 公司
     */
    private Integer companyId;


    /**
     * 回访人员
     */
    private Integer visitManId;

    /**
     * 回访人员
     */
    private String visitManName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getVisitManId() {
        return visitManId;
    }

    public void setVisitManId(Integer visitManId) {
        this.visitManId = visitManId;
    }

    public String getVisitManName() {
        return visitManName;
    }

    public void setVisitManName(String visitManName) {
        this.visitManName = visitManName;
    }

    @Override
    public String toString() {
        return "VisitOrder{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", content='" + content + '\'' +
                ", creatTime='" + creatTime + '\'' +
                ", serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", companyId=" + companyId +
                ", visitManId=" + visitManId +
                ", visitManName='" + visitManName + '\'' +
                '}';
    }
}
