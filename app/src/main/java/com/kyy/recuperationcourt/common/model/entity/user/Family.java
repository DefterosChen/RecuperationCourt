package com.kyy.recuperationcourt.common.model.entity.user;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChWang
 * @since 2020-07-01
 */
public class Family  {


    /**
     * 主键
     */
    private Integer id;

    /**
     * 称谓
     */
    private String appellation;

    /**
     * 家庭人数
     */
    private Integer numberPeople;

    /**
     * 家庭地址
     */
    private String homeAddress;

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

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public Integer getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(Integer numberPeople) {
        this.numberPeople = numberPeople;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Integer getUserElderId() {
        return userElderId;
    }

    public void setUserElderId(Integer userElderId) {
        this.userElderId = userElderId;
    }
}
