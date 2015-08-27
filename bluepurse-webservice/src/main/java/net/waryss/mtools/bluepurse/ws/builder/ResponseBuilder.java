package net.waryss.mtools.bluepurse.ws.builder;

import net.waryss.mtools.bluepurse.exception.BluePurseErrorCode;
import net.waryss.mtools.bluepurse.exception.BluePurseException;
import net.waryss.mtools.bluepurse.ws.util.ResponseWrapper;
import net.waryss.mtools.bluepurse.ws.util.ResponseWrapper.Severity;

public class ResponseBuilder<T> {

	private ResponseWrapper<T> responseWrapper;

	public ResponseBuilder() {
		responseWrapper = new ResponseWrapper<T>();
	}

	public ResponseBuilder<T> addMessage(String message) {
		responseWrapper.setMessage(message);
		return this;
	}

	public ResponseBuilder<T> addDetails(String details) {
		responseWrapper.setDetails(details);
		return this;
	}

	public ResponseBuilder<T> addSeverity(Severity severity) {
		responseWrapper.setSeverity(severity);
		return this;
	}

	public ResponseBuilder<T> addResult(T result) {
		responseWrapper.setResult(result);
		return this;
	}

	public ResponseBuilder<T> addCode(String code) {
		responseWrapper.setCode(code);
		return this;
	}

	public ResponseWrapper<T> buildSucces(String message, T result) {
		addSeverity(ResponseWrapper.Severity.SUCESS).addMessage(message).addResult(result);
		return responseWrapper;
	}

	/*
	 * Build error response for all BluePurseException exceptions
	 */
	public ResponseWrapper<?> buildError(BluePurseException ex) {
		addSeverity(ResponseWrapper.Severity.ERROR).addCode(ex.getCode().name()).addMessage(ex.getMessage()).addDetails(ex.getAdditionalMessage());
		return responseWrapper;
	}

	/*
	 * Build default response for any other exceptions
	 */
	public ResponseWrapper<?> buildError(Exception ex) {
		addSeverity(ResponseWrapper.Severity.ERROR).addCode(BluePurseErrorCode.getDefaultTechnicalCode().name()).addMessage(BluePurseErrorCode.getDefaultTechnicalCode().getMessage()).addDetails(ex.getMessage());
		return responseWrapper;
	}
}
