class BestFit {
    static int g = 0, k = 0;
    static FreeNode freeHead = null, prevFree = null;
    static AllocNode allocHead = null, prevAlloc = null;

    // Structure for free list
    static class FreeNode {
        int tag = -1;
        int size = 0;
        FreeNode next = null;
    }

    // Structure for allocated list
    static class AllocNode {
        int blockId = -1;
        int tag = -1;
        int size = 0;
        AllocNode next = null;
    }

    // Function to create free list with given sizes
    static void createFree(int size) {
        FreeNode p = new FreeNode();
        p.size = size;
        p.tag = g++;
        p.next = null;
        if (freeHead == null) {
            freeHead = p;
        } else {
            prevFree.next = p;
        }
        prevFree = p;
    }

    // Function to print free list
    static void printFree() {
        FreeNode p = freeHead;
        System.out.println("Tag\tSize");
        while (p != null) {
            System.out.println(p.tag + "\t" + p.size);
            p = p.next;
        }
    }

    // Function to print allocated list
    static void printAlloc() {
        AllocNode p = allocHead;
        System.out.println("Tag\tBlock ID\tSize");
        while (p != null) {
            System.out.println(p.tag + "\t" + p.blockId + "\t" + p.size);
            p = p.next;
        }
    }

    // Function to allocate memory to blocks using Best Fit algorithm
    static void createAlloc(int size) {
        AllocNode q = new AllocNode();
        q.size = size;
        q.tag = k++;
        q.next = null;
        FreeNode p = freeHead;
        FreeNode bestFit = null;
        int minDiff = Integer.MAX_VALUE;

        // Iterate to find the best fit block
        while (p != null) {
            if (p.size >= q.size && (p.size - q.size) < minDiff) {
                bestFit = p;
                minDiff = p.size - q.size;
            }
            p = p.next;
        }

        // Node found to allocate
        if (bestFit != null) {
            q.blockId = bestFit.tag;
            bestFit.size -= q.size;
            if (allocHead == null) {
                allocHead = q;
            } else {
                prevAlloc = allocHead;
                while (prevAlloc.next != null) {
                    prevAlloc = prevAlloc.next;
                }
                prevAlloc.next = q;
            }
        } else {
            System.out.println("Block of size " + size + " can't be allocated");
        }
    }

    // Driver Code
    public static void main(String[] args) {
        int[] blockSize = {100, 500, 200};
        int[] processSize = {417, 112, 426, 95};

        // Create free blocks
        for (int size : blockSize) {
            createFree(size);
        }

        // Allocate memory for processes
        for (int size : processSize) {
            createAlloc(size);
        }

        // Print allocated list
        printAlloc();
    }
}
