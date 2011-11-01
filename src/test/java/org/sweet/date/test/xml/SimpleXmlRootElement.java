package org.sweet.date.test.xml;

import javax.xml.bind.annotation.XmlRootElement;

import org.sweet.date.BusinessDate;

@XmlRootElement
public class SimpleXmlRootElement {

	public BusinessDate businessDate;

	public SimpleXmlRootElement() {
	}

	public SimpleXmlRootElement(BusinessDate businessDate) {
		this.businessDate = businessDate;
	}
}
