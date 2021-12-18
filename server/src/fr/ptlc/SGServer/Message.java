package fr.ptlc.SGServer;

public class Message {
	
	String sender;
	
	String content;
	
	public Message(String sender, String content) {
		this.sender = sender;
		this.content = content;
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getContent() {
		return content;
	}
	
}
