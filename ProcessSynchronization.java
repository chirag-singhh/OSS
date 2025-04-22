import java.util.concurrent.Semaphore;

public class ProcessSynchronization {

    // Semaphore initialized with 1 to allow only one thread in critical section
    static Semaphore semaphore = new Semaphore(1);

    // Process class represents a thread
    static class Process extends Thread {
        String processName;

        Process(String name) {
            this.processName = name;
        }

        public void run() {
            try {
                System.out.println(processName + " is trying to enter critical section.");
                semaphore.acquire(); // Wait / P operation
                System.out.println(processName + " entered critical section.");

                // Simulate work inside critical section
                Thread.sleep(1000);

                System.out.println(processName + " leaving critical section.");
                semaphore.release(); // Signal / V operation
            } catch (InterruptedException e) {
                System.out.println(processName + " was interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        // Creating multiple processes
        Process p1 = new Process("Process 1");
        Process p2 = new Process("Process 2");
        Process p3 = new Process("Process 3");
        Process p4 = new Process("Process 4");


        // Start all processes
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}
