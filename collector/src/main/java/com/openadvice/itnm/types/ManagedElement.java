package com.openadvice.itnm.types;

import java.util.Objects;

public class ManagedElement {
	private String md;
	private String me;
	private String userLabel;
	private String resourceState;
	private String location;
	private String productName;
	private boolean isInSyncState;
	private String communicationState;

	public String getMd() {
		return md;
	}

	public void setMd(String md) {
		this.md = md;
	}

	public String getMe() {
		return me;
	}

	public void setMe(String me) {
		this.me = me;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public int getResourceState() {
		if (resourceState.equals("INSTALLING_INSTALLED"))
			return 2;
		else
			return 0;
	}

	public void setResourceState(String resourceState) {
		this.resourceState = resourceState;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public boolean getIsInSyncState() {
		return isInSyncState;
	}

	public void setIsInSyncState(boolean isInSyncState) {
		this.isInSyncState = isInSyncState;
	}

	public String getCommunicationState() {
		return communicationState;
	}

	public void setCommunicationState(String communicationState) {
		this.communicationState = communicationState;
	}

	@Override
	public String toString() {
		return "ManagedElement [md=" + md + ", me=" + me + ", userLabel="
				+ userLabel + ", resourceState=" + resourceState
				+ ", location=" + location + ", productName=" + productName
				+ ", isInSyncState=" + isInSyncState + ", communicationState="
				+ communicationState + "]\n";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof ManagedElement) {
			final ManagedElement that = (ManagedElement) obj;
			return Objects.equals(communicationState, that.communicationState)
					&& Objects.equals(location, that.location)
					&& Objects.equals(productName, that.productName)
					&& Objects.equals(resourceState, that.resourceState)
					&& Objects.equals(userLabel, that.userLabel)
					&& Objects.equals(me, that.me)
					&& Objects.equals(isInSyncState, that.isInSyncState)
					&& Objects.equals(md, that.md);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(md, me, userLabel, resourceState, location,
				productName, isInSyncState, communicationState);
	}
}
