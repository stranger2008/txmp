package com.xingfugo.business.outapi.airline.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for createOrderByPassenger complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;createOrderByPassenger&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;request&quot; type=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}createOrderByPassengerRequest&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrderByPassenger", propOrder = { "request" })
public class CreateOrderByPassenger {

	protected CreateOrderByPassengerRequest request;

	/**
	 * Gets the value of the request property.
	 * 
	 * @return possible object is {@link CreateOrderByPassengerRequest }
	 * 
	 */
	public CreateOrderByPassengerRequest getRequest() {
		return request;
	}

	/**
	 * Sets the value of the request property.
	 * 
	 * @param value
	 *            allowed object is {@link CreateOrderByPassengerRequest }
	 * 
	 */
	public void setRequest(CreateOrderByPassengerRequest value) {
		this.request = value;
	}

}
