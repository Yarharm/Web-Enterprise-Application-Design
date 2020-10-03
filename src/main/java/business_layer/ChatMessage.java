package business_layer;

public class ChatMessage {
    private final String user;
    private final String message;
    private final long timestamp;

    public ChatMessage(String user, String message, long timestamp){
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUser() {
        return this.user;
    }

    public String getMessage() {
        return this.message;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
