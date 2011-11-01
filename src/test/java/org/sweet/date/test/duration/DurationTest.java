package org.sweet.date.test.duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

import org.junit.Test;
import org.sweet.date.duration.Duration;

public class DurationTest {

	@Test
	public void getValue() {
		assertEquals(10, new Duration(10).getValue());
	}

	@Test
	public void average() {
		assertEquals(0, new Duration(100).average(0).getValue());
		assertEquals(10, new Duration(100).average(10).getValue());
		assertEquals(33, new Duration(100).average(3).getValue());
	}

	@Test
	public void compare() {
		Duration smaller = new Duration(10);
		Duration longer = new Duration(100);

		assertEquals(-1, smaller.compareTo(longer));
		assertEquals(0, smaller.compareTo(smaller));
		assertEquals(1, longer.compareTo(smaller));
	}

	@Test
	public void equals() {
		assertTrue(new Duration(10).equals(new Duration(10)));
		assertFalse(new Duration(10).equals(new Duration(100)));
	}

	@Test
	public void hashcode() {
		assertEquals(new Duration(10).hashCode(), new Duration(10).hashCode());
	}

	@Test
	public void format() {
		assertEquals("00:00:01.000", new Duration(1000).format());
	}

	@Test
	public void getAsText() {
		PropertyEditor editor = PropertyEditorManager.findEditor(Duration.class);

		assertNotNull(editor);

		editor.setValue(new Duration(1000));

		assertEquals("1000", editor.getAsText());
	}

	@Test
	public void setAsText() {
		PropertyEditor editor = PropertyEditorManager.findEditor(Duration.class);

		assertNotNull(editor);

		editor.setAsText("1000");

		assertEquals(new Duration(1000), editor.getValue());
	}
}
