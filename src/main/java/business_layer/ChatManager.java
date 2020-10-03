package business_layer;

import java.util.SortedMap;
import java.util.TreeMap;


class MessageInfo{
    private String chat_name;
    private String message;

    MessageInfo(String msg){
        chat_name = "Anonymous";
        message=msg;
    }
    MessageInfo(String cn, String msg){
        chat_name = cn;
        message=msg;
    }



    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString(){
        return this.getChat_name() +"," +this.getMessage()+";";
    }
}




public class ChatManager {
    private Long chat_time;
    private TreeMap<Long, MessageInfo> chatmanager;

    ChatManager() {
        chatmanager  = new TreeMap<Long, MessageInfo>();
    }


    public TreeMap PostMessage(String user, String message) throws InterruptedException {

        if((user=="")||(user.isEmpty())){
            user = "Anonymous";
        }
        MessageInfo messagenew = new MessageInfo(user, message);
        chat_time = System.currentTimeMillis();
        this.chatmanager.put(chat_time, messagenew);
        Thread.sleep(1);



        return this.chatmanager;
    }



    public SortedMap ListMessages(Long x, Long y) throws InterruptedException {
        SortedMap<Long, MessageInfo> treemapincl = new TreeMap<Long, MessageInfo>();
        treemapincl = chatmanager.subMap(x,y);
        return treemapincl;

    }
    //WIP
    public SortedMap ClearChat(Long x, Long y) throws InterruptedException {
        SortedMap<Long, MessageInfo> treemapincl = new TreeMap<Long, MessageInfo>();
        treemapincl = chatmanager.subMap(x,y);

        return treemapincl;

    }

    public long getChat_time() {
        return chat_time;
    }


    public void setChat_time(long chat_time) {
        this.chat_time = chat_time;
    }

    public TreeMap<Long, MessageInfo> getChatmanager() {
        return chatmanager;
    }

    public void setChatmanager(TreeMap<Long, MessageInfo> chatmanager) {
        this.chatmanager = chatmanager;
    }

    @Override
    public String toString() {
        return "ChatManager{chatmanager=" + chatmanager +
                '}';
    }



//Tester doesn't need to be here
    public static void main(String args[]) throws InterruptedException {
        ChatManager chatBox = new ChatManager();
        chatBox.PostMessage("","Hello World");

        chatBox.PostMessage("Bob","Hello Worlod");

        chatBox.PostMessage("Bob","Hello Wosddrld");


        chatBox.PostMessage("Bob","Hello Woasdrld");



        System.out.println(chatBox);
        //make sure to include L
        System.out.println(chatBox.ListMessages(0L,1611757506300L));


        // creating maps






    }




}