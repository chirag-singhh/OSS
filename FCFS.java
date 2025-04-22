
import java.util.*;


public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();


        int[] bt = new int[n]; // burst time
        int[] wt = new int[n]; // waiting time
        int[] tat = new int[n]; // turnaround time


        System.out.println("Enter burst time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
        }


        // Waiting time calculation
        wt[0] = 0;
        for (int i = 1; i < n; i++) {
            wt[i] = wt[i - 1] + bt[i - 1];
        }


        // Turnaround time calculation
        for (int i = 0; i < n; i++) {
            tat[i] = wt[i] + bt[i];
        }


        // Calculating total WT and TAT for average
        int totalWT = 0, totalTAT = 0;
        for (int i = 0; i < n; i++) {
            totalWT += wt[i];
            totalTAT += tat[i];
        }


        double avgWT = (double) totalWT / n;
        double avgTAT = (double) totalTAT / n;


        // Displaying results
        System.out.println("\nProcess\tBT\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }


        System.out.printf("\nAverage Waiting Time: %.2f\n", avgWT);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTAT);
        sc.close();
    }
}



