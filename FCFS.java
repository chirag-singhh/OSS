import java.util.*;
public class FCFS{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();
        int [] at = new int [n];
        int [] bt = new int [n];
        int [] ct = new int [n];
        int [] wt = new int [n];
        int [] tat = new int [n];
        
        System.out.println("Enter the arrival time and burst time for each process : ");
        for(int i=0; i<n; i++){
            System.out.print("p" + (i+1) + ":");
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
        }
        // ct
        int currTime = 0;
        for(int i=0; i<n; i++){
            if(currTime<at[i]){
                currTime = at[i];
            }
            ct[i] =  currTime + bt[i];
            currTime = ct[i];
        }
        // tat
        for(int i=0; i<n; i++){
            tat[i] = ct[i] - at[i];
        }
        // wt
        for(int i=0; i<n; i++){
            wt[i] = tat[i] - bt[i];
        }
        // total
        int totalwt=0;
        int  totaltat=0;
        for(int i=0; i<n;i++){
            totalwt += wt[i];
            totaltat += tat[i];
        }
        // avg
        double avgwt = totalwt/n;
        double avgtat = totaltat/n;
        
        System.out.println("P" + "\tAT" + "\tBT" + "\tCT" + "\tTAT" + "\tWT");
        for(int i=0; i<n; i++){
            System.out.println("p" +(i+1) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i] );
        }
        
        System.out.println("avarage of waiting time : " + avgwt);
        System.out.println("avarage of turn around time : " + avgtat);
    }
}



