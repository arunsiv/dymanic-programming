public class MDSS {
    //O(n^2) time | O(n) space
    //where n is the length of the string
    public int findMinimumDeletionsBottomUp(int[] nums) {
        //Get the LIS
        int lis = findLISLengthBottomUp(nums);

        // subtracting the length of LIS from the length of the input array to get minimum 
        // number of deletions
        return nums.length - lis;
    }

    public int findLISLengthBottomUp(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = 1;

        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] <= dp[j]) {
                    dp[i] = dp[j] + 1;
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        MDSS mdss = new MDSS();
        int[] nums = { 4, 2, 3, 6, 10, 1, 12 };
        System.out.println(mdss.findMinimumDeletionsBottomUp(nums));
        nums = new int[] { -4, 10, 3, 7, 15 };
        System.out.println(mdss.findMinimumDeletionsBottomUp(nums));
        nums = new int[] { 3, 2, 1, 0 };
        System.out.println(mdss.findMinimumDeletionsBottomUp(nums));
    }
}