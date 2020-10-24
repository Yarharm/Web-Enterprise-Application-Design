package business_layer;

import java.util.*;
import java.util.stream.Collectors;

/*
    ChatManager implements Singleton design pattern to ensure that
    only one instance of data is shared across the application.
 */
public class ChatManager {
    private static final ChatManager chatManager = new ChatManager();
    private final TreeMap<Long, ArrayList<ChatMessage>> chatManagerStorage;

    public static ChatManager getChatManagerInstance(){
        return chatManager;
    }

    private ChatManager() {
        chatManagerStorage = new TreeMap<Long, ArrayList<ChatMessage>>();
    }

    public ArrayList<ChatMessage> PostMessage(String user, String message) {
        Long chatTime = System.currentTimeMillis();
        ChatMessage messagenew;
        if(user == null || user.isEmpty()){
            messagenew = new ChatMessage(message, chatTime);
        }else{
            messagenew = new ChatMessage(user, message, chatTime);
        }

        if(chatManagerStorage.containsKey(chatTime)){
            ArrayList<ChatMessage> pos = chatManagerStorage.get(chatTime);
            pos.add(messagenew);
        }else {
            ArrayList<ChatMessage> timeList = new ArrayList<ChatMessage>();
            timeList.add(messagenew);
            this.chatManagerStorage.put(chatTime, timeList);

        }
        return chatManagerStorage.get(chatManagerStorage.lastKey());

    }

    public List<ChatMessage> ListMessages() {
        return this.chatManagerStorage.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public List<ChatMessage> ListMessages(Long x, Long y) {
        SortedMap<Long, ArrayList<ChatMessage>> treemapincl = new TreeMap<Long, ArrayList<ChatMessage>>();
        treemapincl = this.chatManagerStorage.subMap(x,true, y,true);

        return treemapincl.values().stream().flatMap(List::stream).collect(Collectors.toList());

    }

    public List<ChatMessage> ClearMessages(Long x, Long y) {
        SortedMap<Long, ArrayList<ChatMessage>> treemapincl = new TreeMap<Long, ArrayList<ChatMessage>>();
        this.chatManagerStorage.subMap(x,true, y,true).clear();
        treemapincl = this.chatManagerStorage;

        return treemapincl.values().stream().flatMap(List::stream).collect(Collectors.toList());


    }

    @Override
    public String toString() {
        return "ChatManager{chatmanager=" + chatManagerStorage +
                '}';
    }
}



