package library;

import library.messages.Message;

// @FunctionalInterface
public interface Address {
	public void sendMessage(Message m);
}