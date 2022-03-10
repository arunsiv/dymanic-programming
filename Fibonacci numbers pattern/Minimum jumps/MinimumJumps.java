public class MinimumJumps {
    // O(2^n) time | O(n) space
    public int countMinJumps(int[] jumps) {
        return this.countMinJumpsRecursive(jumps, 0);
    }

    private int countMinJumpsRecursive(int[] jumps, int currentIndex) {
        // if we have reached the last index, we don't need any more jumps
        if (currentIndex == jumps.length - 1)
            return 0;

        if (jumps[currentIndex] == 0) {
            return Integer.MAX_VALUE;
        }

        int totalJumps = Integer.MAX_VALUE;
        int start = currentIndex + 1;
        int end = currentIndex + jumps[currentIndex];
        System.out.println(currentIndex + " :: " + start + " :: " + end);
        while (start < jumps.length && start <= end) {
            // jump one step and recurse for the remaining array
            int minJumps = countMinJumpsRecursive(jumps, start++);
            if (minJumps != Integer.MAX_VALUE)
                totalJumps = Math.min(totalJumps, minJumps + 1);
        }
        return totalJumps;
    }

    // O(n) time | O(n) space
    public int countMinJumpsTopDown(int[] jumps) {
        int[] dp = new int[jumps.length];
        return this.countMinJumpsTopDownRecursive(dp, jumps, 0);
    }

    private int countMinJumpsTopDownRecursive(int[] dp, int[] jumps, int currentIndex) {
        // if we have reached the last index, we don't need any more jumps
        if (currentIndex == jumps.length - 1)
            return 0;

        if (jumps[currentIndex] == 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[currentIndex] != 0) {
            return dp[currentIndex];
        }

        int totalJumps = Integer.MAX_VALUE;
        int start = currentIndex + 1;
        int end = currentIndex + jumps[currentIndex];
        // System.out.println(currentIndex + " :: " + start + " :: " + end);
        while (start < jumps.length && start <= end) {
            // jump one step and recurse for the remaining array
            int minJumps = countMinJumpsTopDownRecursive(dp, jumps, start++);
            if (minJumps != Integer.MAX_VALUE)
                totalJumps = Math.min(totalJumps, minJumps + 1);
        }

        dp[currentIndex] = totalJumps;
        return dp[currentIndex];
    }

    // O(n^2) time | O(n) space
    public int countMinJumpsBottomUp(int[] jumps) {
        int[] dp = new int[jumps.length];

        // initialize with infinity, except the first index which should be zero as we
        // start from there
        for (int i = 1; i < jumps.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int start = 0; start < jumps.length - 1; start ++) {
            for (int end = start + 1; end <= start + jumps[start]  && end < jumps.length; end ++) {
                dp[end] = Math.min(dp[end], dp[start] + 1);
            }
        }

        for (int i : dp) {
            System.out.print(i + "\t");
        }

        System.out.println("");

        return dp[jumps.length - 1];
    }

    public static void main(String[] args) {
        MinimumJumps aj = new MinimumJumps();
        int[] jumps = { 2, 1, 1, 1, 4 };
        // System.out.println(aj.countMinJumps(jumps));
        // jumps = new int[] { 1, 1, 3, 6, 9, 3, 0, 1, 3 };
        // System.out.println(aj.countMinJumps(jumps));

        // jumps = new int[] { 2, 1, 1, 1, 4 };
        // System.out.println(aj.countMinJumpsTopDown(jumps));
        // jumps = new int[] { 1, 1, 3, 6, 9, 3, 0, 1, 3 };
        // System.out.println(aj.countMinJumpsTopDown(jumps));

        // jumps = new int[] { 1, 1, 3, 0, 0, 0, 0, 1, 3, 7, 8, 9 };
        // System.out.println(aj.countMinJumpsTopDown(jumps));

        jumps = new int[] { 2, 1, 1, 1, 4 };
        //System.out.println(aj.countMinJumpsBottomUp(jumps));
        jumps = new int[] { 1, 1, 3, 6, 9, 3, 0, 1, 3 };
        //System.out.println(aj.countMinJumpsBottomUp(jumps));

        jumps = new int[] { 1, 1, 3, 0, 0, 0, 0, 1, 3, 7, 8, 9 };
        System.out.println(aj.countMinJumpsBottomUp(jumps));
    }
}