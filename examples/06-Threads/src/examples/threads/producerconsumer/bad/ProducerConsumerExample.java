package examples.threads.producerconsumer.bad;


// An incorrect implementation of a producer and consumer.

/*
    Although the put( ) and get( ) methods on Data are synchronized
    nothing stops the producer from overrunning the consumer
    nor will anything stop the consumer from consuming the same queue value twice.
    Thus, you get the erroneous output

    The problem here is not data consistency but the behavior of the Producer/Consumer threads
 */

class Data {
    int data;

    synchronized int get() {
        System.out.println(Thread.currentThread().getName() + ": got: " + data);
        return data;
    }

    synchronized void put(int n) {
        this.data = n;
        System.out.println(Thread.currentThread().getName() + ": put: " + n);
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