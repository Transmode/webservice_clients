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
			this.userLabel = userLabel != null ? userLabel : "";
			return this;
		}

		public Builder subrack(Integer subrack) {
			this.subrack = subrack != null ? subrack : null;
			return this;
		}

		public Builder slot(Integer slot) {
			this.slot = slot != -1 ? slot : null;
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
