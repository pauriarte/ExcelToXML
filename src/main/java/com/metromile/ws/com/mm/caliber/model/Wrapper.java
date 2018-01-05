package com.metromile.ws.com.mm.caliber.model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Wrapper {

    @JacksonXmlProperty(localName = "InsuredCompany_Ext")
	@JacksonXmlElementWrapper(useWrapping = false)
	//private List<ABAutoRepairShop> abAutoRepairShop = new LinkedList<>();
	private List<InsuranceCompany> insuranceCompany = new LinkedList<>();

	@JacksonXmlProperty(localName = "ABContactAddress")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<ABContactAddress> abContactAddress = new LinkedList<>();

	@JacksonXmlProperty(localName = "Address")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Address> address = new LinkedList<>();

	public List<InsuranceCompany> getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(List<InsuranceCompany> insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
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
