package com.metromile.ws.com.mm.caliber.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ReferenceToAddress {

	@JacksonXmlProperty(isAttribute = true, localName = "public-id")
	private String address;

	public ReferenceToAddress() {
		// TODO Auto-generated constructor stub
	}

	public ReferenceToAddress(String address) {
		super();
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
