package com.openadvice.itnm.types;

import java.util.Objects;

public class Equipment {

	private final String name;

	protected Equipment(String name) {
		this.name = Objects.requireNonNull(name);
	}

	@Override
	public String toString() {
		return "Equipment: name=" + name;
	}

	public static Builder equipment() {
		return new Builder();
	}

	public static class Builder {
		private Integer slot = null;
		private String userLabel = "";
		private Integer subrack = null;

		public Builder userLabel(String userLabel) {
			this.userLabel = Objects.requireNonNull(userLabel);
			return this;
		}

		public Builder subrack(int subrack) {
			this.subrack = subrack;
			return this;
		}

		public Builder slot(int slot) {
			this.slot = slot;
			return this;
		}

		public Equipment build() {
			if (subrack != null && slot != null) {
				return new EquipmentWithPosition(userLabel, subrack, slot);
			} else {
				return new Equipment(userLabel);
			}
		}
	}
}