package examples.threads.deadlock;

/**
 * User: Liron Blecher
 * Date: 3/10/11
 */
class Friend {
    private String name;

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void bow(Friend bower) {
        System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
        bower.bowBack(this);
    }

    public synchronized void bowBack(Friend bower) {
        System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
    }
}

public class DeadlockExample {

    public static void main(String[] args) {
        final Friend tyrion = new Friend("Tyrion");
        final Friend baelish = new Friend("Baelish");

        new Thread(new Runnable() {
            public void run() {
                tyrion.bow(baelish);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                baelish.bow(tyrion);
            }
        }).start();
    }
}

/*
    Tyrion calls bow (which makes this method lock until the method is finished)  Tyrion - locked
    Baelish calls bow (which makes this method lock until the method is finished)    Baelish - locked
    inside Tyrion.bow -> tries to call Baelish.bowBack() - but Baelish is still inside its bow() method
    inside Baelish.bow -> tries to call Tyrion.bowBack() - but Tyrion is still inside its bow() method
 */
