package com.amit.webservices.jaxrsDemoProject.messanger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentResource {

	@GET
	public String test() {
		return "new comment sub resources";
	}
	
	@GET
	@Path("/{commentId}")
	public String test2(@PathParam("messageId") int msgid, @PathParam("commentId") int comentid) {
		return "new comment sub resources with comment ID: "+comentid+" which has message id: "+msgid;
	}
}
