import java.util.concurrent.*;

class ProducerConsumer {
    static final int SIZE = 5;
    static int[] buffer = new int[SIZE];
    static int in = 0, out = 0;

    // Semaphores
    static Semaphore mutex = new Semaphore(1);           // for critical section
    static Semaphore empty = new Semaphore(SIZE);        // counts empty slots
    static Semaphore full = new Semaphore(0);            // counts filled slots

    static class Producer extends Thread {
        public void run() {
            int item = 1;
            try {
                while (true) {
                    empty.acquire();          // wait if buffer is full
                    mutex.acquire();          // enter critical section

                    buffer[in] = item;
                    System.out.println("Produced: " + item);
                    in = (in + 1) % SIZE;
                    item++;

                    mutex.release();          // exit critical section
                    full.release();           // increment count of filled slots

                    Thread.sleep(500);        // simulate delay
                }
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted.");
            }
        }
    }

    static class Consumer extends Thread {
        public void run() {
            try {
                while (true) {
                    full.acquire();           // wait if buffer is empty
                    mutex.acquire();          // enter critical section

                    int item = buffer[out];
                    System.out.println("Consumed: " + item);
                    out = (out + 1) % SIZE;

                    mutex.release();          // exit critical section
                    empty.release();          // increment count of empty slots

                    Thread.sleep(1000);       // simulate delay
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        Consumer c = new Consumer();

        p.start();
        c.start();
    }
}
