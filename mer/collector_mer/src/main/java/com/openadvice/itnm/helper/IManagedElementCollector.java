package com.openadvice.itnm.helper;

import java.util.List;

import com.openadvice.itnm.types.ManagedElement;

public interface IManagedElementCollector {

	/**
	 * Get all managed elements from the webservice.
	 * @param endPointAddress The service URL.
	 * @return A list of managed elements.
	 */
	List<ManagedElement> getAllManagedElements(String endPointAddress);
}
