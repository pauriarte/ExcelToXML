package com.metromile.ws.com.mm.caliber.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ABContactAddress") 
public class ABContactAddress {

	@JacksonXmlProperty(isAttribute = true, localName = "public-id")
	private String id;
	private ReferenceToContact contact;
	private ReferenceToAddress address;

	public String getId() {
		return id;
	}

	public ReferenceToContact getContact() {
		return contact;
	}

	public void setContact(ReferenceToContact contact) {
		this.contact = contact;
	}

	public ReferenceToAddress getAddress() {
		return address;
	}

	public void setAddress(ReferenceToAddress address) {
		this.address = address;
	}

	public void setId(String id) {
		this.id = id;
	}

}
