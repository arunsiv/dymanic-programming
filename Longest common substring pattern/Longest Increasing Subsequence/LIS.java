public class LIS {
    // O(2^n) time | O(n) space
    // where n is the length of the input array
    public int findLISLengthBruteForce(int[] nums) {
        return findLISLengthBruteForce(nums, 0, -1);
    }

    public int findLISLengthBruteForce(int[] nums, int currentIndex, int prevIndex) {
        if (currentIndex == nums.length) {
            return 0;
        }

        // include the number at the currentIndex if it is greater than the number at
        // the previousIndex
        int c1 = 0;
        if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
            c1 = 1 + findLISLengthBruteForce(nums, currentIndex + 1, currentIndex);
        }

        // excluding the number at currentIndex
        int c2 = findLISLengthBruteForce(nums, currentIndex + 1, prevIndex);

        return Math.max(c1, c2);
    }

    // O(n^2) time | O(n^2) space
    // where n is the length of the input array
    // time: n * n subproblems. so, O(n^2)
    // space: O(n^2) for memoization array & O(n) for recursive stack
    // so O(n^2 + n), which is asymptotically equivalent to O(n^2)
    public int findLISLengthTopDown(int[] nums) {
        int[][] dp = new int[nums.length][nums.length + 1];
        return findLISLengthTopDown(dp, nums, 0, -1);
    }

    public int findLISLengthTopDown(int[][] dp, int[] nums, int currentIndex, int prevIndex) {
        if (currentIndex == nums.length) {
            return 0;
        }

        if (dp[currentIndex][prevIndex + 1] == 0) {
            // include the number at the currentIndex if it is greater than the number at
            // the previousIndex
            int c1 = 0;
            if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
                c1 = 1 + findLISLengthTopDown(dp, nums, currentIndex + 1, currentIndex);
            }

            // excluding the number at currentIndex
            int c2 = findLISLengthTopDown(dp, nums, currentIndex + 1, prevIndex);

            dp[currentIndex][prevIndex + 1] = Math.max(c1, c2);
        }

        // for (int startIndex = 0; startIndex < nums.length; startIndex++) {
        // for (int endIndex = 0; endIndex < nums.length; endIndex++) {
        // System.out.print(dp[startIndex][endIndex] + "\t");
        // }
        // System.out.println("");
        // }

        return dp[currentIndex][prevIndex + 1];
    }

    // O(n^2) time | O(n) space
    // where n is the length of the input array
    public int findLISLengthBottomUp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;

        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] <= dp[j]) {
                    dp[i] = 1 + dp[j];
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }

        // for (int i : dp) {
        //     System.out.print(i + "\t");
        // }

        // System.out.println();

        return maxLength;
    }

    public static void main(String[] args) {
        LIS lis = new LIS();
        int[] nums = { 4, 2, 3, 6, 10, 1, 12 };

        // System.out.println(lis.findLISLengthBruteForce(nums));
        // nums = new int[] { -4, 10, 3, 7, 15 };
        // System.out.println(lis.findLISLengthBruteForce(nums));
        // nums = new int[] { 1, -1 };
        // System.out.println(lis.findLISLengthBruteForce(nums));

        // System.out.println(lis.findLISLengthTopDown(nums));
        // nums = new int[] { -4, 10, 3, 7, 15 };
        // System.out.println(lis.findLISLengthTopDown(nums));
        // nums = new int[] { 1, -1 };
        // System.out.println(lis.findLISLengthTopDown(nums));

        System.out.println(lis.findLISLengthBottomUp(nums));
        nums = new int[] { -4, 10, 3, 7, 15 };
        System.out.println(lis.findLISLengthBottomUp(nums));
        nums = new int[] { 1, -1 };
        System.out.println(lis.findLISLengthBottomUp(nums));
        nums = new int[] {};
        System.out.println(lis.findLISLengthBottomUp(nums));
    }
}