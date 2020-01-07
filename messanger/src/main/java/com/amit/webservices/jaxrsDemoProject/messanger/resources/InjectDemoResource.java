package com.amit.webservices.jaxrsDemoProject.messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("authSessionID") String header,
											@CookieParam("name") String cookie) {
		return "matrix param value is: " +matrixParam+", auth Session ID is: "+header+", Cookie name is: "+cookie;
	}
	
	@GET
	@Path("context")
	public String getParamsByUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeader) {
		
		String path = uriInfo.getAbsolutePath().toString();
		String cookie = httpHeader.getCookies().toString();
		return "URI Path is: "+path+", Cookie value is: "+cookie;
	}
	
	
	
}
