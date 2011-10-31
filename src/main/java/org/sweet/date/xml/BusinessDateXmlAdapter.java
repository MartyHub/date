package org.sweet.date.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.sweet.date.BusinessDate;


public class BusinessDateXmlAdapter extends XmlAdapter<String, BusinessDate> {

	@Override
	public BusinessDate unmarshal(String s) throws Exception {
		return BusinessDate.parse(s);
	}

	@Override
	public String marshal(BusinessDate date) throws Exception {
		if (date == null) {
			return null;
		}

		return date.toString();
	}
}
