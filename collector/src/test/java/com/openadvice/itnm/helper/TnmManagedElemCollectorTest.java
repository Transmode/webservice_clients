package com.openadvice.itnm.helper;

import org.junit.Test;

import com.openadvice.itnm.types.ManagedElement;

public class TnmManagedElemCollectorTest {

	@Test
	public void getAllManagedElements_should_return() {
		for (ManagedElement managedElement: new TnmManagedElemCollector()
				.getAllManagedElements("http://tnm:8080/soap/mtosi/ManagedElementRetrieval")) {
			System.out.println(managedElement);
		};
	}
}
