package org.sweet.date.editor;

import org.sweet.date.BusinessTime;

public class BusinessTimeEditor extends SafePropertyEditor<BusinessTime> {

	@Override
	protected String doGetAsText(BusinessTime value) {
		return value.toString();
	}

	@Override
	protected BusinessTime fromText(String text) {
		return BusinessTime.parse(text);
	}
}
