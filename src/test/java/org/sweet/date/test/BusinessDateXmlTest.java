package org.sweet.date.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.sweet.date.BusinessDate;
import org.sweet.date.test.xml.SimpleXmlRootElement;

public class BusinessDateXmlTest {

	private final String xml = "<simpleXmlRootElement><businessDate>2011-11-01</businessDate></simpleXmlRootElement>";

	@Test
	public void marshall() throws JAXBException {
		SimpleXmlRootElement root = new SimpleXmlRootElement(BusinessDate.parse("2011-11-01"));
		StringWriter sw = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(SimpleXmlRootElement.class);

		context.createMarshaller().marshal(root, sw);

		assertTrue(sw.toString().contains(xml));
	}

	@Test
	public void unmarshall() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(SimpleXmlRootElement.class);
		SimpleXmlRootElement root = (SimpleXmlRootElement) context.createUnmarshaller().unmarshal(
				new StringReader(xml));

		assertEquals(BusinessDate.parse("2011-11-01"), root.businessDate);
	}
}
