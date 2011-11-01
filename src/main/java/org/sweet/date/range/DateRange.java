package org.sweet.date.range;

import java.io.Serializable;

import org.sweet.date.AbstractDate;

public class DateRange<T extends AbstractDate<T>> implements Serializable {

	private static final long serialVersionUID = 3661456018582942058L;

	private final T start;

	private final T end;

	public DateRange(T start, T end) {
		this.start = start;
		this.end = end;
	}

	public T getStart() {
		return start;
	}

	public T getEnd() {
		return end;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append('[');
		sb.append(start);
		sb.append(", ");
		sb.append(end);
		sb.append('[');

		return sb.toString();
	}
}
