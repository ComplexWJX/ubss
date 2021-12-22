package com.itany.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.druid.support.json.JSONUtils;

public class Product implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -4133585468943520368L;
    
    private Integer productId;
    
    private String productName;
    
    private Double primeLendingRateFrom;
    
    private Double primeLendingRateTo;
    
    private Integer company;
    
    private Integer financingAmountFrom;
    
    private Integer financingAmountTo;
    
    private Integer productType;
    
    private Integer lendingPeriod;
    
    private String linkMan;
    
    private String ownedBank;
    
    private String productDescription;
    
    private Date creatTime;
    
    private Integer status;
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public Integer getProductId()
    {
        return productId;
    }
    
    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }
    
    public String getProductName()
    {
        return productName;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    
    public Double getPrimeLendingRateFrom()
    {
        return primeLendingRateFrom;
    }
    
    public void setPrimeLendingRateFrom(Double primeLendingRateFrom)
    {
        this.primeLendingRateFrom = primeLendingRateFrom;
    }
    
    public Double getPrimeLendingRateTo()
    {
        return primeLendingRateTo;
    }
    
    public void setPrimeLendingRateTo(Double primeLendingRateTo)
    {
        this.primeLendingRateTo = primeLendingRateTo;
    }
    
    public Integer getCompany()
    {
        return company;
    }
    
    public void setCompany(Integer company)
    {
        this.company = company;
    }
    
    public Integer getFinancingAmountFrom()
    {
        return financingAmountFrom;
    }
    
    public void setFinancingAmountFrom(Integer financingAmountFrom)
    {
        this.financingAmountFrom = financingAmountFrom;
    }
    
    public Integer getFinancingAmountTo()
    {
        return financingAmountTo;
    }
    
    public void setFinancingAmountTo(Integer financingAmountTo)
    {
        this.financingAmountTo = financingAmountTo;
    }
    
    public Integer getProductType()
    {
        return productType;
    }
    
    public void setProductType(Integer productType)
    {
        this.productType = productType;
    }
    
    public Integer getLendingPeriod()
    {
        return lendingPeriod;
    }
    
    public void setLendingPeriod(Integer lendingPeriod)
    {
        this.lendingPeriod = lendingPeriod;
    }
    
    public String getLinkMan()
    {
        return linkMan;
    }
    
    public void setLinkMan(String linkMan)
    {
        this.linkMan = linkMan;
    }
    
    public String getOwnedBank()
    {
        return ownedBank;
    }
    
    public void setOwnedBank(String ownedBank)
    {
        this.ownedBank = ownedBank;
    }
    
    public String getProductDescription()
    {
        return productDescription;
    }
    
    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }
    
    public Date getCreatTime()
    {
        return creatTime;
    }
    
    public void setCreatTime(Date creatTime)
    {
        this.creatTime = creatTime;
    }
    
    @Override
    public String toString()
    {
        return JSONUtils.toJSONString(this);
    }
    
}
