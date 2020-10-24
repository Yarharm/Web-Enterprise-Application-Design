package helpers;

import business_layer.ChatMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/*
    Manager for the chat window content on the Frontend.
 */
public class FrontendChatManager {
    private static final String CHAT_WINDOW_APPLICATION_ATTRIBUTE = "chatWindow";

    public static void filterChatWindow(HttpServletRequest request, List<ChatMessage> filteredMessages) {
        List<ChatMessage> chatWindow = fetchChatWindow(request);
        chatWindow.retainAll(filteredMessages);
    }

    public static void appendChatWindow(HttpServletRequest request, List<ChatMessage> chatMessages) {
        List<ChatMessage> chatWindow = fetchChatWindow(request);
        chatWindow.addAll(chatMessages);
    }

    private static List<ChatMessage> fetchChatWindow(HttpServletRequest request) {
        List<ChatMessage> chatWindow = (List<ChatMessage>) request.getServletContext().getAttribute(CHAT_WINDOW_APPLICATION_ATTRIBUTE);
        if(chatWindow == null) {
            chatWindow = new ArrayList<>();
            request.getServletContext().setAttribute(CHAT_WINDOW_APPLICATION_ATTRIBUTE, chatWindow);
        }
        return chatWindow;
    }
}
