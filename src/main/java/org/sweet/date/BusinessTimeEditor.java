package org.sweet.date;

import org.sweet.date.editor.SafePropertyEditor;

public class BusinessTimeEditor extends SafePropertyEditor<BusinessTime> {

	@Override
	protected BusinessTime doSetAsText(String text) {
		return BusinessTime.parse(text);
	}
}
