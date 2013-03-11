package examples.files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryFile {

    public static void main(String[] args) throws IOException {
        // Write the data into the file:
//        DataOutputStream dataOut = new DataOutputStream(
//                new BufferedOutputStream(
//                new FileOutputStream("data.dat")));
//
//        dataOut.writeUTF("data");
//        dataOut.writeInt(100);
//        dataOut.writeLong(9999);
//
//        dataOut.close();

        // Read the data from the file - must use the same order!
        FileInputStream fileIn = new FileInputStream("data.dat");
        BufferedInputStream buffIn = new BufferedInputStream(fileIn);
        DataInputStream dataIn = new DataInputStream(buffIn);

//        System.out.println(dataIn.readUTF());
//        System.out.println(dataIn.readInt());
//        System.out.println(dataIn.readLong());

        //we can change the order of reading and still get results but they won't
        //have any meaning at all! 
        System.out.println(dataIn.readLong());
        System.out.println(dataIn.readInt());
        System.out.println(dataIn.readUTF());

        dataIn.close();
    }
}