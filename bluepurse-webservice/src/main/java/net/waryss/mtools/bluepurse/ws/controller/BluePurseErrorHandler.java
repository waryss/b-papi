package net.waryss.mtools.bluepurse.ws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.waryss.mtools.bluepurse.exception.BluePurseException;
import net.waryss.mtools.bluepurse.ws.builder.ResponseBuilder;
import net.waryss.mtools.bluepurse.ws.util.ResponseWrapper;

@ControllerAdvice
public class BluePurseErrorHandler {

	@ExceptionHandler(BluePurseException.class)
	@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
	@ResponseBody
	public ResponseWrapper<?> handleException(BluePurseException ex) {
		return new ResponseBuilder<BluePurseException>().buildError(ex);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
	@ResponseBody
	public ResponseWrapper<?> handleException(Exception ex) {
		return new ResponseBuilder<Exception>().buildError(ex);
	}
}
