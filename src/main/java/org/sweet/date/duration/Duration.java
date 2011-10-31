package org.sweet.date.duration;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Duration implements Serializable, Comparable<Duration> {

	private static final long serialVersionUID = 8858278104099114686L;

	private final long value;

	public Duration(final long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}

	public Duration average(final int nb) {
		if (nb != 0) {
			return new Duration(value / nb);
		} else {
			return new Duration(0);
		}
	}

	public String format() {
		Calendar calendar = asCalendar();
		DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");

		df.setCalendar(calendar);

		return df.format(calendar.getTime());
	}

	public int compareTo(Duration other) {
		if (value < other.value) {
			return -1;
		} else if (value > other.value) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	protected Calendar asCalendar() {
		Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));

		calendar.setTimeInMillis(value);

		return calendar;
	}
}
