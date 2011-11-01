package org.sweet.date.duration;

import org.sweet.date.editor.SafePropertyEditor;

public class DurationEditor extends SafePropertyEditor<Duration> {

	@Override
	protected Duration doSetAsText(String text) {
		return new Duration(Long.parseLong(text));
	}
}
