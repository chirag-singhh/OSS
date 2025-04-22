import java.util.Scanner;

public class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] A = new int[100][4]; // [Process ID, Burst Time, Waiting Time, Turnaround Time]
        int n, total = 0;
        float avg_wt, avg_tat;

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        System.out.println("Enter Burst Time:");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + ": ");
            A[i][1] = sc.nextInt();  // Burst Time
            A[i][0] = i + 1;         // Process ID
        }

        // Sorting processes by Burst Time (Selection Sort)
        for (int i = 0; i < n; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (A[j][1] < A[index][1]) {
                    index = j;
                }
            }
            // Swap burst times
            int temp = A[i][1];
            A[i][1] = A[index][1];
            A[index][1] = temp;

            // Swap process IDs
            temp = A[i][0];
            A[i][0] = A[index][0];
            A[index][0] = temp;
        }

        A[0][2] = 0; // Waiting time for first process is 0

        // Calculating waiting times
        for (int i = 1; i < n; i++) {
            A[i][2] = 0;
            for (int j = 0; j < i; j++) {
                A[i][2] += A[j][1];
            }
            total += A[i][2];
        }

        avg_wt = (float) total / n;
        total = 0;

        System.out.println("P\tBT\tWT\tTAT");

        // Calculating Turnaround Times and printing results
        for (int i = 0; i < n; i++) {
            A[i][3] = A[i][1] + A[i][2]; // TAT = BT + WT
            total += A[i][3];
            System.out.printf("P%d\t%d\t%d\t%d\n", A[i][0], A[i][1], A[i][2], A[i][3]);
        }

        avg_tat = (float) total / n;

        System.out.printf("Average Waiting Time = %.2f\n", avg_wt);
        System.out.printf("Average Turnaround Time = %.2f\n", avg_tat);
        sc.close();
    }
}
