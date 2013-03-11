package examples.threads.poetthread;

import java.util.concurrent.TimeUnit;

/**
 * User: Liron Blecher
 * Date: 3/10/11
 * Example of how to monitor and stop one thread from another thread
 */
public class PoetThreadExample {

    //Display a message, preceded by the name of the current thread
    static void printMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    //this class try to read the poem, one line at a time
    //waiting 4 seconds between each line
    private static class PoetRunnable implements Runnable {
        public void run() {
            String importantInfo[] = {
                "Once upon a time",
                "you dressed so fine",
                "You threw the bums a dime",
                "in your prime, didn't you ?"
            };
            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    //Pause for 4 seconds - causes the current running thread to be blocked
                    //for 4 seconds
                    Thread.sleep(TimeUnit.SECONDS.toMillis(4));
                    //Print a message
                    printMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                //somebody (outside of this thread) called interrupt on this thread
                printMessage("I'm not finished!");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        //Delay, in milliseconds before we interrupt MessageLoop
        //waiting fot 10 seconds
        long patience = TimeUnit.SECONDS.toMillis(10);

        printMessage("Starting Poet Thread");
        Thread poetThread = new Thread(new PoetRunnable(), "Poet Thread");
        long startTime = System.currentTimeMillis();
        poetThread.start();

        printMessage("Waiting for Poet Thread thread to finish");
        //loop until poetThread thread exits
        while (poetThread.isAlive()) {
            printMessage("Still waiting...");
//            //Wait maximum of 1 second for MessageLoop thread to finish
//            //this method waits for the thread to die - and limit the time to wait
//            //for 1 second
//            //if 1 second passed and the thread is still alive the method will exit
            poetThread.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && poetThread.isAlive()) {
                printMessage("Tired of waiting!");
                //send a command that will kill the poetThread
                poetThread.interrupt();
                //again - wait until the thread is dead
                //Shouldn't be long now - wait indefinitely for the poetThread to end
                poetThread.join();
            }
        }
        printMessage("Finally!");
    }
}
