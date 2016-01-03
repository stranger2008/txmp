package com.xingfugo.business.outapi.airline.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for wsPaymentInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wsPaymentInfo&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;param1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param2&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param3&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param4&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;paymentUrl&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;settlePrice&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsPaymentInfo", propOrder = { "param1", "param2", "param3",
		"param4", "paymentUrl", "settlePrice" })
public class WsPaymentInfo {

	protected String param1;
	protected String param2;
	protected String param3;
	protected String param4;
	protected String paymentUrl;
	protected Double settlePrice;

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
	 * Gets the value of the paymentUrl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPaymentUrl() {
		return paymentUrl;
	}

	/**
	 * Sets the value of the paymentUrl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPaymentUrl(String value) {
		this.paymentUrl = value;
	}

	/**
	 * Gets the value of the settlePrice property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getSettlePrice() {
		return settlePrice;
	}

	/**
	 * Sets the value of the settlePrice property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setSettlePrice(Double value) {
		this.settlePrice = value;
	}

}
