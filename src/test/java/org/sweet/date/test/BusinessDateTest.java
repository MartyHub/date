package org.sweet.date.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.Date;

import org.junit.Test;
import org.sweet.date.BusinessDate;
import org.sweet.date.utils.Way;

public class BusinessDateTest {

	@Test
	public void parse() {
		BusinessDate date = BusinessDate.parse("2011-11-01");

		assertEquals(2011, date.getYear());
		assertEquals(11, date.getMonth());
		assertEquals(1, date.getDay());
	}

	@Test
	public void fromDate() {
		BusinessDate date = BusinessDate.parse("2011-11-01");

		assertEquals(date, new BusinessDate(date.asDate()));
	}

	@Test
	public void today() {
		assertEquals(new BusinessDate(new Date()), BusinessDate.today());
	}

	@Test
	public void string() {
		assertEquals("2011-11-01", BusinessDate.parse("2011-11-01").toString());
	}

	@Test
	public void compare() {
		BusinessDate before = BusinessDate.parse("2011-11-01");
		BusinessDate after = BusinessDate.parse("2011-11-28");

		assertTrue(before.compareTo(after) < 0);
		assertEquals(0, before.compareTo(before));
		assertTrue(after.compareTo(before) > 0);
	}

	@Test
	public void process() {
		BusinessDate date = BusinessDate.parse("2011-11-01");

		assertEquals(BusinessDate.parse("2011-10-31"), date.previous());
		assertEquals(BusinessDate.parse("2011-10-30"), date.previous(2));
		assertEquals(BusinessDate.parse("2011-11-01"), date.process(Way.BACKWARD, 0));
		assertEquals(BusinessDate.parse("2011-10-31"), date.process(Way.BACKWARD, 1));
		assertEquals(BusinessDate.parse("2011-10-30"), date.process(Way.BACKWARD, 2));

		assertEquals(BusinessDate.parse("2011-11-02"), date.next());
		assertEquals(BusinessDate.parse("2011-11-03"), date.next(2));
		assertEquals(BusinessDate.parse("2011-11-01"), date.process(Way.FORWARD, 0));
		assertEquals(BusinessDate.parse("2011-11-02"), date.process(Way.FORWARD, 1));
		assertEquals(BusinessDate.parse("2011-11-03"), date.process(Way.FORWARD, 2));

		assertEquals(BusinessDate.parse("2011-12-01"), date.nextMonth());

		assertEquals(BusinessDate.parse("2011-10-28"), date.processWithoutWeekEnd(Way.BACKWARD, 2));
		assertEquals(BusinessDate.parse("2011-11-10"), date.processWithoutWeekEnd(Way.FORWARD, 7));
	}

	@Test
	public void format() {
		assertEquals("01/11/2011", BusinessDate.parse("2011-11-01").format("dd/MM/yyyy"));
	}

	@Test
	public void getAsText() {
		PropertyEditor editor = PropertyEditorManager.findEditor(BusinessDate.class);

		assertNotNull(editor);

		editor.setValue(BusinessDate.parse("2011-11-01"));

		assertEquals("2011-11-01", editor.getAsText());
	}

	@Test
	public void setAsText() {
		PropertyEditor editor = PropertyEditorManager.findEditor(BusinessDate.class);

		assertNotNull(editor);

		editor.setAsText("2011-11-01");

		assertEquals(BusinessDate.parse("2011-11-01"), editor.getValue());
	}
}
