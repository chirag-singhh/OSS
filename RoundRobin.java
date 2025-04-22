import java.util.*;

class Process {
    int id, arrivalTime, burstTime, remainingTime, waitingTime = 0, turnaroundTime = 0, completionTime = 0;

    Process(int id, int at, int bt) {
        this.id = id;
        this.arrivalTime = at;
        this.burstTime = bt;
        this.remainingTime = bt;
    }
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter time quantum: ");
        int timeQuantum = sc.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time and Burst Time for P" + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes[i] = new Process(i + 1, at, bt);
        }

        Queue<Process> queue = new LinkedList<>();
        int time = 0, completed = 0;
        boolean[] visited = new boolean[n];

        while (completed < n) {
            // Add newly arrived processes to the queue
            for (int i = 0; i < n; i++) {
                if (!visited[i] && processes[i].arrivalTime <= time) {
                    queue.add(processes[i]);
                    visited[i] = true;
                }
            }

            if (queue.isEmpty()) {
                time++;
                continue;
            }

            Process current = queue.poll();
            int execTime = Math.min(timeQuantum, current.remainingTime);
            current.remainingTime -= execTime;
            time += execTime;

            // Add newly arrived processes during execution time
            for (int i = 0; i < n; i++) {
                if (!visited[i] && processes[i].arrivalTime <= time) {
                    queue.add(processes[i]);
                    visited[i] = true;
                }
            }

            if (current.remainingTime == 0) {
                current.completionTime = time;
                current.turnaroundTime = time - current.arrivalTime;
                current.waitingTime = current.turnaroundTime - current.burstTime;
                completed++;
            } else {
                queue.add(current); // requeue if not finished
            }
        }

        double totalWT = 0, totalTAT = 0;
        System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        for (Process p : processes) {
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
            System.out.println("P" + p.id + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" +
                    p.completionTime + "\t" + p.turnaroundTime + "\t" + p.waitingTime);
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / n);
        sc.close();
    }
}
