package org.sweet.date.editor;

import org.sweet.date.BusinessDate;

public class BusinessDateEditor extends SafePropertyEditor<BusinessDate> {

	@Override
	protected String doGetAsText(BusinessDate value) {
		return value.toString();
	}

	@Override
	protected BusinessDate fromText(String text) {
		return BusinessDate.parse(text);
	}
}
