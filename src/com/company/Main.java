package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static void RRfindWaitingTime(int processes[], int n,
                                int bt[], int wt[], int quantum)
    {
        int rem_bt[] = new int[n];
        for (int i = 0 ; i < n ; i++)
            rem_bt[i] =  bt[i];

        int t = 0;


        while(true)
        {
            boolean done = true;

            for (int i = 0 ; i < n; i++)
            {

                if (rem_bt[i] > 0)
                {
                    done = false;

                    if (rem_bt[i] > quantum)
                    {

                        t += quantum;

                        rem_bt[i] -= quantum;
                    }

                    else
                    {

                        t = t + rem_bt[i];

                        wt[i] = t - bt[i];

                        rem_bt[i] = 0;
                    }
                }
            }

            if (done == true)
                break;
        }
    }

    static void RRfindTurnAroundTime(int processes[], int n,
                                   int bt[], int wt[], int tat[])
    {
        for (int i = 0; i < n ; i++)
            tat[i] = bt[i] + wt[i];
    }

    static void RRfindavgTime(int processes[], int n, int bt[],
                            int quantum) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        RRfindWaitingTime(processes, n, bt, wt, quantum);

        RRfindTurnAroundTime(processes, n, bt, wt, tat);

       System.out.println("Processes " + " Burst time " +
                " Waiting time " + " Turn around time");

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + (i + 1) + "\t\t" + bt[i] + "\t " +
                    wt[i] + "\t\t " + tat[i]);
        }

        double RRavg = (total_tat + total_wt)/2 ;


        System.out.println("Round Robin Average waiting time = " +
                (float) total_wt / (float) n);
        System.out.println("Round Robin Average turn around time = " +
                (float) total_tat / (float) n);



    }

    static void FCFSfindWatingTime(int processes[], int n , int bt[] , int wt[]) {
        wt[0] = 0;

        for (int i = 1; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
        }

    }
    static void FCFSfindTurnAroundTime(int processes[], int n ,int bt[] , int wt[] , int tat[]){

        for (int  i = 0; i < n ; i++)
            tat[i] = bt[i] + wt[i];
    }

   static void FCFSfindavgTime(int processes[] , int n , int bt[]){

        int wt[] = new int[n] ;
        int tat[] = new int[n] ;
        int total_wt = 0;
        int total_tat = 0;

        FCFSfindWatingTime(processes, n ,bt ,wt);
        FCFSfindTurnAroundTime(processes, n, bt, wt, tat);

       System.out.println("Processes " + " Burst time " +
               " Waiting time " + " Turn around time");

       for (int i = 0; i < n; i++) {
           total_wt = total_wt + wt[i];
           total_tat = total_tat + tat[i];
           /*System.out.println(" " + (i + 1) + "\t\t" + bt[i] + "\t " +
                   wt[i] + "\t\t " + tat[i]);*/
       }

       double FCFSavg = (total_tat + total_wt)/2 ;

       System.out.println("FCFS Average waiting time = " +
               (float) total_wt / (float) n);
       System.out.println("FCFS Average turn around time = " +
               (float) total_tat / (float) n);
   }



    public static void main(String[] args) {


        int processes[] = { 1, 2, 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 , 13};
        int n = processes.length;
        int burst_time[] = {10, 5, 8 ,3, 1 , 34, 1, 3, 4, 11 , 22, 3 , 13};
        int quantum = 2;
        RRfindavgTime(processes, n, burst_time, quantum);
        System.out.println("========================================");
        FCFSfindavgTime(processes, n,  burst_time);

    }
}
