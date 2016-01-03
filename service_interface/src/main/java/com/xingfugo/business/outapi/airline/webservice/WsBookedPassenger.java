package com.xingfugo.business.outapi.airline.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for wsBookedPassenger complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wsBookedPassenger&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;airportTax&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;birthday&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;fuelTax&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;identityNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;identityType&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;name&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;parPrice&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param2&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param3&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param4&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;settlePrice&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;type&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsBookedPassenger", propOrder = { "airportTax", "birthday",
		"fuelTax", "identityNo", "identityType", "name", "parPrice", "param1",
		"param2", "param3", "param4", "settlePrice", "type" })
public class WsBookedPassenger {

	protected Double airportTax;
	protected String birthday;
	protected Double fuelTax;
	protected String identityNo;
	protected String identityType;
	protected String name;
	protected Double parPrice;
	protected String param1;
	protected String param2;
	protected String param3;
	protected String param4;
	protected Double settlePrice;
	protected String type;

	/**
	 * Gets the value of the airportTax property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getAirportTax() {
		return airportTax;
	}

	/**
	 * Sets the value of the airportTax property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setAirportTax(Double value) {
		this.airportTax = value;
	}

	/**
	 * Gets the value of the birthday property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Sets the value of the birthday property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBirthday(String value) {
		this.birthday = value;
	}

	/**
	 * Gets the value of the fuelTax property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getFuelTax() {
		return fuelTax;
	}

	/**
	 * Sets the value of the fuelTax property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setFuelTax(Double value) {
		this.fuelTax = value;
	}

	/**
	 * Gets the value of the identityNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdentityNo() {
		return identityNo;
	}

	/**
	 * Sets the value of the identityNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdentityNo(String value) {
		this.identityNo = value;
	}

	/**
	 * Gets the value of the identityType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdentityType() {
		return identityType;
	}

	/**
	 * Sets the value of the identityType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdentityType(String value) {
		this.identityType = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the parPrice property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getParPrice() {
		return parPrice;
	}

	/**
	 * Sets the value of the parPrice property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setParPrice(Double value) {
		this.parPrice = value;
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

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setType(String value) {
		this.type = value;
	}

}
