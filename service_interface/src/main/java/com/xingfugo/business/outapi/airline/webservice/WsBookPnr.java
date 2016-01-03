package com.xingfugo.business.outapi.airline.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for wsBookPnr complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;wsBookPnr&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;airportTax&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;fuelTax&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;parPrice&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param2&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param3&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param4&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;passengers&quot; type=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}wsBookPassenger&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;segments&quot; type=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}wsAirSegment&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsBookPnr", propOrder = { "airportTax", "fuelTax", "parPrice",
		"param1", "param2", "param3", "param4", "passengers", "segments" })
public class WsBookPnr {

	protected Double airportTax;
	protected Double fuelTax;
	protected Double parPrice;
	protected String param1;
	protected String param2;
	protected String param3;
	protected String param4;
	@XmlElement(nillable = true)
	protected List<WsBookPassenger> passengers;
	@XmlElement(nillable = true)
	protected List<WsAirSegment> segments;

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
	 * Gets the value of the passengers property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the passengers property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPassengers().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link WsBookPassenger }
	 * 
	 * 
	 */
	public List<WsBookPassenger> getPassengers() {
		if (passengers == null) {
			passengers = new ArrayList<WsBookPassenger>();
		}
		return this.passengers;
	}

	/**
	 * Gets the value of the segments property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the segments property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSegments().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link WsAirSegment }
	 * 
	 * 
	 */
	public List<WsAirSegment> getSegments() {
		if (segments == null) {
			segments = new ArrayList<WsAirSegment>();
		}
		return this.segments;
	}

	public void setPassengers(List<WsBookPassenger> passengers) {
		this.passengers = passengers;
	}

	public void setSegments(List<WsAirSegment> segments) {
		this.segments = segments;
	}

}
