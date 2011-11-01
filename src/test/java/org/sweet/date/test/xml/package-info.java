@XmlJavaTypeAdapter(type = BusinessDate.class, value = BusinessDateXmlAdapter.class)
package org.sweet.date.test.xml;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sweet.date.BusinessDate;
import org.sweet.date.xml.BusinessDateXmlAdapter;

