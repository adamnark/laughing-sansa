package examples.streams.advanced.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class ChatServer extends Observable {
    int port;
    boolean isAlive;

    public ChatServer(int port) {
        this.port = port;
        this.isAlive = true;
    }

    public void startServer() {
        ServerSocket serverSocket = null;
        try {
            System.out.println("Server> Server is ready...");
            //create new server socket that waits for new connections
            serverSocket = new ServerSocket(port);

            int clientIndex = 0;
            while (isAlive) {
                //waits until a new connection is made
                //once it does - the method will stop block the code and return a socket to the client
                Socket socket = serverSocket.accept();

                //updates all the current clients with a message that a new client was added
                sendMessageToAllConnectedClients("Server> A new client: " + clientIndex);

                //creates a new client thread and starts it
                addClient(this, clientIndex, socket);

                ++clientIndex;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException x) {
                }
        }
    }

    public void addClient(ChatServer chatServer, int clientIndex, Socket socket) {
        //creates a new thread for handling communication with the new client
        ServerChatThread clientThread = new ServerChatThread(chatServer, clientIndex, socket);
        //adds the thread to the list of listeners to this server
        addObserver(clientThread);
        //starts the new chat client
        clientThread.start();
    }

    //This method is called from the ChatThread class (which means it runs in that
    public void sendMessageToAllConnectedClients(String message) {
        //log message to server console
        System.out.println(message);
        //set mode to changed so that notifyObservers will update its observers
        setChanged();
        //iterate over all observers and update them
        notifyObservers(message);
    }

    public void removeClient(int index, ServerChatThread clientThread) {
        sendMessageToAllConnectedClients("Server> Client " + index + " has left.");
        deleteObserver(clientThread);
    }
}