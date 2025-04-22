public class DiningPhilosophers {
    static class Philosopher extends Thread {
        private int id;
        private Object leftFork, rightFork;

        public Philosopher(int id, Object leftFork, Object rightFork) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        public void run() {
            try {
                while (true) {
                    System.out.println("Philosopher " + id + " is thinking");
                    synchronized (leftFork) {
                        System.out.println("Philosopher " + id + " picked left fork");
                        synchronized (rightFork) {
                            System.out.println("Philosopher " + id + " picked right fork - eating");
                            Thread.sleep(1000);
                            System.out.println("Philosopher " + id + " finished eating");
                        }
                        System.out.println("Philosopher " + id + " put down right fork");
                    }
                    System.out.println("Philosopher " + id + " put down left fork");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Object[] forks = new Object[5];
        for (int i = 0; i < 5; i++) forks[i] = new Object();

        for (int i = 0; i < 5; i++) {
            Object left = forks[i];
            Object right = forks[(i + 1) % 5];
            new Philosopher(i, left, right).start();
        }
    }
}
