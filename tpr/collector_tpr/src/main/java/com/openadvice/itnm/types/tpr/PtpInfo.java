package com.openadvice.itnm.types.tpr;

/**
 * Simple class to model information retrieved from a PTP.
 */
public class PtpInfo {
	private final String name;
	private final String userLabel;
	private final Integer rxSignalStatus;

	private PtpInfo(String name, String userLabel,
			Integer rxSignalStatus) {
		super();
		this.name = name;
		this.userLabel = userLabel;
		this.rxSignalStatus = rxSignalStatus;
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder().append("name=")
				.append(name).append(", userLabel=").append(userLabel);
		if (rxSignalStatus != null) {
			stringBuilder.append(", rxSignalStatus=").append(rxSignalStatus);
		}
		return stringBuilder.toString();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String name = "";
		private String userLabel = "";
		private Integer rxSignalStatus = null;

		public Builder userLabel(String userLabel) {
			this.userLabel = userLabel;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder rxSignalStatus(int rxSignalStatus) {
			this.rxSignalStatus = rxSignalStatus;
			return this;
		}

		public PtpInfo build() {
			return new PtpInfo(name, userLabel, rxSignalStatus);
		}

	}
}
