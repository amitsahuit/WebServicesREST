package com.amit.webservices.jaxrsDemoProject.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.amit.webservices.jaxrsDemoProject.messanger.model.ErrorMessage;

@Provider
public class DateNotFoundExceptionMapper implements  ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "Simple documentation link can be provided here.");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}
	
	

}
