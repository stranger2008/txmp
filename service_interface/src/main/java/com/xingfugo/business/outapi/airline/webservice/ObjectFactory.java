package com.xingfugo.business.outapi.airline.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.xingfugo.business.outapi.airline.webservice package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _WsBookPassenger_QNAME = new QName(
			"http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/",
			"wsBookPassenger");
	private final static QName _CreateOrderByPassenger_QNAME = new QName(
			"http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/",
			"createOrderByPassenger");
	private final static QName _CreateOrderByPassengerReply_QNAME = new QName(
			"http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/",
			"createOrderByPassengerReply");
	private final static QName _CreateOrderByPassengerRequest_QNAME = new QName(
			"http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/",
			"createOrderByPassengerRequest");
	private final static QName _CreateOrderByPassengerResponse_QNAME = new QName(
			"http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/",
			"createOrderByPassengerResponse");
	private final static QName _WsBookPnr_QNAME = new QName(
			"http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/",
			"wsBookPnr");
	private final static QName _WsAirSegment_QNAME = new QName(
			"http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/",
			"wsAirSegment");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.xingfugo.business.outapi.airline.webservice
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link CreateOrderByPassengerResponse }
	 * 
	 */
	public CreateOrderByPassengerResponse createCreateOrderByPassengerResponse() {
		return new CreateOrderByPassengerResponse();
	}

	/**
	 * Create an instance of {@link com.xingfugo.business.outapi.airline.webservice.WsFlightInfo }
	 * 
	 */
	public WsFlightInfo createWsFlightInfo() {
		return new WsFlightInfo();
	}

	/**
	 * Create an instance of {@link com.xingfugo.business.outapi.airline.webservice.WsAirSegment }
	 * 
	 */
	public WsAirSegment createWsAirSegment() {
		return new WsAirSegment();
	}

	/**
	 * Create an instance of {@link WsPolicyOrder }
	 * 
	 */
	public WsPolicyOrder createWsPolicyOrder() {
		return new WsPolicyOrder();
	}

	/**
	 * Create an instance of {@link WsPaymentInfo }
	 * 
	 */
	public WsPaymentInfo createWsPaymentInfo() {
		return new WsPaymentInfo();
	}

	/**
	 * Create an instance of {@link CreateOrderByPassengerReply }
	 * 
	 */
	public CreateOrderByPassengerReply createCreateOrderByPassengerReply() {
		return new CreateOrderByPassengerReply();
	}

	/**
	 * Create an instance of {@link WsBookPassenger }
	 * 
	 */
	public WsBookPassenger createWsBookPassenger() {
		return new WsBookPassenger();
	}

	/**
	 * Create an instance of {@link CreateOrderByPassengerRequest }
	 * 
	 */
	public CreateOrderByPassengerRequest createCreateOrderByPassengerRequest() {
		return new CreateOrderByPassengerRequest();
	}

	/**
	 * Create an instance of {@link com.xingfugo.business.outapi.airline.webservice.WsBookPnr }
	 * 
	 */
	public WsBookPnr createWsBookPnr() {
		return new WsBookPnr();
	}

	/**
	 * Create an instance of {@link CreateOrderByPassenger }
	 * 
	 */
	public CreateOrderByPassenger createCreateOrderByPassenger() {
		return new CreateOrderByPassenger();
	}

	/**
	 * Create an instance of {@link WsBookedPassenger }
	 * 
	 */
	public WsBookedPassenger createWsBookedPassenger() {
		return new WsBookedPassenger();
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link WsBookPassenger }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/", name = "wsBookPassenger")
	public JAXBElement<WsBookPassenger> createWsBookPassenger(
			WsBookPassenger value) {
		return new JAXBElement<WsBookPassenger>(_WsBookPassenger_QNAME,
				WsBookPassenger.class, null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link CreateOrderByPassenger }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/", name = "createOrderByPassenger")
	public JAXBElement<CreateOrderByPassenger> createCreateOrderByPassenger(
			CreateOrderByPassenger value) {
		return new JAXBElement<CreateOrderByPassenger>(
				_CreateOrderByPassenger_QNAME, CreateOrderByPassenger.class,
				null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link CreateOrderByPassengerReply }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/", name = "createOrderByPassengerReply")
	public JAXBElement<CreateOrderByPassengerReply> createCreateOrderByPassengerReply(
			CreateOrderByPassengerReply value) {
		return new JAXBElement<CreateOrderByPassengerReply>(
				_CreateOrderByPassengerReply_QNAME,
				CreateOrderByPassengerReply.class, null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link CreateOrderByPassengerRequest }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/", name = "createOrderByPassengerRequest")
	public JAXBElement<CreateOrderByPassengerRequest> createCreateOrderByPassengerRequest(
			CreateOrderByPassengerRequest value) {
		return new JAXBElement<CreateOrderByPassengerRequest>(
				_CreateOrderByPassengerRequest_QNAME,
				CreateOrderByPassengerRequest.class, null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link CreateOrderByPassengerResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/", name = "createOrderByPassengerResponse")
	public JAXBElement<CreateOrderByPassengerResponse> createCreateOrderByPassengerResponse(
			CreateOrderByPassengerResponse value) {
		return new JAXBElement<CreateOrderByPassengerResponse>(
				_CreateOrderByPassengerResponse_QNAME,
				CreateOrderByPassengerResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link com.xingfugo.business.outapi.airline.webservice.WsBookPnr }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/", name = "wsBookPnr")
	public JAXBElement<WsBookPnr> createWsBookPnr(WsBookPnr value) {
		return new JAXBElement<WsBookPnr>(_WsBookPnr_QNAME, WsBookPnr.class,
				null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link com.xingfugo.business.outapi.airline.webservice.WsAirSegment }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://createorderbypassenger.b2b.service.version1_0.webservice.model.ltips.com/", name = "wsAirSegment")
	public JAXBElement<WsAirSegment> createWsAirSegment(WsAirSegment value) {
		return new JAXBElement<WsAirSegment>(_WsAirSegment_QNAME,
				WsAirSegment.class, null, value);
	}

}
