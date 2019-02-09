package javaAssignment;

public class ClaimType {

	String claimTypeID, claimTypeName, appToPosition;
	float limit;
	
	public ClaimType () {
	}
	
	void setClaimTypeID (String newClaimTypeID) {
		claimTypeID = newClaimTypeID;
	}
	
	String getClaimTypeId () {
		return claimTypeID;
	}
	
	void setClaimTypeName (String newClaimTypeName) {
		claimTypeName = newClaimTypeName;
	}
	
	String getClaimTypeName () {
		return claimTypeName;
	}
	
	void setAppToPosition (String newAppToPosition) {
		appToPosition = newAppToPosition;
	}
	
	String getAppToPosition () {
		return appToPosition;
	}
	
	void setLimit (float newLimit) {
		limit = newLimit;
	}
	
	float getLimit () {
		return limit;
	}

}