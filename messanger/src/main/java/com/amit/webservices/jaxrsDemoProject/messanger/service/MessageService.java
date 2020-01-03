package com.amit.webservices.jaxrsDemoProject.messanger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amit.webservices.jaxrsDemoProject.messanger.database.DatabaseClass;
import com.amit.webservices.jaxrsDemoProject.messanger.model.Message;

public class MessageService {

	private static Map<Long, Message> messages = DatabaseClass.getMessage();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hi Neha", "Amit"));
		messages.put(2L, new Message(2, "Hi Amit", "Neha"));
	}

	public List<Message> getAllMessages(){
		/*Message m1 = new Message(1, "Hi Neha", "Amit");
		Message m2 = new Message(2, "Hi Amit", "Neha");
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		return list;*/
		return new ArrayList<Message>(messages.values());
	}
	
	/*public List<Message> getAllMessagesForYear(int year){
		
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		
	}*/
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <= 0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
		
	}
}

