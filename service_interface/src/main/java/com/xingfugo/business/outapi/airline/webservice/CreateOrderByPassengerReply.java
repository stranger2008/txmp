package com.xingfugo.business.outapi.airline.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for createOrderByPassengerReply complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;createOrderByPassengerReply&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}abstractLiantuoReply&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;order&quot; type=&quot;{http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/}wsPolicyOrder&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param2&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param3&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;param5&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;parma4&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrderByPassengerReply", propOrder = { "order", "param1",
		"param2", "param3", "param5", "parma4" })
public class CreateOrderByPassengerReply extends AbstractLiantuoReply {

	protected WsPolicyOrder order;
	protected String param1;
	protected String param2;
	protected String param3;
	protected String param5;
	protected String parma4;

	/**
	 * Gets the value of the order property.
	 * 
	 * @return possible object is {@link com.xingfugo.business.outapi.airline.webservice.WsPolicyOrder }
	 * 
	 */
	public WsPolicyOrder getOrder() {
		return order;
	}

	/**
	 * Sets the value of the order property.
	 * 
	 * @param value
	 *            allowed object is {@link com.xingfugo.business.outapi.airline.webservice.WsPolicyOrder }
	 * 
	 */
	public void setOrder(WsPolicyOrder value) {
		this.order = value;
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
	 * Gets the value of the parma4 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParma4() {
		return parma4;
	}

	/**
	 * Sets the value of the parma4 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParma4(String value) {
		this.parma4 = value;
	}

}
