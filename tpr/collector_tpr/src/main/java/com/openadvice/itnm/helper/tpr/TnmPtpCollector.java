package com.openadvice.itnm.helper.tpr;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.JAXBElement;
import javax.xml.ws.Holder;

import org.tmforum.mtop.fmw.xsd.hdr.v1.CommunicationPatternType;
import org.tmforum.mtop.fmw.xsd.hdr.v1.CommunicationStyleType;
import org.tmforum.mtop.fmw.xsd.hdr.v1.Header;
import org.tmforum.mtop.fmw.xsd.nam.v1.NamingAttributeType;
import org.tmforum.mtop.fmw.xsd.nam.v1.RelativeDistinguishNameType;
import org.tmforum.mtop.mri.wsdl.tpr.v1_0.GetAllPhysicalTerminationPointsException;
import org.tmforum.mtop.mri.wsdl.tpr.v1_0.PhysicalTerminationPointExtraInfo;
import org.tmforum.mtop.mri.wsdl.tpr.v1_0.TerminationPointRetrievalHttp;
import org.tmforum.mtop.mri.wsdl.tpr.v1_0.TerminationPointRetrievalRPC;
import org.tmforum.mtop.mri.wsdl.tpr.v1_0.TnmNamingAttributeType;
import org.tmforum.mtop.mri.xsd.tpr.v1.GetAllTerminationPointsType;
import org.tmforum.mtop.mri.xsd.tpr.v1.MultipleTerminationPointObjectsResponseType;
import org.tmforum.mtop.nrf.xsd.ptp.v1.PhysicalTerminationPointType;
import org.tmforum.mtop.nrf.xsd.tp.v1.TerminationPointType;

import com.openadvice.itnm.collector.common.CollectorException;
import com.openadvice.itnm.types.tpr.PtpInfo;
import com.openadvice.itnm.types.tpr.PtpInfo.Builder;

public class TnmPtpCollector {

	private static final String SOAP_MTOSI_TERMINATION_POINT_RETRIEVAL = "soap/mtosi/TerminationPointRetrieval";

	private Collection<PtpInfo> getAllPtpInfos(String endPointAddress) {

		try {
			final URL url = new URL(endPointAddress);
			final TerminationPointRetrievalRPC terminationPointRetrievalRPC = new TerminationPointRetrievalHttp(
					url).getPort(TerminationPointRetrievalRPC.class);

			final Collection<PtpInfo> ptpInfos = new ArrayList<>();

			// Make this a multi-batch or it gets to big.
			final Holder<Header> mtopHeader = new Holder<Header>(new Header());
			mtopHeader.value
					.setCommunicationPattern(CommunicationPatternType.MULTIPLE_BATCH_RESPONSE);
			final GetAllTerminationPointsType mtopBody = new GetAllTerminationPointsType();
			final MultipleTerminationPointObjectsResponseType allPhysicalTerminationPoints = terminationPointRetrievalRPC
					.getAllPhysicalTerminationPoints(mtopHeader, mtopBody);

			// Only getting the first batch here; should be sufficient for demo
			// purposes.
			for (TerminationPointType terminationPointType : allPhysicalTerminationPoints
					.getTpList().getTp()) {
				if (terminationPointType.getPtp() != null) {
					final PhysicalTerminationPointType physicalTerminationPointType = terminationPointType
							.getPtp();
					final Builder ptpInfo = PtpInfo.builder();
					ptpInfo.name(name(physicalTerminationPointType.getName()));
					ptpInfo.userLabel(physicalTerminationPointType
							.getUserLabel());
					for (Object vendorExtension : physicalTerminationPointType
							.getVendorExtensions().getAny()) { 
						if (vendorExtension instanceof JAXBElement<?>) {
						final JAXBElement<?> jaxbElement = (JAXBElement<?>) vendorExtension;
						if (jaxbElement.getValue() instanceof PhysicalTerminationPointExtraInfo) {
							final PhysicalTerminationPointExtraInfo physicalTerminationPointExtraInfo = (PhysicalTerminationPointExtraInfo) jaxbElement
									.getValue();
							if (physicalTerminationPointExtraInfo
									.isRxSignalStatusAvailable()) {
								ptpInfo.rxSignalStatus(physicalTerminationPointExtraInfo.getRxSignalStatus());

							}
						}
						}
					}
					ptpInfos.add(ptpInfo.build());
				}
			}

			// continue iterating here

			return ptpInfos;
		} catch (MalformedURLException
				| GetAllPhysicalTerminationPointsException e) {
			throw new CollectorException(e);
		}
	}

	private static String name(NamingAttributeType namingAttributeType) {
		final StringBuilder name = new StringBuilder();
		for (RelativeDistinguishNameType relativeDistinguishNameType : namingAttributeType
				.getRdn()) {
			name.append("/").append(relativeDistinguishNameType.getType())
					.append("=").append(relativeDistinguishNameType.getValue());
		}
		;
		return name.toString();
	}

	public static void main(String[] args) {
		final String endPointAddress;
		if (args.length > 0) {
			endPointAddress = args[0] + SOAP_MTOSI_TERMINATION_POINT_RETRIEVAL;
		} else {
			endPointAddress = "http://tnm:8080/"
					+ SOAP_MTOSI_TERMINATION_POINT_RETRIEVAL;
		}
		for (PtpInfo ptpInfo : new TnmPtpCollector()
				.getAllPtpInfos(endPointAddress)) {
			System.out.println(ptpInfo);
		}
	}

}
