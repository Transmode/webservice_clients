package com.openadvice.itnm.helper.eir;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.tmforum.mtop.fmw.xsd.hdr.v1.CommunicationPatternType;
import org.tmforum.mtop.fmw.xsd.hdr.v1.Header;
import org.tmforum.mtop.mri.wsdl.eir.v1_0.EquipmentExtraInfo;
import org.tmforum.mtop.mri.wsdl.eir.v1_0.EquipmentInventoryRetrievalHttp;
import org.tmforum.mtop.mri.wsdl.eir.v1_0.EquipmentInventoryRetrievalRPC;
import org.tmforum.mtop.mri.wsdl.eir.v1_0.GetAllEquipmentException;
import org.tmforum.mtop.mri.wsdl.eir.v1_0.GetEquipmentIteratorException;
import org.tmforum.mtop.mri.xsd.eir.v1.GetAllEquipmentRequestType;
import org.tmforum.mtop.nrf.xsd.eq.v1.EquipmentType;
import org.tmforum.mtop.nrf.xsd.eq_inv.v1.EquipmentOrHolderListType;
import org.tmforum.mtop.nrf.xsd.eq_inv.v1.EquipmentOrHolderType;

import javax.xml.bind.JAXBElement;
import javax.xml.ws.Holder;

import com.openadvice.itnm.types.Equipment;

public class TnmEquipmentCollector {

	public static final String SOAP_MTOSI_EQUIPMENT_INVENTORY_RETRIEVAL = "/soap/mtosi/EquipmentInventoryRetrieval";

	public List<Equipment> getAllEquipment(String endPointAddress) {
		try {
			final URL url = new URL(endPointAddress);
			final EquipmentInventoryRetrievalHttp equipmentInventoryRetrievalHttp = new EquipmentInventoryRetrievalHttp(
					url);

			final Holder<Header> holder = new Holder<>(new Header());
			holder.value
					.setCommunicationPattern(CommunicationPatternType.MULTIPLE_BATCH_RESPONSE);
			holder.value.setRequestedBatchSize(100l);

			final EquipmentInventoryRetrievalRPC equipmentInventoryRetrievalSoapHttp = equipmentInventoryRetrievalHttp
					.getEquipmentInventoryRetrievalSoapHttp();

			try {
				final EquipmentOrHolderListType firstBatch = equipmentInventoryRetrievalSoapHttp
						.getAllEquipment(holder,
								new GetAllEquipmentRequestType());

				final List<Equipment> equipments = new ArrayList<>();
				equipments(firstBatch, equipments);

				while (!holder.value.isBatchSequenceEndOfReply()) {
					final EquipmentOrHolderListType nextBatch = equipmentInventoryRetrievalSoapHttp
							.getEquipmentIterator(holder, null);
					equipments(nextBatch, equipments);
				}

				return equipments;
			} catch (GetAllEquipmentException | GetEquipmentIteratorException e) {
				throw new CollectorException(e);
			}
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private void equipments(final EquipmentOrHolderListType firstBatch,
			final List<Equipment> equipments) {
		for (EquipmentOrHolderType equipmentOrHolderType : firstBatch.getEoh()) {
			final EquipmentType equipmentType = equipmentOrHolderType.getEq();
			if (equipmentType != null) {
			final Equipment.Builder equipment = Equipment.equipment();
			equipment.userLabel(equipmentType.getUserLabel());
				if (equipmentType.getVendorExtensions() != null) {
					for (final Object vendorExtension : equipmentType
							.getVendorExtensions().getAny()) {
						final JAXBElement<?> jaxbElement = (JAXBElement<?>) vendorExtension;
						if (jaxbElement.getValue() instanceof EquipmentExtraInfo) {
							final EquipmentExtraInfo equipmentExtraInfo = (EquipmentExtraInfo) jaxbElement.getValue();
							equipment.subrack(equipmentExtraInfo.getSubrack());
							equipment.slot(equipmentExtraInfo.getSlot());
						}
					}
				}
				equipments.add(equipment.build());
				System.out.println(equipments.size() + ": " + equipment.build());
			}
		}
	}

	public static void main(String[] args) {
		final String endPointAddress;
		if (args.length > 0) {
			endPointAddress = args[0]
					+ SOAP_MTOSI_EQUIPMENT_INVENTORY_RETRIEVAL;
		} else {
			endPointAddress = "http://tnm:8080"
					+ SOAP_MTOSI_EQUIPMENT_INVENTORY_RETRIEVAL;
		}
		for (Equipment equipment : new TnmEquipmentCollector()
				.getAllEquipment(endPointAddress)) {
			System.out.println(equipment);
		}
	}
}
