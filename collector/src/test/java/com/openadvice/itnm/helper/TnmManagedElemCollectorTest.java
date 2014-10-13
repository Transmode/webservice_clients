package com.openadvice.itnm.helper;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.openadvice.itnm.types.ManagedElement;
import org.tmforum.mtop.fmw.xsd.nam.v1.RelativeDistinguishNameType;
import org.tmforum.mtop.mri.wsdl.mer.v1_0.TnmNamingAttributeType;
import org.tmforum.mtop.nrb.xsd.cri.v1.ResourceStateEnumType;
import org.tmforum.mtop.nrb.xsd.cri.v1.ResourceStateType;
import org.tmforum.mtop.nrf.xsd.me.v1.CommunicationStateType;
import org.tmforum.mtop.nrf.xsd.me.v1.ManagedElementType;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class TnmManagedElemCollectorTest {

    private static final String MD_VALUE = "mdValue";
    private static final String ME_VALUE = "meValue";
    public static final String USER_LABEL_VALUE = "userLabelValue";
    public static final String PRODUCT_NAME_VALUE = "productNameValue";
    public static final String LOCATION_VALUE = "locationValue";

    @Ignore("For manual use")
    @Test
    public void getAllManagedElements_for_manual_use() {
        for (ManagedElement managedElement : new TnmManagedElemCollector()
                .getAllManagedElements("http://tnm:8080/soap/mtosi/ManagedElementRetrieval")) {
            System.out.println(managedElement);
        }
    }

    @Test
    public void managedElement_should_return_managedElement() {
        final ManagedElement actualManagedElement = TnmManagedElemCollector.managedElement(managedElementType());
        assertThat(actualManagedElement, equalTo(expectedManagedElement()));
        assertThat(actualManagedElement.getResourceState(), equalTo(2));
    }

    private ManagedElementType managedElementType() {
        final ManagedElementType managedElementType = new ManagedElementType();
        final TnmNamingAttributeType tnmNamingAttributeType = new TnmNamingAttributeType();
        {
            final RelativeDistinguishNameType mdRelativeDistinguishNameType = new RelativeDistinguishNameType();
            mdRelativeDistinguishNameType.setType("MD");
            mdRelativeDistinguishNameType.setValue(MD_VALUE);
            tnmNamingAttributeType.getRdn().add(mdRelativeDistinguishNameType);
        }
        {
            final RelativeDistinguishNameType meRelativeDistinguishNameType = new RelativeDistinguishNameType();
            meRelativeDistinguishNameType.setType("ME");
            meRelativeDistinguishNameType.setValue(ME_VALUE);
            tnmNamingAttributeType.getRdn().add(meRelativeDistinguishNameType);
        }
        managedElementType.setName(tnmNamingAttributeType);
        managedElementType.setUserLabel(USER_LABEL_VALUE);
        managedElementType.setProductName(PRODUCT_NAME_VALUE);
        managedElementType.setLocation(LOCATION_VALUE);
        managedElementType.setCommunicationState(CommunicationStateType.CS_AVAILABLE);
        final ResourceStateType resourceStateType = new ResourceStateType();
        resourceStateType.setValue(ResourceStateEnumType.INSTALLING_INSTALLED);
        managedElementType.setResourceState(resourceStateType);
        managedElementType.setIsInSyncState(true);
        return managedElementType;
    }

    private ManagedElement expectedManagedElement() {
        final ManagedElement expectedManagedElement = new ManagedElement();
        expectedManagedElement.setMd(MD_VALUE);
        expectedManagedElement.setMe(ME_VALUE);
        expectedManagedElement.setUserLabel(USER_LABEL_VALUE);
        expectedManagedElement.setProductName(PRODUCT_NAME_VALUE);
        expectedManagedElement.setCommunicationState(CommunicationStateType.CS_AVAILABLE.toString());
        expectedManagedElement.setIsInSyncState(true);
        expectedManagedElement.setResourceState((ResourceStateEnumType.INSTALLING_INSTALLED.toString()));
        expectedManagedElement.setLocation(LOCATION_VALUE);
        return expectedManagedElement;
    }
}
