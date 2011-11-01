package org.sweet.date;

import org.sweet.date.editor.SafePropertyEditor;

public class BusinessDateEditor extends SafePropertyEditor<BusinessDate> {

	@Override
	protected BusinessDate doSetAsText(String text) {
		return BusinessDate.parse(text);
	}
}
