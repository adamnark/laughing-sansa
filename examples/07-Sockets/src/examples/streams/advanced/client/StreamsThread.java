package examples.streams.advanced.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class StreamsThread extends Thread {
    BufferedReader reader;
    PrintWriter writer;

    //listens to input from the input stream and for every line that arrives
    //sends it to the output stream
    //can be run in a thread or just as a method
    StreamsThread(InputStream in, OutputStream out) {
        reader = new BufferedReader(
                new InputStreamReader(in));
        writer = new PrintWriter(
                new OutputStreamWriter(out));
    }

    public void run() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.println(line);
                writer.flush();
            }
        } catch (IOException x) {
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
            }
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
