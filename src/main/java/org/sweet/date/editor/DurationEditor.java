package org.sweet.date.editor;

import org.sweet.date.duration.Duration;

public class DurationEditor extends SafePropertyEditor<Duration> {

	@Override
	protected String doGetAsText(Duration value) {
		return value.toString();
	}

	@Override
	protected Duration fromText(String text) {
		return new Duration(Long.parseLong(text));
	}
}
