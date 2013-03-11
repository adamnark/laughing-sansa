package examples.threads.producerconsumer.good;

/**
 * User: Liron Blecher
 * Date: 3/11/11
 */
// A correct implementation of a producer and consumer.

/*
Inside get(), wait() is called.
This causes its execution to suspend until the Producer notifies you that some data is ready.
When this happens, execution inside get() resumes.
After the data has been obtained, get() calls notify().
This tells Producer that it is okay to put more data in the queue.

Inside put(), wait() suspends execution until the Consumer has removed the item from the queue.
When execution resumes, the next item of data is put in the queue, and notify() is called.
This tells the Consumer that it should now remove it.
*/
class Data {
    int data;
    boolean valueSet = false;

    synchronized int get() {
        if (!valueSet) { //to prevent deadlock
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        System.out.println(Thread.currentThread().getName() + ": got: " + data);
        valueSet = false;
        this.notifyAll();
        return data;
    }

    synchronized void put(int n) {
        if (valueSet) { //to prevent deadlock
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        this.data = n;
        valueSet = true;
        System.out.println(Thread.currentThread().getName() + ": put: " + n);
        this.notifyAll();
    }
}

class Producer implements Runnable {
    Data data;

    Producer(Data data) {
        this.data = data;
    }

    public void run() {
        int i = 0;
        while (true) {
            data.put(i++);
        }
    }
}

class Consumer implements Runnable {
    Data data;

    Consumer(Data data) {
        this.data = data;
    }

    public void run() {
        while (true) {
            data.get();
        }
    }
}

class ProducerConsumerExample {
    public static void main(String args[]) {
        Data data = new Data();
        new Thread(new Producer(data), "Producer").start();
        new Thread(new Consumer(data), "Consumer").start();
        System.out.println("Press Control-C to stop.");
    }
}
