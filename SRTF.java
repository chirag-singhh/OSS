// //Pre-emptive
import java.util.*;

class Process {
    int id, arrival, burst, remaining, waiting, turnaround, completion;

    Process(int id, int arrival, int burst) {
        this.id = id;
        this.arrival = arrival;
        this.burst = burst;
        this.remaining = burst;
    }
}

public class SRTF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for P" + (i + 1) + ": ");
            int arrival = sc.nextInt();
            System.out.print("Enter burst time for P" + (i + 1) + ": ");
            int burst = sc.nextInt();
            processes[i] = new Process(i + 1, arrival, burst);
        }

        int time = 0, completed = 0;
        double totalWT = 0, totalTAT = 0;

        while (completed < n) {
            int idx = -1;
            int minTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (processes[i].arrival <= time && processes[i].remaining > 0) {
                    if (processes[i].remaining < minTime) {
                        minTime = processes[i].remaining;
                        idx = i;
                    }
                }
            }

            if (idx != -1) {
                processes[idx].remaining--;
                time++;

                if (processes[idx].remaining == 0) {
                    processes[idx].completion = time;
                    processes[idx].turnaround = processes[idx].completion - processes[idx].arrival;
                    processes[idx].waiting = processes[idx].turnaround - processes[idx].burst;

                    totalWT += processes[idx].waiting;
                    totalTAT += processes[idx].turnaround;
                    completed++;
                }
            } else {
                time++; // CPU is idle
            }
        }

        // Output
        System.out.println("\nProcess\tAT\tBT\tCT\tWT\tTAT");
        for (Process p : processes) {
            System.out.println("P" + p.id + "\t" + p.arrival + "\t" + p.burst + "\t" + p.completion + "\t" + p.waiting + "\t" + p.turnaround);
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / n);
        sc.close();
    }
}


// // Non Pre-emptive 
// badme likta


// import java.util.Scanner;

// /**
//  * Created by hadiana_sliwa on 12/1/18.
//  */

// public class SJF {
//     public static void main(String args[])
//     {
//         Scanner sc = new Scanner(System.in);
//         System.out.println ("enter no of process:");
//         int n = sc.nextInt();
//         int pid[] = new int[n];
//         int at[] = new int[n];
//         int bt[] = new int[n];
//         int ct[] = new int[n];
//         int ta[] = new int[n];
//         int wt[] = new int[n];
//         int f[] = new int[n];

//         int st=0, tot=0;
//         float avgwt=0, avgta=0;

//         for(int i=0;i<n;i++)
//         {
//             System.out.println ("enter process " + (i+1) + " arrival time:");
//             at[i] = sc.nextInt();
//             System.out.println ("enter process " + (i+1) + " brust time:");
//             bt[i] = sc.nextInt();
//             pid[i] = i+1;
//             f[i] = 0;
//         }


//         while(true)
//         {
//             int c=n, min = 999999;

//             if (tot == n)
//                 break;

//             for (int i=0; i<n; i++)
//             {

//                 if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
//                 {
//                     min=bt[i];
//                     c=i;
//                 }
//             }
//             if (c==n)
//                 st++;
//             else
//             {
//                 ct[c]=st+bt[c];
//                 st+=bt[c];
//                 ta[c]=ct[c]-at[c];
//                 wt[c]=ta[c]-bt[c];
//                 f[c]=1;
//                 pid[tot] = c + 1;
//                 tot++;
//             }
//         }

//         System.out.println("\npid  arrival brust  complete turn waiting");
//         for(int i=0;i<n;i++)
//         {
//             avgwt+= wt[i];
//             avgta+= ta[i];
//             System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+ta[i]+"\t\t"+wt[i]);
//         }
//         System.out.println ("\naverage tat is "+ (float)(avgta/n));
//         System.out.println ("average wt is "+ (float)(avgwt/n));
//         sc.close();
//         for(int i=0;i<n;i++)
//         {
//             System.out.print(pid[i] + " ");
//         }
//     }
// }