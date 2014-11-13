package com.openadvice.itnm.helper.eir;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Holder;

import org.apache.log4j.Logger;

import com.openadvice.itnm.types.Equipment;

public class TnmEquipmentCollector {

	public static final String SOAP_MTOSI_EQUIPMENT_INVENTORY_RETRIEVAL = "/soap/mtosi/EquipmentInventoryRetrieval";
	private static Logger log = Logger.getLogger(TnmEquipmentCollector.class);

	public List<Equipment> getAllEquipment(String endPointAddress) {
		try {
			final URL url = new URL(endPointAddress);
			// final EquipmentInventoryRetrievalHttp equipmentInventoryRetrievalHttp =
			// new ManagedElementRetrievalHttp(
			// url);
			final List<Equipment> equipments = new ArrayList<>();
			
			final Holder<?> holder = new Holder<>();
			
			// getAllEquipment()
			
//			while (holder.value) {
//				getIterator();
//			}
			
			return equipments;
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static void main(String[] args) {		
        final String endPointAddress;
        if (args.length > 0) {
            endPointAddress = args[0] + SOAP_MTOSI_EQUIPMENT_INVENTORY_RETRIEVAL;
        } else {
            endPointAddress = "http://tnm:8080" + SOAP_MTOSI_EQUIPMENT_INVENTORY_RETRIEVAL;
        }
        for (Equipment equipment: new TnmEquipmentCollector()
                .getAllEquipment(endPointAddress)) {
            System.out.println(equipment);
        }
	}
}
