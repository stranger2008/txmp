package com.xingfugo.business.outapi.airline.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for wsPolicyOrder complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wsPolicyOrder&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;commisionPoint&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;createdAt&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;flightInfoList&quot; type=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}wsFlightInfo&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;increaseSystemCharge&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;liantuoOrderNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param2&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param3&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param4&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;passengerList&quot; type=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}wsBookedPassenger&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;paymentInfo&quot; type=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}wsPaymentInfo&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;pnrNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;policyId&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;status&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;supplyOfficeNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;systemAlipayAccount&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsPolicyOrder", propOrder = { "commisionPoint", "createdAt",
		"flightInfoList", "increaseSystemCharge", "liantuoOrderNo", "param1",
		"param2", "param3", "param4", "passengerList", "paymentInfo", "pnrNo",
		"policyId", "status", "supplyOfficeNo", "systemAlipayAccount" })
public class WsPolicyOrder {

	protected String commisionPoint;
	protected String createdAt;
	@XmlElement(nillable = true)
	protected List<WsFlightInfo> flightInfoList;
	protected String increaseSystemCharge;
	protected String liantuoOrderNo;
	protected String param1;
	protected String param2;
	protected String param3;
	protected String param4;
	@XmlElement(nillable = true)
	protected List<WsBookedPassenger> passengerList;
	protected WsPaymentInfo paymentInfo;
	protected String pnrNo;
	protected String policyId;
	protected String status;
	protected String supplyOfficeNo;
	protected String systemAlipayAccount;

	/**
	 * Gets the value of the commisionPoint property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCommisionPoint() {
		return commisionPoint;
	}

	/**
	 * Sets the value of the commisionPoint property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCommisionPoint(String value) {
		this.commisionPoint = value;
	}

	/**
	 * Gets the value of the createdAt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the value of the createdAt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatedAt(String value) {
		this.createdAt = value;
	}

	/**
	 * Gets the value of the flightInfoList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the flightInfoList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getFlightInfoList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link com.xingfugo.business.outapi.airline.webservice.WsFlightInfo }
	 * 
	 * 
	 */
	public List<WsFlightInfo> getFlightInfoList() {
		if (flightInfoList == null) {
			flightInfoList = new ArrayList<WsFlightInfo>();
		}
		return this.flightInfoList;
	}

	/**
	 * Gets the value of the increaseSystemCharge property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIncreaseSystemCharge() {
		return increaseSystemCharge;
	}

	/**
	 * Sets the value of the increaseSystemCharge property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIncreaseSystemCharge(String value) {
		this.increaseSystemCharge = value;
	}

	/**
	 * Gets the value of the liantuoOrderNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLiantuoOrderNo() {
		return liantuoOrderNo;
	}

	/**
	 * Sets the value of the liantuoOrderNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLiantuoOrderNo(String value) {
		this.liantuoOrderNo = value;
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
	 * Gets the value of the passengerList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the passengerList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPassengerList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link WsBookedPassenger }
	 * 
	 * 
	 */
	public List<WsBookedPassenger> getPassengerList() {
		if (passengerList == null) {
			passengerList = new ArrayList<WsBookedPassenger>();
		}
		return this.passengerList;
	}

	/**
	 * Gets the value of the paymentInfo property.
	 * 
	 * @return possible object is {@link com.xingfugo.business.outapi.airline.webservice.WsPaymentInfo }
	 * 
	 */
	public WsPaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * Sets the value of the paymentInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link com.xingfugo.business.outapi.airline.webservice.WsPaymentInfo }
	 * 
	 */
	public void setPaymentInfo(WsPaymentInfo value) {
		this.paymentInfo = value;
	}

	/**
	 * Gets the value of the pnrNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPnrNo() {
		return pnrNo;
	}

	/**
	 * Sets the value of the pnrNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPnrNo(String value) {
		this.pnrNo = value;
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

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

	/**
	 * Gets the value of the supplyOfficeNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSupplyOfficeNo() {
		return supplyOfficeNo;
	}

	/**
	 * Sets the value of the supplyOfficeNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSupplyOfficeNo(String value) {
		this.supplyOfficeNo = value;
	}

	/**
	 * Gets the value of the systemAlipayAccount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSystemAlipayAccount() {
		return systemAlipayAccount;
	}

	/**
	 * Sets the value of the systemAlipayAccount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSystemAlipayAccount(String value) {
		this.systemAlipayAccount = value;
	}

}
