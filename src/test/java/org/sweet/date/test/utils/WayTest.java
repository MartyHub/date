package org.sweet.date.test.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sweet.date.utils.Way;

public class WayTest {

	@Test
	public void increment() {
		assertEquals(-1, Way.BACKWARD.getIncrement());
		assertEquals(1, Way.FORWARD.getIncrement());
	}
}
