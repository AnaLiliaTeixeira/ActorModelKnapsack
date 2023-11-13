package library;

import java.util.concurrent.ConcurrentLinkedQueue;

import library.messages.Message;
import library.messages.SystemKillMessage;

public abstract class Actor extends Thread {

	public Actor() {
		this.start();
	}
    
    public Address getAddress() {
		return (Message m) -> {
			mailbox.add(m);
		};
	}
	ConcurrentLinkedQueue<Message> mailbox = new ConcurrentLinkedQueue<>();

    public void run() {
		while (true) {
			Message m = mailbox.poll();
			if (m == null) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				handleMessage(m);
				if (m instanceof SystemKillMessage)  {
					return;
				}
			}
		}
	}

	protected abstract void handleMessage(Message m);
}