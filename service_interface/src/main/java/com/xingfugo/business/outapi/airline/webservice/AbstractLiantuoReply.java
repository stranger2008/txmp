package com.xingfugo.business.outapi.airline.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for abstractLiantuoReply complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;abstractLiantuoReply&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;returnCode&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;returnMessage&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "abstractLiantuoReply", propOrder = { "returnCode",
		"returnMessage" })
public abstract class AbstractLiantuoReply {

	protected String returnCode;
	protected String returnMessage;

	/**
	 * Gets the value of the returnCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * Sets the value of the returnCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReturnCode(String value) {
		this.returnCode = value;
	}

	/**
	 * Gets the value of the returnMessage property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReturnMessage() {
		return returnMessage;
	}

	/**
	 * Sets the value of the returnMessage property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReturnMessage(String value) {
		this.returnMessage = value;
	}

}
