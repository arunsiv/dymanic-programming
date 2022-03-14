public class MinJumpFee {
    // O(3^n) time | O(n) space
    public int findMinFeeBruteForce(int[] fees) {
        return findMinFeeBruteForceRecursive(fees, 0);
    }

    private int findMinFeeBruteForceRecursive(int[] fees, int currentIndex) {
        // base checks
        if (fees.length == 0 || currentIndex > fees.length - 1) {
            return 0;
        }

        int take1step = findMinFeeBruteForceRecursive(fees, currentIndex + 1);
        int take2step = findMinFeeBruteForceRecursive(fees, currentIndex + 2);
        int take3step = findMinFeeBruteForceRecursive(fees, currentIndex + 3);

        int min = Math.min(take3step, Math.min(take1step, take2step));

        return min + fees[currentIndex];
    }

    // O(n) time | O(n) space
    public int findMinFeeTopDown(int[] fees) {
        // base checks
        int n = fees.length;
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n];

        return findMinFeeTopDownRecursive(dp, fees, 0);
    }

    private int findMinFeeTopDownRecursive(int[] dp, int[] fees, int currentIndex) {
        // base checks
        if (fees.length == 0 || currentIndex > fees.length - 1) {
            return 0;
        }

        if (dp[currentIndex] == 0) {
            int take1step = findMinFeeTopDownRecursive(dp, fees, currentIndex + 1);
            int take2step = findMinFeeTopDownRecursive(dp, fees, currentIndex + 2);
            int take3step = findMinFeeTopDownRecursive(dp, fees, currentIndex + 3);

            int min = Math.min(take3step, Math.min(take1step, take2step));

            dp[currentIndex] = min + fees[currentIndex];
        }

        return dp[currentIndex];
    }

    // O(n) time | O(n) space
    public int findMinFeeBottomDown(int[] fees) {
        // base checks
        if (fees.length == 0) {
            return 0;
        }

        int[] dp = new int[fees.length + 1]; // +1 to handle the 0th step

        // if there are no steps, we don't have to pay any fee
        dp[0] = 0;
        // only one step, so we have to pay its fee
        dp[1] = fees[0];
        // for 2 or 3 steps staircase, since we start from the first step so we have to pay 
        // its fee and from the first step we can reach the top by taking two or three steps, 
        // so we don't have to pay any other fee.
        dp[2] = dp[3] = fees[0];

        for (int i = 3; i < fees.length; i++) {
            dp[i + 1] = Math.min(dp[i] + fees[i],
                    Math.min(dp[i - 1] + fees[i - 1], dp[i - 2] + fees[i - 2]));
        }

        return dp[fees.length];
    }

    public static void main(String[] args) {
        MinJumpFee sc = new MinJumpFee();
        int[] fee = { 1, 2, 5, 2, 1, 2 };
        // System.out.println(sc.findMinFeeBruteForce(fee));
        // fee = new int[] { 2, 3, 4, 5 };
        // System.out.println(sc.findMinFeeBruteForce(fee));
        // fee = new int[] {0};
        // System.out.println(sc.findMinFeeBruteForce(fee));

        // System.out.println(sc.findMinFeeTopDown(fee));
        // fee = new int[] { 2, 3, 4, 5 };
        // System.out.println(sc.findMinFeeTopDown(fee));
        // fee = new int[] {};
        // System.out.println(sc.findMinFeeTopDown(fee));

        System.out.println(sc.findMinFeeBottomDown(fee));
        fee = new int[] { 2, 3, 4, 5 };
        System.out.println(sc.findMinFeeBottomDown(fee));
        fee = new int[] {};
        System.out.println(sc.findMinFeeBottomDown(fee));
    }
}