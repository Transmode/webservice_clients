package com.openadvice.itnm.types.tpr;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import com.openadvice.itnm.types.tpr.PtpInfo.Builder;

public class PtpInfoTest {

	private static final String NAME_NAME_VALUE_USER_LABEL_USER_LABEL_VALUE = "name=nameValue, userLabel=userLabelValue";
	private static final String USER_LABEL_VALUE = "userLabelValue";
	private static final String NAME_VALUE = "nameValue";

	@Test
	public void toString_should_return_rxSignalStatusAvailable_false() {
		assertThat(ptpInfoBuilder().build().toString(),
				equalTo(NAME_NAME_VALUE_USER_LABEL_USER_LABEL_VALUE));
	}

	private Builder ptpInfoBuilder() {
		return PtpInfo.builder().name(NAME_VALUE).userLabel(USER_LABEL_VALUE);
	}

	@Test
	public void toString_should_return_rxSignalStatusAvailable_true() {
		assertThat(ptpInfoBuilder().rxSignalStatus(1).build().toString(),
				equalTo(NAME_NAME_VALUE_USER_LABEL_USER_LABEL_VALUE
						+ ", rxSignalStatus=1"));
	}

}
