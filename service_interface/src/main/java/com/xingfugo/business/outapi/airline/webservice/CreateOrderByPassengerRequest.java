package com.xingfugo.business.outapi.airline.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for createOrderByPassengerRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;createOrderByPassengerRequest&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}abstractLiantuoRequest&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;allowSwitchPolicy&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;b2cClientPay&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;b2cProfit&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;createdBy&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;linkMan&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;linkPhone&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;needSpeRulePolicy&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;notifiedUrl&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;otherLinkMethod&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;outOrderNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param2&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param3&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param4&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param5&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;paymentReturnUrl&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;pnrInfo&quot; type=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}wsBookPnr&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;policyId&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrderByPassengerRequest", propOrder = {
		"allowSwitchPolicy", "b2CClientPay", "b2CProfit", "createdBy",
		"linkMan", "linkPhone", "needSpeRulePolicy", "notifiedUrl",
		"otherLinkMethod", "outOrderNo", "param1", "param2", "param3",
		"param4", "param5", "paymentReturnUrl", "pnrInfo", "policyId" })
public class CreateOrderByPassengerRequest extends AbstractLiantuoRequest {

	protected Integer allowSwitchPolicy;
	@XmlElement(name = "b2cClientPay")
	protected String b2CClientPay;
	@XmlElement(name = "b2cProfit")
	protected String b2CProfit;
	protected String createdBy;
	protected String linkMan;
	protected String linkPhone;
	protected Integer needSpeRulePolicy;
	protected String notifiedUrl;
	protected String otherLinkMethod;
	protected String outOrderNo;
	protected String param1;
	protected String param2;
	protected String param3;
	protected String param4;
	protected String param5;
	protected String paymentReturnUrl;
	protected WsBookPnr pnrInfo;
	protected String policyId;

	/**
	 * Gets the value of the allowSwitchPolicy property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getAllowSwitchPolicy() {
		return allowSwitchPolicy;
	}

	/**
	 * Sets the value of the allowSwitchPolicy property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setAllowSwitchPolicy(Integer value) {
		this.allowSwitchPolicy = value;
	}

	/**
	 * Gets the value of the b2CClientPay property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getB2CClientPay() {
		return b2CClientPay;
	}

	/**
	 * Sets the value of the b2CClientPay property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setB2CClientPay(String value) {
		this.b2CClientPay = value;
	}

	/**
	 * Gets the value of the b2CProfit property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getB2CProfit() {
		return b2CProfit;
	}

	/**
	 * Sets the value of the b2CProfit property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setB2CProfit(String value) {
		this.b2CProfit = value;
	}

	/**
	 * Gets the value of the createdBy property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the value of the createdBy property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatedBy(String value) {
		this.createdBy = value;
	}

	/**
	 * Gets the value of the linkMan property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLinkMan() {
		return linkMan;
	}

	/**
	 * Sets the value of the linkMan property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLinkMan(String value) {
		this.linkMan = value;
	}

	/**
	 * Gets the value of the linkPhone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLinkPhone() {
		return linkPhone;
	}

	/**
	 * Sets the value of the linkPhone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLinkPhone(String value) {
		this.linkPhone = value;
	}

	/**
	 * Gets the value of the needSpeRulePolicy property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getNeedSpeRulePolicy() {
		return needSpeRulePolicy;
	}

	/**
	 * Sets the value of the needSpeRulePolicy property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setNeedSpeRulePolicy(Integer value) {
		this.needSpeRulePolicy = value;
	}

	/**
	 * Gets the value of the notifiedUrl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNotifiedUrl() {
		return notifiedUrl;
	}

	/**
	 * Sets the value of the notifiedUrl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNotifiedUrl(String value) {
		this.notifiedUrl = value;
	}

	/**
	 * Gets the value of the otherLinkMethod property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOtherLinkMethod() {
		return otherLinkMethod;
	}

	/**
	 * Sets the value of the otherLinkMethod property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOtherLinkMethod(String value) {
		this.otherLinkMethod = value;
	}

	/**
	 * Gets the value of the outOrderNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOutOrderNo() {
		return outOrderNo;
	}

	/**
	 * Sets the value of the outOrderNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOutOrderNo(String value) {
		this.outOrderNo = value;
	}

	/**
	 * Gets the value of the param1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParam1() {
		return param1;
	}

	/**
	 * Sets the value of the param1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParam1(String value) {
		this.param1 = value;
	}

	/**
	 * Gets the value of the param2 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParam2() {
		return param2;
	}

	/**
	 * Sets the value of the param2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParam2(String value) {
		this.param2 = value;
	}

	/**
	 * Gets the value of the param3 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParam3() {
		return param3;
	}

	/**
	 * Sets the value of the param3 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParam3(String value) {
		this.param3 = value;
	}

	/**
	 * Gets the value of the param4 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParam4() {
		return param4;
	}

	/**
	 * Sets the value of the param4 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParam4(String value) {
		this.param4 = value;
	}

	/**
	 * Gets the value of the param5 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParam5() {
		return param5;
	}

	/**
	 * Sets the value of the param5 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParam5(String value) {
		this.param5 = value;
	}

	/**
	 * Gets the value of the paymentReturnUrl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPaymentReturnUrl() {
		return paymentReturnUrl;
	}

	/**
	 * Sets the value of the paymentReturnUrl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPaymentReturnUrl(String value) {
		this.paymentReturnUrl = value;
	}

	/**
	 * Gets the value of the pnrInfo property.
	 * 
	 * @return possible object is {@link com.xingfugo.business.outapi.airline.webservice.WsBookPnr }
	 * 
	 */
	public WsBookPnr getPnrInfo() {
		return pnrInfo;
	}

	/**
	 * Sets the value of the pnrInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link com.xingfugo.business.outapi.airline.webservice.WsBookPnr }
	 * 
	 */
	public void setPnrInfo(WsBookPnr value) {
		this.pnrInfo = value;
	}

	/**
	 * Gets the value of the policyId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPolicyId() {
		return policyId;
	}

	/**
	 * Sets the value of the policyId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPolicyId(String value) {
		this.policyId = value;
	}

}
