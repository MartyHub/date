package org.sweet.date.utils;

public enum Way {

	BACKWARD(-1), FORWARD(1);

	private final int increment;

	Way(final int increment) {
		this.increment = increment;
	}

	public int getIncrement() {
		return increment;
	}
}
