package examples.threads.threadexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: Liron Blecher
 * Date: 3/10/11
 */
public class ExecutorExample {
    public static void main(String[] args) {
        // create and name each runnable
        List<PrintTask> tasksList = new ArrayList<PrintTask>();
        for (int i=0 ; i < 6 ; i++) {
            tasksList.add(new PrintTask("task" + (i+1)));
        }

        System.out.println("Starting threads");

        // create ExecutorService to manage threads
        ExecutorService threadExecutor = Executors.newFixedThreadPool(3);

        // start threads and place in runnable state
        for (PrintTask printTask : tasksList) {
            threadExecutor.execute(printTask);
        }

        threadExecutor.shutdown(); // shutdown worker threads

        System.out.println("Threads started, main ends\n");
    } // end main
}
