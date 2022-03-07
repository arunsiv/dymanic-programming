class Staircase {
    // O(3^n) time (3 recursive calls) | O(n) space
    public int CountWaysBruteForce(int n) {
        // base case, we don't need to take any step, so there is only one way
        if (n == 0) {
            return 1;
        }

        // we can take one step to reach the end, and that is the only way
        if (n == 1) {
            return 1;
        }

        // we can take one step twice or jump two steps to reach at the top
        if (n == 2) {
            return 2;
        }

        // if we take 1 step, we are left with 'n-1' steps;
        int take1step = CountWaysBruteForce(n - 1);

        // if we take 2 steps, we are left with 'n-2' steps;
        int take2step = CountWaysBruteForce(n - 2);

        // if we take 3 step, we are left with 'n-3' steps;
        int take3step = CountWaysBruteForce(n - 3);

        return take1step + take2step + take3step;
    }

    // O(n) time | O(n) space
    public int CountWaysTopDown(int n) {
        int[] dp = new int[n + 1];
        return CountWaysTopDownRecursive(dp, n);
    }

    public int CountWaysTopDownRecursive(int[] dp, int n) {
        // base case, we don't need to take any step, so there is only one way
        if (n == 0) {
            return 1;
        }

        // we can take one step to reach the end, and that is the only way
        if (n == 1) {
            return 1;
        }

        // we can take one step twice or jump two steps to reach at the top
        if (n == 2) {
            return 2;
        }

        if (dp[n] == 0) {
            // if we take 1 step, we are left with 'n-1' steps;
            int take1step = CountWaysTopDownRecursive(dp, n - 1);

            // if we take 2 steps, we are left with 'n-2' steps;
            int take2step = CountWaysTopDownRecursive(dp, n - 2);

            // if we take 3 step, we are left with 'n-3' steps;
            int take3step = CountWaysTopDownRecursive(dp, n - 3);

            dp[n] = take1step + take2step + take3step;
        }

        return dp[n];
    }

    // O(n) time | O(n) space
    public int CountWaysBottomUp(int n) {
        int[] dp = new int[n + 1];

        // base case, we don't need to take any step, so there is only one way
        if (n == 0) {
            return 1;
        }

        // we can take one step to reach the end, and that is the only way
        if (n == 1) {
            return 1;
        }

        // we can take one step twice or jump two steps to reach at the top
        if (n == 2) {
            return 2;
        }

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    // O(n) time | O(1) space
    public int CountWaysBottomUpSpaceOptimized(int n) {
        // base case, we don't need to take any step, so there is only one way
        if (n == 0) {
            return 1;
        }

        // we can take one step to reach the end, and that is the only way
        if (n == 1) {
            return 1;
        }

        // we can take one step twice or jump two steps to reach at the top
        if (n == 2) {
            return 2;
        }

        int step1 = 1, step2 = 1, step3 = 2, temp;

        for (int i = 3; i <= n; i++) {
            temp = step1 + step2 + step3;
            step1 = step2;
            step2 = step3;
            step3 = temp;
        }

        return step3;
    }

    public static void main(String[] args) {
        Staircase sc = new Staircase();
        // System.out.println(sc.CountWaysBruteForce(3));
        // System.out.println(sc.CountWaysBruteForce(4));
        // System.out.println(sc.CountWaysBruteForce(5));

        // System.out.println(sc.CountWaysTopDown(3));
        // System.out.println(sc.CountWaysTopDown(4));
        // System.out.println(sc.CountWaysTopDown(5));

        // System.out.println(sc.CountWaysBottomUp(3));
        // System.out.println(sc.CountWaysBottomUp(4));
        // System.out.println(sc.CountWaysBottomUp(5));

        System.out.println(sc.CountWaysBottomUpSpaceOptimized(3));
        System.out.println(sc.CountWaysBottomUpSpaceOptimized(4));
        System.out.println(sc.CountWaysBottomUpSpaceOptimized(5));
    }
}