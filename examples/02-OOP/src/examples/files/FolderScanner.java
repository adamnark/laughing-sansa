package examples.files;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author blecherl
 */
public class FolderScanner implements FilenameFilter{
    private String folder;
    private String extension;

    public FolderScanner(String folder, String extension) {
        this.folder = folder;
        this.extension = "." + extension;

        if (folder == null || extension == null) {
            throw new NullPointerException("must have a folder and an extension");
        }
    }

    //with anonymous class
    public String[] scan1() {
        File folderToScan = new File(folder);
        if (!folderToScan.exists() || !folderToScan.isDirectory()) {
            return new String[0];
        }
        String[] files = folderToScan.list(new FilenameFilter() {
                                @Override
                                public boolean accept(File dir, String name) {
                                    return name.endsWith(extension);
                                }
                            });
        return files;
    }

    //with anonymous class that calls a method defined in its outer class (FolderScanner)
    public String[] scan2() {
        File folderToScan = new File(folder);
        if (!folderToScan.exists() || !folderToScan.isDirectory()) {
            return new String[0];
        }
        String[] files = folderToScan.list(new FilenameFilter() {
                                @Override
                                public boolean accept(File dir, String name) {
                                    //acceptFileName is a method defined in class
                                    //FolderScanner (see a few rows down...)
                                    return acceptFileName(name);
                                }
                            });
        return files;
    }

    private boolean acceptFileName (String filename) {
        return filename != null && filename.endsWith("." + extension);
    }

    //with inner class that calls a method defined in its outer class (FolderScanner)
    public String[] scan3() {
        File folderToScan = new File(folder);
        if (!folderToScan.exists() || !folderToScan.isDirectory()) {
            return new String[0];
        }
        String[] files = folderToScan.list(new FolderScannerFilenameFilter());
        return files;
    }
    
    class FolderScannerFilenameFilter implements FilenameFilter{
        @Override
        public boolean accept(File dir, String name) {
            return acceptFileName(name);
        }
    }
    
    public String[] scan4() {
        File folderToScan = new File(folder);
        if (!folderToScan.exists() || !folderToScan.isDirectory()) {
            return new String[0];
        }
        String[] files = folderToScan.list(new StaticFolderScannerFilenameFilter(this));
        return files;
    }
    
   static  class StaticFolderScannerFilenameFilter implements FilenameFilter{
       FolderScanner parent;

        public StaticFolderScannerFilenameFilter(FolderScanner parent) {
            this.parent = parent;
        }
       
       @Override
        public boolean accept(File dir, String name) {
            return this.parent.acceptFileName(name);
        }
    }
   
   
   
   public String[] scan5() {
        File folderToScan = new File(folder);
        if (!folderToScan.exists() || !folderToScan.isDirectory()) {
            return new String[0];
        }
        String[] files = folderToScan.list(this);
        return files;
    }


//    static class StaticFolderScannerFilenameFilter implements FilenameFilter{
//        @Override
//        public boolean accept(File dir, String name) {
//            //since this class is a static class, it cannot access its outer class
//            //data members or methods
//            return acceptFileName(name);
//        }
//    }

    @Override
    public boolean accept(File dir, String name) {
        return acceptFileName(name);
    }
}