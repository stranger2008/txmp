package com.xingfugo.business.outapi.airline.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("airports")
public class Airports {
	
	@XStreamImplicit(itemFieldName="airport")
	private List<AirportCode> airportCode;

	public List<AirportCode> getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(List<AirportCode> airportCode) {
		this.airportCode = airportCode;
	}
	
	

}
