package business_layer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ChatManager {
    private final TreeMap<Long, List<ChatMessage>> messageStorage;

    public ChatManager() {
        this.messageStorage = new TreeMap<>();
    }

    public ChatMessage postMessage(String user, String message) {
        long timestamp = System.currentTimeMillis();
        ChatMessage chatMessage = new ChatMessage(user, message, timestamp);
        this.addChatMessage(timestamp, chatMessage);
        return chatMessage;
    }

    private void addChatMessage(long timestamp, ChatMessage chatMessage) {
        this.messageStorage.putIfAbsent(timestamp, new ArrayList<>());
        this.messageStorage.get(timestamp).add(chatMessage);
    }
}
