public class BankersAlgorithmJava {

    static final int P = 5; // Number of processes
    static final int R = 3; // Number of resources

    // Function to calculate the Need matrix
    static void calculateNeed(int[][] need, int[][] max, int[][] allot) {
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < R; j++) {
                need[i][j] = max[i][j] - allot[i][j];
            }
        }
    }

    // Function to check system is in safe state
    static boolean isSafe(int[] processes, int[] avail, int[][] max, int[][] allot) {
        int[][] need = new int[P][R];
        calculateNeed(need, max, allot);

        boolean[] finish = new boolean[P];
        int[] safeSeq = new int[P];
        int[] work = new int[R];
        System.arraycopy(avail, 0, work, 0, R);

        int count = 0;

        while (count < P) {
            boolean found = false;
            for (int p = 0; p < P; p++) {
                if (!finish[p]) {
                    int j;
                    for (j = 0; j < R; j++) {
                        if (need[p][j] > work[j])
                            break;
                    }

                    // If all needs of process p can be satisfied
                    if (j == R) {
                        for (int k = 0; k < R; k++)
                            work[k] += allot[p][k];

                        safeSeq[count++] = p;
                        finish[p] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("System is not in a safe state.");
                return false;
            }
        }

        System.out.println("System is in a safe state.");
        System.out.print("Safe sequence is: ");
        for (int i = 0; i < P; i++) {
            System.out.print("P" + safeSeq[i] + " ");
        }
        System.out.println();
        return true;
    }

    // Main method
    public static void main(String[] args) {
        int[] processes = {0, 1, 2, 3, 4};

        int[] avail = {3, 3, 2};

        int[][] max = {
            {7, 5, 3},
            {3, 2, 2},
            {9, 0, 2},
            {2, 2, 2},
            {4, 3, 3}
        };

        int[][] allot = {
            {0, 1, 0},
            {2, 0, 0},
            {3, 0, 2},
            {2, 1, 1},
            {0, 0, 2}
        };

        isSafe(processes, avail, max, allot);
    }
}
