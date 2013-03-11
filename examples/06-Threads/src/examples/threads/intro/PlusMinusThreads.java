package examples.threads.intro;

import java.util.Scanner;

/**
 * Basic demo of running several threads simultaneously without synchronization
 * The pluses and minuses will be written to the system console without any
 * particular order. Each run of this program will produce different results
 *
 * @author blecherl
 */
public class PlusMinusThreads {

    public static void main(String[] args) {
        new PlusMinusThreads().createAndRunExample();
    }

    public void createAndRunExample() {
        //create a thread by providing an implementation of Runnable
        //to the thread's ctor
        Runnable r = new MinusPrinter();
        Thread t1 = new Thread(r);
        //create a thread by extending the Thread class
        Thread t2 = new PlusPrinter();
        //to start a thread call the start() method - no the run() method
        t1.start();
        t2.start();
        //started both threads (they might have started running or not
        System.out.print(Thread.currentThread().getName() + ": Still in main");
    }

    public class MinusPrinter implements Runnable {
        public void run() {
            for (int i = 0; i < 1000; ++i) {
                System.out.print("-");
//                Thread.yield();
            }
        }
    }

    public class PlusPrinter extends Thread {
        public void run() {
            for (int i = 0; i < 1000; ++i) {
                System.out.print("+");
            }
        }
    }
}