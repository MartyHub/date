package org.sweet.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TechnicalDate extends AbstractDate<TechnicalDate> {

	public static final TechnicalDate BEGIN_OF_TIME = new TechnicalDate(BusinessDate.BEGIN_OF_TIME,
			BusinessTime.BEGIN_OF_DAY);

	public static final TechnicalDate END_OF_TIME = new TechnicalDate(BusinessDate.END_OF_TIME,
			BusinessTime.END_OF_DAY);

	public static final String ISO = BusinessDate.ISO + " " + BusinessTime.ISO;

	public static TechnicalDate parse(String iso) {
		try {
			return new TechnicalDate(new SimpleDateFormat(ISO).parse(iso));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Failed to parse date <" + iso + "> with pattern <" + ISO
					+ ">", e);
		}
	}

	private static final long serialVersionUID = 7988073692442974235L;

	private final BusinessDate date;

	private final BusinessTime time;

	public TechnicalDate(Date date) {
		this.date = new BusinessDate(date);
		this.time = new BusinessTime(date);
	}

	public TechnicalDate(BusinessDate date, BusinessTime time) {
		this.date = date;
		this.time = time;
	}

	@Override
	public Calendar asCalendar() {
		Calendar calendar = date.asCalendar();

		time.merge(calendar);

		return calendar;
	}

	public BusinessDate getDate() {
		return date;
	}

	public BusinessTime getTime() {
		return time;
	}

	public int compareTo(TechnicalDate other) {
		int result = date.compareTo(other.date);

		if (result == 0) {
			result = time.compareTo(other.time);
		}

		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		TechnicalDate other = (TechnicalDate) obj;

		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;

		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(23);

		sb.append(date);
		sb.append(' ');
		sb.append(time);

		return sb.toString();
	}

	@Override
	protected void merge(Calendar calendar) {
		date.merge(calendar);
		time.merge(calendar);
	}
}
