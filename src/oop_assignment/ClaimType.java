package oop_assignment;

import java.io.Serializable;

public class ClaimType implements Serializable {
	
	public 	String claimTypeID, claimTypeName, appToPosition;
	float limit;

	public ClaimType () {
	}
	
	public void setClaimTypeID (String claimTypeID) {
		this.claimTypeID = claimTypeID;
	}
	
	public String getClaimTypeID () {
		return claimTypeID;
	}
	
	public void setClaimTypeName (String claimTypeName) {
		this.claimTypeName = claimTypeName;
	}
	
	public String getClaimTypeName () {
		return claimTypeName;
	}
	
	public void setAppToPosition (String appToPosition) {
		this.appToPosition = appToPosition;
	}
	
	public String getAppToPosition () {
		return appToPosition;
	}
	
	public void setLimit (float limit) {
		this.limit = limit;
	}
	
	public float getLimit () {
		return limit;
	}

}