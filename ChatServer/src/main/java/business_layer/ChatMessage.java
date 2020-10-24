package business_layer;

import java.util.Date;

public class ChatMessage {
    private final String user;
    private final String message;
    private final Date timestamp;

    public ChatMessage(String user, String message, long timestamp){
        this.user = user;
        this.message = message;
        this.timestamp = new Date(timestamp);
    }

    public ChatMessage(String message, long timestamp) {
        this.user = "Anonymous";
        this.message = message;
        this.timestamp = new Date(timestamp);
    }

    public String getUser() {
        return this.user;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "user='" + user + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
