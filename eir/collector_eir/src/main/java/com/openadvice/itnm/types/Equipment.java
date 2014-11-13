package com.openadvice.itnm.types;

import java.util.Objects;

public class Equipment {
	private final String name;

	public Equipment(String name) {
		this.name = Objects.requireNonNull(name);
	}

	public static Equipment equipment(String name) {
		return new Equipment(name);
	}

	@Override
	public String toString() {
		return "Equipment: " + name;
	}
}
