package business_layer;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class ChatManager {
    private Long chatTime;
    private TreeMap<Long, ArrayList<ChatMessage>> chatmanager;

    ChatManager() {
        chatmanager = new TreeMap<Long, ArrayList<ChatMessage>>();

    }


    public Map.Entry<Long, ArrayList<ChatMessage>> PostMessage(String user, String message) throws InterruptedException {
        chatTime = System.currentTimeMillis();
        ChatMessage messagenew = new ChatMessage(user, message, chatTime);


        if(chatmanager.containsKey(chatTime)){
            ArrayList<ChatMessage> pos = chatmanager.get(chatTime);
            pos.add(messagenew);
        }else {
            ArrayList<ChatMessage> timeList = new ArrayList<ChatMessage>();
            timeList.add(messagenew);
            this.chatmanager.put(chatTime, timeList);

        }
        return chatmanager.lastEntry();

    }


    public SortedMap ListMessages(Long x, Long y) throws InterruptedException {
        SortedMap<Long, ArrayList<ChatMessage>> treemapincl = new TreeMap<Long, ArrayList<ChatMessage>>();
        treemapincl = this.chatmanager.subMap(x,true, y,true);
        treemapincl.values();
        return treemapincl;

    }

    public TreeMap ClearMessages(Long x, Long y) throws InterruptedException {
        SortedMap<Long, ArrayList<ChatMessage>> treemapincl = new TreeMap<Long, ArrayList<ChatMessage>>();
        treemapincl = this.chatmanager.subMap(x,true, y,true);

        ArrayList sortedKeysList = new ArrayList<>(treemapincl.keySet());
        chatmanager.keySet().removeAll(sortedKeysList);

        return chatmanager;

    }
    public TreeMap ClearMessages() throws InterruptedException {

        chatmanager.clear();
        return chatmanager;

    }


    @Override
    public String toString() {
        return "ChatManager{chatmanager=" + chatmanager +
                '}';
    }


//    //Tester doesn't need to be here
//    public static void main(String args[]) throws InterruptedException {
//        ChatManager chatBox = new ChatManager();
//        chatBox.PostMessage("", "Hello");
//
//        chatBox.PostMessage("Bob", "Hello World");
//        Thread.sleep(1000);
//
//        chatBox.PostMessage("Bob", "Hld");
//        chatBox.PostMessage("", "Hello");
//
//        chatBox.PostMessage("Bob", "Hello World");
//
//
//
//
//        System.out.println(chatBox);
//
//
//        //make sure to include L
//       System.out.println(chatBox.ListMessages(1601845004260L, 1601845192245L));
//
//        System.out.println(chatBox.ClearMessages(1601846232701L,1601846482281L));
//        System.out.println(chatBox.ClearMessages());
//
//
//
//
//        // creating maps
//
//
//    }

}




