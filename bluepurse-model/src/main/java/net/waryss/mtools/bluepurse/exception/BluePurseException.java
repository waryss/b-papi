package net.waryss.mtools.bluepurse.exception;

public class BluePurseException extends Exception {

	private static final long serialVersionUID = 1L;
	private String additionalMessage;
	private BluePurseErrorCode code;

	public BluePurseException() {
		super();
	}

	public BluePurseException(BluePurseErrorCode code) {
		super(code.getMessage());
		this.code = code;
	}

	public BluePurseException(BluePurseErrorCode code, String additionalMessage) {
		super(code.getMessage());
		this.code = code;
		this.additionalMessage = additionalMessage;
	}

	public BluePurseException(String message) {
		super(message);
	}

	public BluePurseErrorCode getCode() {
		return code;
	}

	public void setCode(BluePurseErrorCode code) {
		this.code = code;
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}

	public void setAdditionalMessage(String additionalMessage) {
		this.additionalMessage = additionalMessage;
	}
}
