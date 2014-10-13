package com.openadvice.itnm.helper;

import com.openadvice.itnm.types.ManagedElement;
import org.apache.log4j.Logger;
import org.tmforum.mtop.fmw.xsd.hdr.v1.Header;
import org.tmforum.mtop.fmw.xsd.nam.v1.RelativeDistinguishNameType;
import org.tmforum.mtop.mri.wsdl.mer.v1_0.GetAllManagedElementsException;
import org.tmforum.mtop.mri.wsdl.mer.v1_0.ManagedElementRetrievalHttp;
import org.tmforum.mtop.mri.wsdl.mer.v1_0.ManagedElementRetrievalRPC;
import org.tmforum.mtop.mri.xsd.mer.v1.GetAllManagedElementsRequest;
import org.tmforum.mtop.mri.xsd.mer.v1.MultipleMeObjectsResponseType;
import org.tmforum.mtop.nrf.xsd.me.v1.ManagedElementType;

import javax.xml.ws.Holder;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TnmManagedElemCollector implements IManagedElementCollector {
    public static final String SOAP_MTOSI_MANAGED_ELEMENT_RETRIEVAL = "/soap/mtosi/ManagedElementRetrieval";
    private static Logger log = Logger.getLogger(TnmManagedElemCollector.class);

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

            final ArrayList<ManagedElement> allElements = new ArrayList<>();
            final MultipleMeObjectsResponseType multipleMeObjectsResponseType = managedElementRetrievalRPC
                    .getAllManagedElements(holder, getAllManagedElementsRequest);

            for (ManagedElementType managedElementType : multipleMeObjectsResponseType
                    .getMeList().getMe()) {
                allElements.add(managedElement(managedElementType));
            }

            // oneElem.setResourceState(tmp.getResourceState().getValue());
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

    static ManagedElement managedElement(ManagedElementType managedElementType) {
        final ManagedElement managedElement = new ManagedElement();
        for (RelativeDistinguishNameType relativeDistinguishNameType : managedElementType.getName().getRdn()) {
            switch (relativeDistinguishNameType.getType()) {
                case "MD":
                    managedElement.setMd(relativeDistinguishNameType.getValue());
                    break;
                case "ME":
                    managedElement.setMe(relativeDistinguishNameType.getValue());
                    break;
                default:
                    break;
            }
        }
        managedElement.setUserLabel(managedElementType.getUserLabel());
        managedElement.setLocation(managedElementType.getLocation());
        managedElement.setProductName(managedElementType.getProductName());
        managedElement.setIsInSyncState(managedElementType.isIsInSyncState());
        managedElement.setCommunicationState(managedElementType.getCommunicationState().toString());
        managedElement.setResourceState(managedElementType.getResourceState().getValue().toString());
        return managedElement;
    }

    public static void main(String[] args) {
        final String endPointAddress;
        if (args.length > 0) {
            endPointAddress = args[0] + SOAP_MTOSI_MANAGED_ELEMENT_RETRIEVAL;
        } else {
            endPointAddress = "http://tnm:8080" + SOAP_MTOSI_MANAGED_ELEMENT_RETRIEVAL;
        }
        for (ManagedElement managedElement : new TnmManagedElemCollector()
                .getAllManagedElements(endPointAddress)) {
            System.out.println(managedElement);
        }
    }
}