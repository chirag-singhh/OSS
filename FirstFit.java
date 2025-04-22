class FirstFit {
    // Function to implement First Fit algorithm
    static int[] firstFit(int[] memoryBlocks, int[] processSizes) {
        int[] allocation = new int[processSizes.length];
        for (int i = 0; i < processSizes.length; i++) {
            allocation[i] = -1; // -1 means no block is allocated to the process
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processSizes[i]) {
                    allocation[i] = j; // Process i is allocated to block j
                    memoryBlocks[j] -= processSizes[i]; // Reduce available memory in block j
                    break;
                }
            }
        }
        return allocation;
    }

    // Driver Code
    public static void main(String[] args) {
        int[] memoryBlocks = {100, 250, 200, 300, 150};
        int[] processSizes = {150, 350, 200, 100};

        int[] allocation = firstFit(memoryBlocks, processSizes);

        // Print the allocation result
        System.out.println("Memory Allocation:");
        for (int i = 0; i < allocation.length; i++) {
            if (allocation[i] != -1) {
                System.out.println("Process " + i + " is allocated to Block " + allocation[i]);
            } else {
                System.out.println("Process " + i + " could not be allocated");
            }
        }
    }
}
