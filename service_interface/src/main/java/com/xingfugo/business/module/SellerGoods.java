package com.xingfugo.business.module;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class SellerGoods {
    private Integer goodsId;

    private Integer custId;

    @NotBlank
    private String catAttr;

    private String sizeAttr;
    
    //自定义分类
    private String membercat;

    @NotBlank
    @Length(max=100)
    private String goodsName;

    private String goodsNo;
    @Length(max=100)
    private String goodsWd;

    private Integer brand_id;
    @Length(max=200)
    private String goodsDesc;

    @NotBlank
    private String imgPath;

    private String goodsVideo;

    private String selfCat;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date upDate = null;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date downDate = null;

    @Min(0)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal marketPrice = BigDecimal.ZERO;

    @NotNull
    @Min(0)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal salePrice = BigDecimal.ZERO;
    
    @Min(0)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal costPrice = BigDecimal.ZERO;

    private String memPrice;

    private Integer totalStock = 0;

	@NotNull
    @Min(0)
    @Max(999999)
    private Integer nowStock = 0;

    @Min(0)
    private Integer warnStock = 0;

    private Integer saledNum = 0;

    private Integer giveInter;

    private Integer interbuy;

    private Float weight;

    private String unit;

    //运费计算类型:0：免运费，1：固定值
    private String isShip = "0";

    //可否使用优惠券：0：是，1：否
    private String isVolume = "0";

    private String seoTitel;

    private String seoKeyword;

    private String seoDesc;

    private String relateGoods;

    private String give;

    private String label = "";
    @Length(max=300)
    private String busiRemark;

    //0，是；1，否
    private String isDel = "1";

    private String noReason;

    //状态：0：未审核；1：正常；2：审核未通过；3：禁用
    private String infoState = "1";

    private Date inDate;

    private Integer userId;

    private Float volume;

    private String infoattrId;

    //是否为虚拟商品：0：是，1：否
    @NotNull
    private String isVirtual = "1";

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCatAttr() {
        return catAttr;
    }

    public void setCatAttr(String catAttr) {
        this.catAttr = catAttr;
    }

    public String getSizeAttr() {
        return sizeAttr;
    }

    public void setSizeAttr(String sizeAttr) {
        this.sizeAttr = sizeAttr;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsWd() {
        return goodsWd;
    }

    public void setGoodsWd(String goodsWd) {
        this.goodsWd = goodsWd;
    }

    public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getGoodsVideo() {
        return goodsVideo;
    }

    public void setGoodsVideo(String goodsVideo) {
        this.goodsVideo = goodsVideo;
    }

    public String getSelfCat() {
        return selfCat;
    }

    public void setSelfCat(String selfCat) {
        this.selfCat = selfCat;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public Date getDownDate() {
        return downDate;
    }

    public void setDownDate(Date downDate) {
        this.downDate = downDate;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getMemPrice() {
        return memPrice;
    }

    public void setMemPrice(String memPrice) {
        this.memPrice = memPrice;
    }

    public Integer getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Integer totalStock) {
        this.totalStock = totalStock;
    }

    public Integer getNowStock() {
        return nowStock;
    }

    public void setNowStock(Integer nowStock) {
        this.nowStock = nowStock;
    }

    public Integer getWarnStock() {
        return warnStock;
    }

    public void setWarnStock(Integer warnStock) {
        this.warnStock = warnStock;
    }

    public Integer getSaledNum() {
        return saledNum;
    }

    public void setSaledNum(Integer saledNum) {
        this.saledNum = saledNum;
    }

    public Integer getGiveInter() {
        return giveInter;
    }

    public void setGiveInter(Integer giveInter) {
        this.giveInter = giveInter;
    }

    public Integer getInterbuy() {
        return interbuy;
    }

    public void setInterbuy(Integer interbuy) {
        this.interbuy = interbuy;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIsShip() {
        return isShip;
    }

    public void setIsShip(String isShip) {
        this.isShip = isShip;
    }

    public String getIsVolume() {
        return isVolume;
    }

    public void setIsVolume(String isVolume) {
        this.isVolume = isVolume;
    }

    public String getSeoTitel() {
        return seoTitel;
    }

    public void setSeoTitel(String seoTitel) {
        this.seoTitel = seoTitel;
    }

    public String getSeoKeyword() {
        return seoKeyword;
    }

    public void setSeoKeyword(String seoKeyword) {
        this.seoKeyword = seoKeyword;
    }

    public String getSeoDesc() {
        return seoDesc;
    }

    public void setSeoDesc(String seoDesc) {
        this.seoDesc = seoDesc;
    }

    public String getRelateGoods() {
        return relateGoods;
    }

    public void setRelateGoods(String relateGoods) {
        this.relateGoods = relateGoods;
    }

    public String getGive() {
        return give;
    }

    public void setGive(String give) {
        this.give = give;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBusiRemark() {
        return busiRemark;
    }

    public void setBusiRemark(String busiRemark) {
        this.busiRemark = busiRemark;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getNoReason() {
        return noReason;
    }

    public void setNoReason(String noReason) {
        this.noReason = noReason;
    }

    public String getInfoState() {
        return infoState;
    }

    public void setInfoState(String infoState) {
        this.infoState = infoState;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public String getInfoattrId() {
        return infoattrId;
    }

    public void setInfoattrId(String infoattrId) {
        this.infoattrId = infoattrId;
    }

    public String getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(String isVirtual) {
        this.isVirtual = isVirtual;
    }

	public String getMembercat() {
		return membercat;
	}

	public void setMembercat(String membercat) {
		this.membercat = membercat;
	}
    
}