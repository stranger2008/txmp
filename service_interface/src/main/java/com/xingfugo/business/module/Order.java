package com.xingfugo.business.module;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String orderId;

    private Integer buyCustId;

    private Integer saleCustId;

    private String consignee;

    private String areaAttr;

    private String address;

    private String zipCode;

    private String telephone;

    private String mobile;

    private BigDecimal goodsAmount;

    private BigDecimal shipFree;

    private BigDecimal taxInvoice;

    private Float discount;

    private BigDecimal discountMoney;

    private String disExplain;

    private BigDecimal insured;

    private BigDecimal tatalAmount;

    private Integer interMoney;

    private String buyMode;

    private Integer payId;

    private String sendMode;

    private String orderState;

    private String payState;

    private String sendState;

    private Date orderTime;

    private Date payTime;

    private Date sendTime;

    private String memRemark;

    private String invoiceType;

    private String invoiceTop;

    private String companyName;

    private String identNumber;

    private String regisAddress;

    private String regisTel;

    private String openBank;

    private String bankAccount;

    private String invoiceContent;

    private Integer giveInter;

    private String orderType;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getBuyCustId() {
        return buyCustId;
    }

    public void setBuyCustId(Integer buyCustId) {
        this.buyCustId = buyCustId;
    }

    public Integer getSaleCustId() {
        return saleCustId;
    }

    public void setSaleCustId(Integer saleCustId) {
        this.saleCustId = saleCustId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAreaAttr() {
        return areaAttr;
    }

    public void setAreaAttr(String areaAttr) {
        this.areaAttr = areaAttr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getShipFree() {
        return shipFree;
    }

    public void setShipFree(BigDecimal shipFree) {
        this.shipFree = shipFree;
    }

    public BigDecimal getTaxInvoice() {
        return taxInvoice;
    }

    public void setTaxInvoice(BigDecimal taxInvoice) {
        this.taxInvoice = taxInvoice;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getDisExplain() {
        return disExplain;
    }

    public void setDisExplain(String disExplain) {
        this.disExplain = disExplain;
    }

    public BigDecimal getInsured() {
        return insured;
    }

    public void setInsured(BigDecimal insured) {
        this.insured = insured;
    }

    public BigDecimal getTatalAmount() {
        return tatalAmount;
    }

    public void setTatalAmount(BigDecimal tatalAmount) {
        this.tatalAmount = tatalAmount;
    }

    public Integer getInterMoney() {
        return interMoney;
    }

    public void setInterMoney(Integer interMoney) {
        this.interMoney = interMoney;
    }

    public String getBuyMode() {
        return buyMode;
    }

    public void setBuyMode(String buyMode) {
        this.buyMode = buyMode;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getSendMode() {
        return sendMode;
    }

    public void setSendMode(String sendMode) {
        this.sendMode = sendMode;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getSendState() {
        return sendState;
    }

    public void setSendState(String sendState) {
        this.sendState = sendState;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getMemRemark() {
        return memRemark;
    }

    public void setMemRemark(String memRemark) {
        this.memRemark = memRemark;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTop() {
        return invoiceTop;
    }

    public void setInvoiceTop(String invoiceTop) {
        this.invoiceTop = invoiceTop;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIdentNumber() {
        return identNumber;
    }

    public void setIdentNumber(String identNumber) {
        this.identNumber = identNumber;
    }

    public String getRegisAddress() {
        return regisAddress;
    }

    public void setRegisAddress(String regisAddress) {
        this.regisAddress = regisAddress;
    }

    public String getRegisTel() {
        return regisTel;
    }

    public void setRegisTel(String regisTel) {
        this.regisTel = regisTel;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public Integer getGiveInter() {
        return giveInter;
    }

    public void setGiveInter(Integer giveInter) {
        this.giveInter = giveInter;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}