package com.metromile.ws.com.mm.caliber.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "InsuranceCompany_Ext")
public class InsuranceCompany {

    @JacksonXmlProperty(isAttribute = true, localName = "public-id")
    private String publicId;
    private String name;
    private String taxId = "111-11-1111";
    @JacksonXmlElementWrapper(localName = "tags")
    private List<ABContactTag> tags;
    private String workphone;
    @JacksonXmlProperty(localName = "MitchellID_Ext")
    private String mithcellid;
    @JacksonXmlProperty(localName = "YelpURL_Ext")
    private String yelp;
    @JacksonXmlProperty(localName = "EmailAddress1")
    private String email;
    private ReferenceToAddress primaryAddress;
    private String notes;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public List<ABContactTag> getTags() {
        return tags;
    }

    public void setTags(List<ABContactTag> tags) {
        this.tags = tags;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public String getMithcellid() {
        return mithcellid;
    }

    public void setMithcellid(String mithcellid) {
        this.mithcellid = mithcellid;
    }

    public String getYelp() {
        return yelp;
    }

    public void setYelp(String yelp) {
        this.yelp = yelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReferenceToAddress getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(ReferenceToAddress primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
