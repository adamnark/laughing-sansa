package examples.swing.events;

/**
 * User: Liron Blecher
 * Date: 3/11/11
 */
public class EventsMain {
    public static void main(String[] args) {
        SomeComponent generator = new SomeComponent();
        SystemOutPrinter systemOutPrinter = new SystemOutPrinter();
        FileWriterListener fileWriterListener = new FileWriterListener();
////
        generator.addListener(systemOutPrinter);
        generator.addListener(new SystemOutPrinter());
        generator.addListener(fileWriterListener);

        generator.startCounting();
    }
}
