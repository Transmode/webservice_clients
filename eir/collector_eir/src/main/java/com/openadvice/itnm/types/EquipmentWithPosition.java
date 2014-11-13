package com.openadvice.itnm.types;

public class EquipmentWithPosition extends Equipment {
	private final int subrack;
	private final int slot;
	
	EquipmentWithPosition(String name, int subrack, int slot) {
		super(name);
		this.subrack = subrack;
		this.slot = slot;
	}

	@Override
	public String toString() {
		return super.toString() + ", subrack=" + subrack + ", slot=" + slot;
	}
}
