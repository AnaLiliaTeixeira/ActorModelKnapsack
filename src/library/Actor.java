package library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import library.messages.Message;
import library.messages.SystemKillMessage;

public abstract class Actor extends Thread {

	private boolean running = true;
	private ConcurrentLinkedQueue<Message> mailbox = new ConcurrentLinkedQueue<>();
	private List<Actor> children = new ArrayList<>();

	public Actor() {
		this.start();
	}
    
    public Address getAddress() {
		return (Message m) -> {
			mailbox.add(m);
		};
	}


    public void run() {
		while (running || !mailbox.isEmpty()) {
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
					running = false;
                    for (Actor a : children) {
						this.send(m, a.getAddress());
						System.out.println("kill message sent");
                    }
				}
			}
		}
	}

	public Actor launchActor(Actor a) {
        this.children.add(a);
        return a;
    }

	public static void sendFromMain(Message m, Address a) {
        a.receiveMessage(m);
    }

	public void send(Message m, Address a) {
        m.setSenderAddress(this.getAddress());
        a.receiveMessage(m);
    }

	protected abstract void handleMessage(Message m);
}