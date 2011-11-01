package org.sweet.date.editor;

import java.beans.PropertyEditorSupport;

public abstract class SafePropertyEditor<T> extends PropertyEditorSupport {

	@SuppressWarnings("unchecked")
	@Override
	public String getAsText() {
		Object value = getValue();

		if (value == null) {
			return null;
		} else {
			return doGetAsText((T) value);
		}
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || "".equals(text)) {
			setValue(null);
		} else {
			T value = doSetAsText(text);

			setValue(value);
		}
	}

	protected String doGetAsText(T value) {
		return value.toString();
	}

	protected abstract T doSetAsText(String text);
}
