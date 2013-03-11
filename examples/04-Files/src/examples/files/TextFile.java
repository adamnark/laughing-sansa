package examples.files;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/*
 * This is an example on how to write a text file - which is encoded into UTF8
 */
public class TextFile {

    public static void main(String[] args) throws Exception {

        //the text.txt file will be created in the run directory
        //which is where the java command was called
        Writer out = new BufferedWriter(
                        new OutputStreamWriter(
                            new FileOutputStream("text.txt"), "UTF8"));

        out.write("Hello\r\n");
        out.write("שלום\r\n");
        out.close();
    }
}