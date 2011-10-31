package org.sweet.date.editor;

import org.sweet.date.TechnicalDate;

public class TechnicalDateEditor extends SafePropertyEditor<TechnicalDate> {

	@Override
	protected String doGetAsText(TechnicalDate value) {
		return value.toString();
	}

	@Override
	protected TechnicalDate fromText(String text) {
		return TechnicalDate.parse(text);
	}
}
