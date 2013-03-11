package examples.streams.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) throws Exception {
        int port = 23;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);
        while (true) {
            //wait until a client connects to this server at the listening port
            final Socket socket = serverSocket.accept();
            //this code is run only AFTER a client has successfully initialized a connection
            //open a stream to/from the client and start communicating with it
            final BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Welcome to our server!");

            //in order to make the server available again, meaning, listen to the port
            //we do all the communication WITH EACH CLIENT IN A DIFFERENT THREAD
            new Thread(new Runnable() {

                @Override
                public void run() {
                    String line;
                    try {
                        int lineCounter = 0;
                        while ((line = in.readLine()) != null) {
                            System.out.println(++lineCounter + " " + line);
                            out.println("HTTP/1.0 200 OK");
                            out.println("<html>this is a very stupid web page!</html>");
//                            out.println("<html>Server Says: No, " + line.toUpperCase()+"</html>");
//                            out.println("Server Says: No, " + line.toUpperCase());
                            socket.close();
                        }
                    } catch (IOException e) {
                        // we handle the exception by ending the thread
                        // which happens automatically by being thrown
                        // from the loop
                    }
                }
            }).start();
        }
        // need to add try+finally
        // and close the socket and any open streams
        // in the finally block!
    }
}