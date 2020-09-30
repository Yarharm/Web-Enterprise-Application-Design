import java.util.ArrayList;

public class ChatManager {
    ArrayList<Message> messages;

    public ChatManager(){
        messages = new ArrayList<Message>();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void postMessage(String userName,  String content){
        this.messages.add(new Message(userName, content));
    }

    public void clearChat(long startTimeStamp, long endTimeStamp){
        for (Message m : this.messages){
            if (m.timestamp > startTimeStamp && m.timestamp < endTimeStamp){
                this.messages.remove(m);
            }
        }
    }

    public class Message{
        String userName;
        String message;
        long timestamp;

        public Message(String userName, String message){
            this.userName = userName;
            this.message = message;
            this.timestamp = System.currentTimeMillis();
        }
    }
}
