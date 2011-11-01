package org.sweet.date;

import org.sweet.date.editor.SafePropertyEditor;

public class TechnicalDateEditor extends SafePropertyEditor<TechnicalDate> {

	@Override
	protected TechnicalDate doSetAsText(String text) {
		return TechnicalDate.parse(text);
	}
}
