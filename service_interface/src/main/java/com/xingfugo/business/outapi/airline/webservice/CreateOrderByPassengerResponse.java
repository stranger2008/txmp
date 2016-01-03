package com.xingfugo.business.outapi.airline.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for createOrderByPassengerResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;createOrderByPassengerResponse&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;return&quot; type=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}createOrderByPassengerReply&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrderByPassengerResponse", propOrder = { "_return" })
public class CreateOrderByPassengerResponse {

	@XmlElement(name = "return")
	protected CreateOrderByPassengerReply _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link com.xingfugo.business.outapi.airline.webservice.CreateOrderByPassengerReply }
	 * 
	 */
	public CreateOrderByPassengerReply getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link com.xingfugo.business.outapi.airline.webservice.CreateOrderByPassengerReply }
	 * 
	 */
	public void setReturn(CreateOrderByPassengerReply value) {
		this._return = value;
	}

}
