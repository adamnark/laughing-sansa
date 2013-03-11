package examples.files;

import java.util.Arrays;

/**
 *
 * @author blecherl
 */
public class FolderScannerMain {

    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            System.out.println("Usage: FolderScannerMain dir extension");
        }

        String folder = args[0];
        String extension = args[1];

        FolderScanner folderScanner = new FolderScanner(folder, extension);
        String[] files = folderScanner.scan1();

        System.out.println("Found " + files.length + " " + extension.toUpperCase() + " Files in " + folder);
        System.out.println(Arrays.toString(files));
    }
}
