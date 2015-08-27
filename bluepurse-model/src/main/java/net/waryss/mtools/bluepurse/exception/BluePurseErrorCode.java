package net.waryss.mtools.bluepurse.exception;

public enum BluePurseErrorCode {
	// Technical exceptions from 10 to 99
	BP_10("Technical error occured"),
	BP_11("Database exception"),
	// Business exception from 100 to 999
	BP_100 ("Business Exception"),
	BP_101 ("Invalid element"),
	BP_102 ("Element not found"),
	BP_103 ("Incoherent request data");

	private String message;

	BluePurseErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static BluePurseErrorCode getDefaultTechnicalCode(){
		return BP_10;
	}

	public static BluePurseErrorCode getDefaultBusinessCode(){
		return BP_100;
	}
}
