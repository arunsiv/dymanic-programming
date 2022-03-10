public class NumberFactor {
    // O(3^n) time (3 recursive calls) | O(n) space
    public int CountWaysBruteForce(int n) {
        // base case, we don't need to subtract anything, there is only one way
        if (n == 0) {
            return 1;
        }

        // we can subtract 1 to be left with zero, and that is the only way
        if (n == 1) {
            return 1;
        }

        // we can subtract 1 twice to get zero and that is the only way
        if (n == 2) {
            return 1;
        }

        // '3' can be expressed as {1,1,1}, {3}
        if (n == 3) {
            return 2;
        }

        // if we subtract 1, we are left with 'n-1';
        int subtract1 = CountWaysBruteForce(n - 1);

        // if we subtract 1, we are left with 'n-3';
        int subtract3 = CountWaysBruteForce(n - 3);

        // if we subtract 1, we are left with 'n-4';
        int subtract4 = CountWaysBruteForce(n - 4);

        return subtract1 + subtract3 + subtract4;
    }

    // O(n) time | O(n) space
    public int CountWaysTopDown(int n) {
        int[] dp = new int[n + 1];
        return CountWaysTopDownRecursive(dp, n);
    }

    public int CountWaysTopDownRecursive(int[] dp, int n) {
        // base case, we don't need to subtract anything, there is only one way
        if (n == 0) {
            return 1;
        }

        // we can subtract 1 to be left with zero, and that is the only way
        if (n == 1) {
            return 1;
        }

        // we can subtract 1 twice to get zero and that is the only way
        if (n == 2) {
            return 1;
        }

        // '3' can be expressed as {1,1,1}, {3}
        if (n == 3) {
            return 2;
        }

        if (dp[n] == 0) {
            // if we subtract 1, we are left with 'n-1';
            int subtract1 = CountWaysBruteForce(n - 1);

            // if we subtract 1, we are left with 'n-3';
            int subtract3 = CountWaysBruteForce(n - 3);

            // if we subtract 1, we are left with 'n-4';
            int subtract4 = CountWaysBruteForce(n - 4);

            dp[n] = subtract1 + subtract3 + subtract4;
        }

        return dp[n];
    }

    // O(n) time | O(n) space
    public int CountWaysBottomUp(int n) {
        int[] dp = new int[n + 1];

        // base case, we don't need to subtract anything, there is only one way
        if (n == 0) {
            return 1;
        }

        // we can subtract 1 to be left with zero, and that is the only way
        if (n == 1) {
            return 1;
        }

        // we can subtract 1 twice to get zero and that is the only way
        if (n == 2) {
            return 1;
        }

        // '3' can be expressed as {1,1,1}, {3}
        if (n == 3) {
            return 2;
        }

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3] + dp[i - 4];
        }

        return dp[n];
    }

    // O(n) time | O(1) space
    // public int CountWaysBottomUpSpaceOptimized(int n) {
    //     // base case, we don't need to subtract anything, there is only one way
    //     if (n == 0) {
    //         return 1;
    //     }

    //     // we can subtract 1 to be left with zero, and that is the only way
    //     if (n == 1) {
    //         return 1;
    //     }

    //     // we can subtract 1 twice to get zero and that is the only way
    //     if (n == 2) {
    //         return 1;
    //     }

    //     // '3' can be expressed as {1,1,1}, {3}
    //     if (n == 3) {
    //         return 2;
    //     }

    //     int n1 = 1, n3 = 1, n4 = 2, temp;

    //     for (int i = 4; i <= n; i++) {
    //         temp = n4 + n3 + n1;
    //         n1 = n3;
    //         n3 = n4;
    //         n4 = temp;
    //     }

    //     return n4;
    // }

    public static void main(String[] args) {
        NumberFactor en = new NumberFactor();
        System.out.println(en.CountWaysBruteForce(4));
        System.out.println(en.CountWaysBruteForce(5));
        System.out.println(en.CountWaysBruteForce(6));

        System.out.println(en.CountWaysTopDown(4));
        System.out.println(en.CountWaysTopDown(5));
        System.out.println(en.CountWaysTopDown(6));

        System.out.println(en.CountWaysBottomUp(4));
        System.out.println(en.CountWaysBottomUp(5));
        System.out.println(en.CountWaysBottomUp(6));

        // System.out.println(en.CountWaysBottomUpSpaceOptimized(4));
        // System.out.println(en.CountWaysBottomUpSpaceOptimized(5));
        // System.out.println(en.CountWaysBottomUpSpaceOptimized(6));
    }
}