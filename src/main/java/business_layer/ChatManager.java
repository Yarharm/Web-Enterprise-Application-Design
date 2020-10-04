package business_layer;

import java.util.*;


public class ChatManager {
    private Long chatTime;
    private TreeMap<Long, ArrayList<ChatMessage>> chatmanager;

    ChatManager() {
        chatmanager = new TreeMap<Long, ArrayList<ChatMessage>>();

    }


    public ArrayList PostMessage(String user, String message) throws InterruptedException {
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


    public ArrayList ListMessages(Long x, Long y) throws InterruptedException {
        SortedMap<Long, ArrayList<ChatMessage>> treemapincl = new TreeMap<Long, ArrayList<ChatMessage>>();
        ArrayList<ArrayList<ChatMessage>> holder = new ArrayList<>();
        treemapincl = this.chatmanager.subMap(x,true, y,true);
        holder.addAll(treemapincl.values());
        return holder;

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
//        Thread.sleep(1000);
//
//        chatBox.PostMessage("", "Hello");
//
//        System.out.print(chatBox.PostMessage("Bob", "Hello World"));
//
//
//
//
//        System.out.println(chatBox);
//
//
////        //make sure to include L
//       System.out.println(chatBox.ListMessages(1601852620961L, 1601852720961L));
//       System.out.println(chatBox.ClearMessages());
////
////        System.out.println(chatBox.ClearMessages(1601846232701L,1601846482281L));
////        System.out.println(chatBox.ClearMessages());
//
//
//
//
//        // creating maps
//
//
//    }

}







