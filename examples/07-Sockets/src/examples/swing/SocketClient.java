package examples.swing;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

    private Socket socket;

    private ServerMessageListener messageListener;
    private PrintWriter out;

    public SocketClient(String host, int port, ServerMessageListener messageListener) {
        this.messageListener = messageListener;

        try {
            //creates a socket to the server
            socket = new Socket(host, port);

            //starts a thread that receives data from the server and writes it to the system console
            ServerInputListener responseThread =
                    new ServerInputListener(socket.getInputStream());

            //make that thread a daemon thread which means that if there are no more threads in this process
            //the process will quit
            responseThread.setDaemon(true);
            //starts the thread
            responseThread.start();

            //gets user input from the system console and sends it to the server output stream
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            messageListener.onError(e);
        }
    }

    public void close() {
        try {
            //since handleUserInput is not running is a separate thread
            //we will close the socket only AFTER the user has exited the input loop
            //meaning, the client needs to shutdown
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

    public class ServerInputListener extends Thread {

        BufferedReader reader;

        //listens to input from the input stream and for every line that arrives
        //sends it to the output stream
        //can be run in a thread or just as a method
        ServerInputListener(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        @Override
        public void run() {
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    messageListener.onServerMessage(line);
                }
            } catch (IOException x) {
            } finally {
                try {
                    reader.close();
                } catch (Exception e) {
                }
            }
        }
    }
}