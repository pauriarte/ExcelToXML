package com.metromile.ws.com.mm.caliber.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ReferenceToContact {

	@JacksonXmlProperty(isAttribute = true, localName = "public-id")
	private String contact;

	public ReferenceToContact() {
		// TODO Auto-generated constructor stub
	}

	public ReferenceToContact(String contact) {
		super();
		this.contact = contact;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
