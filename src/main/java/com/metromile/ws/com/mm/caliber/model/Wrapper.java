package com.metromile.ws.com.mm.caliber.model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Wrapper {

	@JacksonXmlElementWrapper(useWrapping = false)
	private List<ABAutoRepairShop> abAutoRepairShop = new LinkedList<>();

	@JacksonXmlProperty(localName = "ABContactAddress")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<ABContactAddress> abContactAddress = new LinkedList<>();

	@JacksonXmlProperty(localName = "Address")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Address> address = new LinkedList<>();

	public List<ABAutoRepairShop> getAbAutoRepairShop() {
		return abAutoRepairShop;
	}

	public void setAbAutoRepairShop(List<ABAutoRepairShop> abAutoRepairShop) {
		this.abAutoRepairShop = abAutoRepairShop;
	}

	public List<ABContactAddress> getAbContactAddress() {
		return abContactAddress;
	}

	public void setAbContactAddress(List<ABContactAddress> abContactAddress) {
		this.abContactAddress = abContactAddress;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

}
