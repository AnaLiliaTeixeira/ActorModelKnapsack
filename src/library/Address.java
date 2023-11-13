package library;

import library.messages.Message;

// @FunctionalInterface
public interface Address {
	public void receiveMessage(Message m);
}