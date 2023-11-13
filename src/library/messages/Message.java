package library.messages;

import library.Address;

public class Message {
    private Address senderAddress;

    public void setSenderAddress(Address senderAddress) {
        this.senderAddress = senderAddress;
    }

    public Address getSenderAddress() {
        return senderAddress;
    }
}
