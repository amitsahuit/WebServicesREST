package com.amit.webservices.jaxrsDemoProject.messanger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.amit.webservices.jaxrsDemoProject.messanger.model.Message;
import com.amit.webservices.jaxrsDemoProject.messanger.resources.beans.MessageFilterBean;
import com.amit.webservices.jaxrsDemoProject.messanger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	
	/*@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessages() {
		return "getMessages method called.";
	}*/
	
	/*@GET
	//@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,
									 @QueryParam("start") int start,
									 @QueryParam("size") int size) {
		if(year > 0) {
			return messageService.getAllMessagesForYear(year);
		}
		
		if(start > 0 && size > 0) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}*/
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if(filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		
		if(filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	
	/*@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	}*/
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		//First make a static call of Response then create an entity then do a build.
		Message newMessage = messageService.addMessage(message);
		//return Response.created(new URI("messenger/webapi/messages/" + newMessage.getId())).status(Status.CREATED).entity(newMessage).build();
		
		String newId = String.valueOf(message.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage).build();
	}
	
	@PUT
	@Path("/{messageId}")
	/*@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)*/
	public Message update(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		//return "Got PathParam value as: "+messageId;
		//return messageService.getMessage(id);
		
		Message message = messageService.getMessage(id);	
		message.addLink(getUri(uriInfo, message), "Self");
		return message;
		
	}

	private String getUri(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		.path(Long.toString(message.getId()))
		.build()
		.toString();
		return uri;
	}
	
	//For Comments. NOTE: NO action is included..
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	} 
	
}
