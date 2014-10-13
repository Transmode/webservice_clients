package com.openadvice.itnm.helper;

import com.openadvice.itnm.types.ManagedElement;

import javax.xml.ws.Holder;
import org.tmforum.mtop.fmw.xsd.hdr.v1.Header;
import org.tmforum.mtop.mri.wsdl.mer.v1_0.ManagedElementRetrievalHttp;
import org.tmforum.mtop.mri.wsdl.mer.v1_0.ManagedElementRetrievalRPC;
import org.tmforum.mtop.mri.xsd.mer.v1.GetAllManagedElementsRequest;
import org.tmforum.mtop.mri.wsdl.mer.v1_0.GetAllManagedElementsException;
import org.tmforum.mtop.mri.xsd.mer.v1.MultipleMeObjectsResponseType;
import org.tmforum.mtop.nrf.xsd.me.v1.ManagedElementType;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.jws.WebParam;

import org.apache.log4j.Logger;

public class TnmManagedElemCollector implements IManagedElementCollector {
	private static Logger log = Logger.getLogger(TnmManagedElemCollector.class
			.getName());

	@Override
	public ArrayList<ManagedElement> getAllManagedElements(
			String endPointAddress) {
		log.debug("Starting to retrieve all managed elements from Transmode ");
		try {
			final long time = System.currentTimeMillis();
			final URL url = new URL(endPointAddress);

			final ManagedElementRetrievalHttp managedElementRetrievalHttp = new ManagedElementRetrievalHttp(
					url);

			final ManagedElementRetrievalRPC managedElementRetrievalRPC = managedElementRetrievalHttp
					.getManagedElementRetrievalSoapHttp();

			final Holder<Header> holder = new Holder<>();
			final GetAllManagedElementsRequest getAllManagedElementsRequest = new GetAllManagedElementsRequest();

			final ArrayList<ManagedElement> allElements = new ArrayList<ManagedElement>();
			final MultipleMeObjectsResponseType multipleMeObjectsResponseType = managedElementRetrievalRPC
					.getAllManagedElements(holder, getAllManagedElementsRequest);
			allElements.add(new ManagedElement());

			for (ManagedElementType managedElementType : multipleMeObjectsResponseType
					.getMeList().getMe()) {

				System.out.println(managedElementType);
			}

			// managedElemDoc.addNewGetAllManagedElementsRequest();
			// HeaderDocument meHeader = HeaderDocument.Factory.newInstance();
			// meHeader.addNewHeader();
			// GetAllManagedElementsResponseDocument manaResp =
			// managedElemStub.getAllManagedElements(managedElemDoc, meHeader);
			// MultipleMeObjectsResponseType allElemResp =
			// manaResp.getGetAllManagedElementsResponse();
			// // ManagedElementListType meList = allElemResp.getMeList();
			// ManagedElementType [] elems =allElemResp.getMeList();
			//
			// for(ManagedElementType tmp:elems){
			// ManagedElement oneElem = new ManagedElement();
			// // RelativeDistinguishNameType [] names =
			// tmp.getName().getRdnArray();
			// RelativeDistinguishNameType [] names = tmp.getName();
			// for(RelativeDistinguishNameType rdn:names){
			// if(rdn.getType().equalsIgnoreCase("ME"))
			// oneElem.setMe(rdn.getValue());
			// if(rdn.getType().equalsIgnoreCase("MD"))
			// oneElem.setMd(rdn.getValue());
			// }
			// oneElem.setResourceState(tmp.getResourceState().getValue());
			// oneElem.setUserLabel(tmp.getUserLabel());
			// oneElem.setLocation(tmp.getLocation());
			// oneElem.setProductName(tmp.getProductName());
			// // oneElem.setIsInSyncState(tmp.getIsInSyncState());
			// oneElem.setIsInSyncState(true);
			// oneElem.setCommunicationState(tmp.getCommunicationState().toString());
			// allElements.add(oneElem);
			// }
			final long execution = System.currentTimeMillis() - time;
			log.debug("[PERF] Retrieving all managed elements took: "
					+ execution + " ms.");
			return allElements;
		} catch (MalformedURLException e) {
			log.error("Error getting all equipment: RemoteException: " + e);
			throw new CollectorException("Failed to create URL.", e);
		} catch (GetAllManagedElementsException e) {
			log.error(
					"Error getting all equipment: GetAllManagedElementsException: ",
					e);
			throw new CollectorException("Failed to getAllManagedElements.", e);
		}
	}

	public static void main(String[] args) {
		System.out
				.println(new TnmManagedElemCollector()
						.getAllManagedElements("http://tnm:8080/soap/mtosi/ManagedElementRetrieval"));
	}
}