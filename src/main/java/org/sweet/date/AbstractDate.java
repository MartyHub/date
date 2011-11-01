package org.sweet.date;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public abstract class AbstractDate<T extends AbstractDate<T>> implements Serializable, Comparable<T> {

	private static final long serialVersionUID = 5584182716548023346L;

	protected AbstractDate() {
	}

	public final Date asDate() {
		return asCalendar().getTime();
	}

	public boolean before(T other) {
		return this.compareTo(other) < 0;
	}

	public boolean after(T other) {
		return this.compareTo(other) > 0;
	}

	public abstract Calendar asCalendar();

	protected abstract void merge(Calendar calendar);
}
