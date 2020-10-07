package business_layer;

import java.util.*;
import java.util.stream.Collectors;


public class ChatManager {
    private Long chatTime;
    private TreeMap<Long, ArrayList<ChatMessage>> chatmanager;

    public ChatManager() {
        chatmanager = new TreeMap<Long, ArrayList<ChatMessage>>();

    }


    public ArrayList<ChatMessage> PostMessage(String user, String message) {
        chatTime = System.currentTimeMillis();
        ChatMessage messagenew;
        if(user==""||user.isEmpty()){
            messagenew = new ChatMessage(message, chatTime);
        }else{
            messagenew = new ChatMessage(user, message, chatTime);
        }

        if(chatmanager.containsKey(chatTime)){
            ArrayList<ChatMessage> pos = chatmanager.get(chatTime);
            pos.add(messagenew);
        }else {
            ArrayList<ChatMessage> timeList = new ArrayList<ChatMessage>();
            timeList.add(messagenew);
            this.chatmanager.put(chatTime, timeList);

        }
        return chatmanager.get(chatmanager.lastKey());

    }


    public List<ChatMessage> ListMessages(Long x, Long y) {
        SortedMap<Long, ArrayList<ChatMessage>> treemapincl = new TreeMap<Long, ArrayList<ChatMessage>>();
        treemapincl = this.chatmanager.subMap(x,true, y,true);

        return treemapincl.values().stream().flatMap(List::stream).collect(Collectors.toList());

    }


    public List<ChatMessage> ClearMessages(){

        this.chatmanager.clear();
        return this.chatmanager.values().stream().flatMap(List::stream).collect(Collectors.toList());

    }
    public List<ChatMessage> ClearMessages(Long x, Long y) {
        SortedMap<Long, ArrayList<ChatMessage>> treemapincl = new TreeMap<Long, ArrayList<ChatMessage>>();
        this.chatmanager.subMap(x,true, y,true).clear();
        treemapincl = this.chatmanager;

        return treemapincl.values().stream().flatMap(List::stream).collect(Collectors.toList());


    }

<<<<<<< 69708ca62f3787fb529294f467ca469c028884a8
=======
    public List<ChatMessage> ListMessages(){
        SortedMap<Long, ArrayList<ChatMessage>> treemapincl = new TreeMap<Long, ArrayList<ChatMessage>>();
        ArrayList<ArrayList<ChatMessage>> holder = new ArrayList<>();
        treemapincl = this.chatmanager.subMap(Long.valueOf(0),true, System.currentTimeMillis(),true);
        return treemapincl.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }


>>>>>>> fixed commented issues
    @Override
    public String toString() {
        return "ChatManager{chatmanager=" + chatmanager +
                '}';
    }


}



