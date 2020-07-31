package com.kyy.recuperationcourt.common.model.dto;

/**
 * 根据订单获得当前老人经常选择的服务项的返回DTO
 */
public class OrderByItmeUserDto {

    // 使用次数
    private Integer count;

    // 老人名称
    private String elderName;

    // 服务项名称
    private String serviceName;

    // 服务项种类
    private Integer type;

    // 服务项价格
    private Integer price;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderByItmeUserDto{" +
                "count=" + count +
                ", elderName='" + elderName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}
