public class LASS {
    // O(2^n) time | O(n) space
    // where n is the length of the input array
    public int findLASLengthBruteForce(int[] nums) {
        return Math.max(findLASLengthBruteForce(nums, 0, -1, true), findLASLengthBruteForce(nums, 0, -1, false));
    }

    public int findLASLengthBruteForce(int[] nums, int currentIndex, int prevIndex, boolean isAscending) {
        if (currentIndex == nums.length) {
            return 0;
        }

        int c1 = 0;
        if (isAscending) {
            // if ascending, the next element should be bigger
            if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
                c1 = 1 + findLASLengthBruteForce(nums, currentIndex + 1, currentIndex, !isAscending);
            }
        } else {
            // if descending, the next element should be smaller
            if (prevIndex == -1 || nums[currentIndex] < nums[prevIndex]) {
                c1 = 1 + findLASLengthBruteForce(nums, currentIndex + 1, currentIndex, !isAscending);
            }
        }

        // skip the cuurent element
        int c2 = findLASLengthBruteForce(nums, currentIndex + 1, prevIndex, isAscending);

        return Math.max(c1, c2);
    }

    // O(n^2) time | O(n^2) space
    // where n is the length of the input array
    public int findLASLengthTopdown(int[] nums) {
        int[][][] dp = new int[nums.length][nums.length][2];
        return Math.max(findLASLengthTopdown(dp, nums, 0, -1, true), findLASLengthTopdown(dp, nums, 0, -1, false));
    }

    public int findLASLengthTopdown(int[][][] dp, int[] nums, int currentIndex, int prevIndex, boolean isAscending) {
        if (currentIndex == nums.length) {
            return 0;
        }

        if (dp[currentIndex][prevIndex][isAscending ? 1 : 0] == 0) {
            int c1 = 0;
            if (isAscending) {
                // if ascending, the next element should be bigger
                if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
                    c1 = 1 + findLASLengthTopdown(dp, nums, currentIndex + 1, currentIndex, !isAscending);
                }
            } else {
                // if descending, the next element should be smaller
                if (prevIndex == -1 || nums[currentIndex] < nums[prevIndex]) {
                    c1 = 1 + findLASLengthTopdown(dp, nums, currentIndex + 1, currentIndex, !isAscending);
                }
            }

            // skip the cuurent element
            int c2 = findLASLengthTopdown(dp, nums, currentIndex + 1, prevIndex, isAscending);

            dp[currentIndex][prevIndex][isAscending ? 1 : 0] = Math.max(c1, c2);
        }

        return dp[currentIndex][prevIndex][isAscending ? 1 : 0];
    }

    // O(n^2) time | O(n) space
    // where n is the length of the input array
    public int findLASLengthBottomUp(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        // dp[i][0] = stores the LAS ending at 'i' such that the last two elements are
        // in ascending order
        // dp[i][1] = stores the LAS ending at 'i' such that the last two elements are
        // in descending order
        int[][] dp = new int[n][2];
        int maxLength = 1;

        for (int i = 0; i < n; i++) {
            dp[i][0] = dp[i][1] = 1; // every single element can be considered as LAS of length 1
            for (int j = 0; j < i; j++) {
                // if nums[i] is BIGGER than nums[j] then we will consider the LAS ending at
                // 'j' where the last two elements were in DESCENDING order
                if (nums[i] > nums[j]) {
                    dp[i][0] = Math.max(dp[i][0], 1 + dp[j][1]);
                    maxLength = Math.max(maxLength, dp[i][0]);
                }

                // if nums[i] is SMALLER than nums[j] then we will consider the LAS ending at
                // 'j' where the last two elements were in ASCENDING order
                if (nums[i] < nums[j]) {
                    dp[i][1] = Math.max(dp[i][1], 1 + dp[j][0]);
                    maxLength = Math.max(maxLength, dp[i][1]);
                }
            }
        }

        for (int startIndex = 0; startIndex < n; startIndex++) {
            for (int endIndex = 0; endIndex < 2; endIndex++) {
                System.out.print(dp[startIndex][endIndex] + "\t");
            }
            System.out.println("");
        }
        System.out.println("--------");

        return maxLength;
    }

    public static void main(String[] args) {
        LASS las = new LASS();
        int[] nums = { 1, 2, 3, 4 };
        // System.out.println(las.findLASLengthBruteForce(nums));
        // nums = new int[] { 3, 2, 1, 4 };
        // System.out.println(las.findLASLengthBruteForce(nums));
        // nums = new int[] { 1, 3, 2, 4 };
        // System.out.println(las.findLASLengthBruteForce(nums));

        // nums = new int[] { 1, 2, 3, 4 };
        // System.out.println(las.findLASLengthBruteForce(nums));
        // nums = new int[] { 3, 2, 1, 4 };
        // System.out.println(las.findLASLengthBruteForce(nums));
        // nums = new int[] { 1, 3, 2, 4 };
        // System.out.println(las.findLASLengthBruteForce(nums));

        nums = new int[] { 1, 2, 3, 4 };
        System.out.println(las.findLASLengthBottomUp(nums));
        nums = new int[] { 3, 2, 1, 4 };
        System.out.println(las.findLASLengthBottomUp(nums));
        nums = new int[] { 1, 3, 2, 4 };
        System.out.println(las.findLASLengthBottomUp(nums));
        nums = new int[] { 8, 9, 6, 4, 5, 7, 3, 2, 4 };
        System.out.println(las.findLASLengthBottomUp(nums));
    }
}