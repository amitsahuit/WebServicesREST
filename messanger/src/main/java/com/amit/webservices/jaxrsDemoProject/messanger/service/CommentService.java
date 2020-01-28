package com.amit.webservices.jaxrsDemoProject.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.amit.webservices.jaxrsDemoProject.messanger.database.DatabaseClass;
import com.amit.webservices.jaxrsDemoProject.messanger.model.Comment;
import com.amit.webservices.jaxrsDemoProject.messanger.model.ErrorMessage;
import com.amit.webservices.jaxrsDemoProject.messanger.model.Message;

public class CommentService {

	private static Map<Long, Message> messages = DatabaseClass.getMessage();
	
	public List<Comment> getAllComments(Long messageId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId){
		
		ErrorMessage errorMessage = new ErrorMessage("NOT FOUND", 404, "Simple documentation link can be provided here.");
		Response res = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		
		Message message = messages.get(messageId);
		if(message == null) {
			//throw new WebApplicationException(Status.NOT_FOUND);
			throw new WebApplicationException(res);
		}
		
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if(comment == null) {
			//throw new WebApplicationException(Status.NOT_FOUND);
			//throw new WebApplicationException(res);
			throw new NotFoundException(res);
		}
		
		return comments.get(commentId);
	}
	
	public Comment addComment(Long messageId, Comment comment){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(Long messageId, Comment comment){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0){
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(Long messageId, long commentId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
		
	}
}
