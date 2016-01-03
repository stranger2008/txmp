package com.xingfugo.business.outapi.airline.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for wsFlightInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wsFlightInfo&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;arrCode&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;arrTime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;depCode&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;depDate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;depTime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;flightNo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param2&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param3&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param4&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;planeModel&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;seatClass&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;seatDiscount&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsFlightInfo", propOrder = { "arrCode", "arrTime", "depCode",
		"depDate", "depTime", "flightNo", "param1", "param2", "param3",
		"param4", "planeModel", "seatClass", "seatDiscount" })
public class WsFlightInfo {

	protected String arrCode;
	protected String arrTime;
	protected String depCode;
	protected String depDate;
	protected String depTime;
	protected String flightNo;
	protected String param1;
	protected String param2;
	protected String param3;
	protected String param4;
	protected String planeModel;
	protected String seatClass;
	protected Double seatDiscount;

	/**
	 * Gets the value of the arrCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArrCode() {
		return arrCode;
	}

	/**
	 * Sets the value of the arrCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setArrCode(String value) {
		this.arrCode = value;
	}

	/**
	 * Gets the value of the arrTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArrTime() {
		return arrTime;
	}

	/**
	 * Sets the value of the arrTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setArrTime(String value) {
		this.arrTime = value;
	}

	/**
	 * Gets the value of the depCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDepCode() {
		return depCode;
	}

	/**
	 * Sets the value of the depCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDepCode(String value) {
		this.depCode = value;
	}

	/**
	 * Gets the value of the depDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDepDate() {
		return depDate;
	}

	/**
	 * Sets the value of the depDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDepDate(String value) {
		this.depDate = value;
	}

	/**
	 * Gets the value of the depTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDepTime() {
		return depTime;
	}

	/**
	 * Sets the value of the depTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDepTime(String value) {
		this.depTime = value;
	}

	/**
	 * Gets the value of the flightNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFlightNo() {
		return flightNo;
	}

	/**
	 * Sets the value of the flightNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFlightNo(String value) {
		this.flightNo = value;
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
	 * Gets the value of the planeModel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPlaneModel() {
		return planeModel;
	}

	/**
	 * Sets the value of the planeModel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPlaneModel(String value) {
		this.planeModel = value;
	}

	/**
	 * Gets the value of the seatClass property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSeatClass() {
		return seatClass;
	}

	/**
	 * Sets the value of the seatClass property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSeatClass(String value) {
		this.seatClass = value;
	}

	/**
	 * Gets the value of the seatDiscount property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getSeatDiscount() {
		return seatDiscount;
	}

	/**
	 * Sets the value of the seatDiscount property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setSeatDiscount(Double value) {
		this.seatDiscount = value;
	}

}
