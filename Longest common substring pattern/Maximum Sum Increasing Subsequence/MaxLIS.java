import java.util.HashMap;

public class MaxLIS {
    // O(2^n) time | O(n) space
    // where n is the length of the input array
    public int findMSISRecursive(int[] nums) {
        return findMSISRecursive(nums, 0, -1, 0);
    }

    public int findMSISRecursive(int[] nums, int currentIndex, int prevIndex, int sum) {
        if (currentIndex == nums.length) {
            return sum;
        }

        int sum1 = sum;
        if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
            sum1 = findMSISRecursive(nums, currentIndex + 1, currentIndex, sum + nums[currentIndex]);
        }

        int sum2 = findMSISRecursive(nums, currentIndex + 1, prevIndex, sum);

        return Math.max(sum1, sum2);
    }

    // O(n^2) time | O(n^2) space
    // where n is the length of the input array
    // time: n * n subproblems. so, O(n^2)
    // space: O(n^2) for memoization array & O(n) for recursive stack
    // so O(n^2 + n), which is asymptotically equivalent to O(n^2)
    public int findMSISTopDown(int[] nums) {
        HashMap<String, Integer> dp = new HashMap<>();
        return findMSISTopDown(dp, nums, 0, -1, 0);
    }

    public int findMSISTopDown(HashMap<String, Integer> dp, int[] nums, int currentIndex, int prevIndex, int sum) {
        if (currentIndex == nums.length) {
            return sum;
        }

        String hashKey = currentIndex + "-" + prevIndex + "-" + sum;
        if (!dp.containsKey(hashKey)) {
            int sum1 = sum;
            if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
                sum1 = findMSISTopDown(dp, nums, currentIndex + 1, currentIndex, sum + nums[currentIndex]);
            }

            int sum2 = findMSISTopDown(dp, nums, currentIndex + 1, prevIndex, sum);

            dp.put(hashKey, Math.max(sum1, sum2));
        }

        return dp.get(hashKey);
    }

    // O(n^2) time | O(n) space
    // where n is the length of the input array
    public int findMSISBottomUp(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int maxSum = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + nums[i]) {
                    dp[i] = dp[j] + nums[i];
                    maxSum = Math.max(maxSum, dp[i]);
                }
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaxLIS msis = new MaxLIS();
        int[] nums = { 4, 1, 2, 6, 10, 1, 12 };

        // System.out.println(msis.findMSISRecursive(nums));
        // nums = new int[] { -4, 10, 3, 7, 15 };
        // System.out.println(msis.findMSISRecursive(nums));

        // System.out.println(msis.findMSISTopDown(nums));
        // nums = new int[] { -4, 10, 3, 7, 15 };
        // System.out.println(msis.findMSISTopDown(nums));

        System.out.println(msis.findMSISBottomUp(nums));
        nums = new int[] { -4, 10, 3, 7, 15 };
        System.out.println(msis.findMSISBottomUp(nums));
    }
}