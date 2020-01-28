package com.amit.webservices.jaxrsDemoProject.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.amit.webservices.jaxrsDemoProject.messanger.model.ErrorMessage;

//@Provider
public class GenericExceptionMapper implements  ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500, "Simple documentation link can be provided here.");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}
	
	

}
