/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.swing.events;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blecherl
 */
public class FileWriterListener implements EventsListener{

    FileWriter fileWriter;
    public FileWriterListener() {
        try {
            fileWriter = new FileWriter("file.txt");
        } catch (IOException ex) {
            Logger.getLogger(FileWriterListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void eventHappened(MyEvent event) {
        try {
            fileWriter.write(event.getMessage() + "\n");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(FileWriterListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
