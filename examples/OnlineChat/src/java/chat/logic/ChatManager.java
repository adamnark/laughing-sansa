package chat.logic;

import java.util.ArrayList;

/**
 *
 * @author blecherl
 */
public class ChatManager {
    private ArrayList<ChatData> chatDataList;

    public ChatManager() {
        chatDataList = new ArrayList<ChatData>();
    }

    public void addChatString(String chatString, String username) {
        chatDataList.add(new ChatData(chatString, username));
    }

    public String getChat() {
        return getChat(0);
    }

    public String getChat(int fromIndex) {
        if (fromIndex < 0 || fromIndex >= chatDataList.size()) {
            fromIndex = 0;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (ChatData chatData : chatDataList.subList(fromIndex, chatDataList.size())) {
            stringBuilder.append(chatData.toString()).append("\r\n");
        }
        return stringBuilder.toString();
    }

    public int getVersion() {
        return chatDataList.size();
    }



    class ChatData {
        private String chatString;
        private String username;
        private long time;

        public ChatData(String chatString, String username) {
            this.chatString = chatString;
            this.username = username;
            this.time = System.currentTimeMillis();
        }

        public String getChatString() {
            return chatString;
        }

        public long getTime() {
            return time;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public String toString() {
            return (username != null ? username + ": " : "") + chatString;
        }
    }
}
